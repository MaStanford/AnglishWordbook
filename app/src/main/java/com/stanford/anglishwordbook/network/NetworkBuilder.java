package com.stanford.anglishwordbook.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stanford.anglishwordbook.network.requests.IRequest;

import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;

import io.gsonfire.GsonFireBuilder;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Just a big fluffy wrapper for the RestAdapterBuilder.
 * Created by m.stanford on 3/3/15.
 */
public class NetworkBuilder {

    /**
     * Webservice
     * Contains the Java EE like endpoints.
     *
     * The actual class gets build by Retro using the annotations and builder methods.
     *
     */
    private NetworkWebservice mService = null;

    /**
     * Request interceptor
     */
    private RequestInterceptor mRequestInterceptor = null;

    /**
     * Domain Url
     */
    private String mUrl = "";

    /**
     * Log Level
     */
    RestAdapter.LogLevel mLogLevel = RestAdapter.LogLevel.FULL;

    /**
     * We need a custom gson for serialization callbacks.  Maybe we will need to set this to a custom one for other reasons in the future.
     */
    private Gson mGson = null;

    /**
     * Private constructor.
     */
    public NetworkBuilder(){
    }

    /**
     * Builds the service.
     *
     * We will error check the request here. I can't find any other place to force the rest of the SDK to check params.
     * @return
     */
    public NetworkWebservice buildService(IRequest request){

        //Check to make sure
        if(this.mUrl == null || this.mUrl.equals("")) {
            throw new InvalidParameterException("Invalids params.  Check URL or Request Params or query params");
        }

        /**
         * We may also want to enable hooks in the response object.
         * That would mean we need to have the response type in the request?
         */
        if(mGson == null){
            GsonFireBuilder Firebuilder = new GsonFireBuilder().enableHooks(request.getClass());
            GsonBuilder builder = Firebuilder.createGsonBuilder();
            builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
            mGson = builder.create();
        }

        if(mRequestInterceptor == null){
            setDefaultRequestInterceptor();
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(this.mRequestInterceptor)
                .setEndpoint(this.mUrl)
                .setLogLevel(this.mLogLevel)
                .setErrorHandler(new NetworkErrorHandler())
                .setConverter(new GsonConverter(this.mGson))
                .build();

        mService = restAdapter.create(NetworkWebservice.class);

        return mService;
    }

    /**
     *
     * @param url
     * @return
     */
    public NetworkBuilder setUrl(String url){
        this.mUrl = url;
        return this;
    }

    /**
     * Sets a default interceptor.
     */
    protected NetworkBuilder setDefaultRequestInterceptor(){
        return setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("contentType", "application/x-www-form-urlencoded");
                request.addHeader("X-Parse-Application-Id", NetworkWebservice.APP_ID);
                request.addHeader("X-Parse-REST-API-Key", NetworkWebservice.REST_API_KEY);
            }
        });
    }

    /**
     * Sets a default gson that has callbacks in the IGinSerializable interface
     * @return
     */
    protected NetworkBuilder setDefaultGson(){
        this.mGson = new Gson();
        return this;
    }

    protected NetworkBuilder setGson(Gson gson){
        this.mGson = gson;
        return this;
    }

    /**
     * Sets a custom interceptor
     * @param ri
     * @return
     */
    protected NetworkBuilder setRequestInterceptor(RequestInterceptor ri) {
        this.mRequestInterceptor = ri;

       return this;
    }
}

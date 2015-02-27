package com.stanford.anglishwordbook.network;

import android.net.Uri;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * This class handles putting together the requests and params and headers into a JSONRequest.
 *
 * Created by m.stanford on 2/26/15.
 */
public class WebService {

    /**
     * Paths for our network calls.
     */
    public static final String BASE_PATH    = "http://anglish.wikia.com";
    public static final String API_PATH     = "/api";
    public static final String API_VERSION  = "/v1";


    // Required parameters that we set on almost every service call
    public static final String PARAM_REQUIRED_AUTH_TOKEN        = "authToken";

    // Optional parameters available for pretty much every service call
    public static final String PARAM_OPTIONAL_FORMAT            = "format";
    public static final String FORMAT_JSON                      = "application/json";
    public static final String PARAM_OPTIONAL_CALLBACK          = "callback";
    public static final String PARAM_OPTIONAL_RETURNHTML        = "returnHTML";

    /**
     * Params for the actual Volley request.
     */
    private static final Integer SOCKET_TIMEOUT_MS              = 15000;

    /**
     * This does the actual work of the request.
     * Pass in the volley queue, the request, the params, the body and listeners.
     *
     * @param queue
     * @param serviceRequest
     * @param requestParams
     * @param body
     * @param successListener
     * @param errorListener
     */
    public static void makeRequest(
            final RequestQueue queue,
            final BaseRequest serviceRequest,
            final Bundle requestParams,
            final JSONBody body,
            final Response.Listener<JSONObject> successListener,
            final Response.ErrorListener errorListener) {

        //Params, api key needs to be first I think
        final Bundle params = generateParams(requestParams);

        Uri mURI = generateURI(serviceRequest, params);

        /**
         * This is the Volley request, we build this from our projects request.
         */
        JsonObjectRequest request = new JsonObjectRequest(
            serviceRequest.getRequestMethod(),
            mURI.toString(),
            null,
            successListener,
            new Response.ErrorListener() {
                @Override public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    errorListener.onErrorResponse(error);
                    return;
                }

                Integer statusCode = error.networkResponse.statusCode;
                if (statusCode >= 400 && statusCode < 500) {
                    errorListener.onErrorResponse(serviceRequest.getClientErrorObject(error.networkResponse));
                } else {
                    // we do not have special types for any other status codes yet
                    errorListener.onErrorResponse(error);
                }
            }
        }){
            /**
             * Add headers here
             * @return
             * @throws com.android.volley.AuthFailureError
             */
            @Override public Map<String,String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            /**
             * Add body to request here
             * @return
             */
            @Override public byte[] getBody() {
                if(body != null){
                    if(!serviceRequest.isBodyValid(body)) {
                        errorListener.onErrorResponse(new VolleyError("Unable to send request.  Missing required body param."));
                        return super.getBody();
                    }

                    return JSONBody.toByteArray(body.toString());
                }
                return super.getBody();
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

        queue.start();
    }

    /**
     * Returns the URI based on params and request
     * @param serviceRequest
     * @return
     */
    private static Uri generateURI(BaseRequest serviceRequest,Bundle params){

        Uri uri = null;

        String hostName = serviceRequest.getHostName();
        String uriString = serviceRequest.getServiceUri();

        uri = Uri.parse(String.format("%s://%s%s%s%s%s%s",
                serviceRequest.getRequestTypeSecure() ? "https" : "http",
                hostName,
                API_VERSION,
                uriString));

        //Set the API version and URI based on request
        switch(serviceRequest.getServiceName()) {

        }
        return uri;
    }

    /**
     * Puts params into the bundle, if null create empty.
     * @param requestParams
     * @return
     */
    private static Bundle generateParams(Bundle requestParams){
        Bundle params = new Bundle();
        if (requestParams != null) {
            params.putAll(requestParams);
        }
        return params;
    }

    /**
     * Appends the parama to the end of the URI.
     * @param uri
     * @param params
     * @return
     */
    private static Uri appendQueryBundle(Uri uri, Bundle params) {

        Uri.Builder uriBuilder = uri.buildUpon();

        // Loop through our params and append them to the Uri.
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (value != null)
                uriBuilder.appendQueryParameter(key, value.toString());
        }

        uri = uriBuilder.build();
        return uri;
    }
}

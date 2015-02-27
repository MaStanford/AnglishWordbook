package com.stanford.anglishwordbook.network;

import android.os.Bundle;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles all the params, URIs, methods, hostnames for the request.
 *
 * Created by m.stanford on 2/26/15.
 */
public abstract class BaseRequest {

    /**
     * Use http or https
     */
    protected boolean mRequestTypeSecure = false;

    /**
     * Service name. This will help build the URI.
     * Ex. public static final int ARTICLES = 0
     */
    protected int mServiceName;

    /**
     * Uri of the service.
     * Ex. /Articles or /Users
     */
    protected String mServiceUri;

    /**
     * The method such as GET, POST, DELETE
     */
    protected int mRequestMethod;

    /**
     * Hostname
     * Ex. anglosh.wikia.com
     */
    protected String mHostName;

    /**
     * Params required for this request.
     * Ex. Locale, Location, List, Id etc.
     */
    protected List<String> mRequiredParams = new ArrayList<String>();

    /**
     *
     */
    protected List<String> mOptionalParams = new ArrayList<String>();

    /**
     * The list of required headers.
     * Example: Auth : "12947219hf8282"
     */
    protected List<String> mRequiredHeaders = new ArrayList<String>();

    /**
     * List of the body keys needed.
     */
    protected List<String> mRequiredBody = new ArrayList<String>();

    /**
     * List of optional body keys.
     */
    protected List<String> mOptionalBody = new ArrayList<String>();

    /**
     * Create a new request with the URI of the request and the service name from webservice.
     * @param serviceName
     * @param serviceUri
     */
    public BaseRequest(int serviceName, String serviceUri) {
        mServiceName = serviceName;
        mServiceUri = serviceUri;
    }

    /**
     * Set the volley request method.
     * @param requestMethod
     * @return
     */
    public BaseRequest setRequestMethod(int requestMethod){
        this.mRequestMethod = requestMethod;
        return this;
    }

    /**
     * Returns the request method such as Request.Method.GET
     * @return
     */
    public int getRequestMethod() {
        return mRequestMethod;
    }

    /**
     * Returns the constant of the service name.
     * This is used to help build the URI in case different services have different API version or something.
     * @return
     */
    public int getServiceName() {
        return mServiceName;
    }

    /**
     * Returns the service Uri, such as /Articles or /Users
     * @return
     */
    public String getServiceUri() {
        return mServiceUri;
    }

    /**
     * Returns the hostname.
     * For this project this should be http://anglish.wikia.com
     * @return
     */
    public String getHostName() {
        return mHostName;
    }

    /**
     * checks if we needs https or not in this request.
     * @return
     */
    public boolean getRequestTypeSecure() {
        return mRequestTypeSecure;
    }

    /**
     * Sets if we need https or not.
     * @param requestTypeSecure
     */
    public void setRequestTypeSecure(boolean requestTypeSecure) {
        mRequestTypeSecure = requestTypeSecure;
    }

    /**
     * Returns a list of required params.
     * @return
     */
    public List<String> getRequiredParams() {
        return mRequiredParams;
    }

    /**
     * Returns a list of optional params.
     * @return
     */
    public List<String> getOptionalParams() {
        return mOptionalParams;
    }

    /**
     * Returns the list of required headers.
     * @return
     */
    public List<String> getRequiredHeaders() {
        return mRequiredHeaders;
    }

    /**
     * Checks if we need an auth token.
     * Usually the auth token is saved in the cookie manager or a cache or even shared prefs.
     * @return
     */
    public Boolean isAuthTokenRequired() {
        return mRequiredParams.contains(WebService.PARAM_REQUIRED_AUTH_TOKEN);
    }

    /**
     * Do a check here to see if an auth header is required.  The auth header will be in the webservice
     * and added when the request is constructed.
     * @return
     */
    public Boolean isAuthHeaderRequired() {
        return false;
    }

    /**
     * Checks to see if all params are included in the bundle
     * @param params
     * @return
     */
    public Boolean areParamsValid(Bundle params) {
        // make certain all required params are present
        for (String param : mRequiredParams) {
            if (!params.containsKey(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if all headers are included in the bundle
     * @param headers
     * @return
     */
    public Boolean areHeadersValid(Map<String,String> headers) {
        // make certain all required params are present
        for (String param : mRequiredHeaders) {
            if (!headers.containsKey(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes sure that all the params in the body are there.
     * @param body
     * @return
     */
    public boolean isBodyValid(JSONBody body){
        for (String param : mRequiredBody) {
            if (!body.getMap().containsKey(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the error object.
     * @param networkResponse
     * @return
     */
    public VolleyError getClientErrorObject(NetworkResponse networkResponse) {
        return new VolleyError(networkResponse);
    }

    abstract public List<String> createDefaultOptionalParams();
    abstract public List<String> createDefaultRequiredParams();
}

package com.stanford.anglishwordbook.network.requests.usercallbacks;

/**
 * Communicates response data (results) from the server back to the consumer of the UserSDK when the
 * asynchronous response returns. This class is utilized in the  public API of the UserSDK (passed
 * in as a parameter to the public methods of GinUser)
 */
public interface PreferenceStatusCallback {

    /**
     * Called when the request results/data is returned from the API
     * */
    void statusResultReady(final boolean completedSuccessfully);
}
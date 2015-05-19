package com.stanford.anglishwordbook.network.requests.usercallbacks;

import java.util.Map;

/**
 * Communicates response data (results) from the server back to the consumer of the UserSDK when the
 * asynchronous response returns. This class is utilized in the  public API of the UserSDK (passed
 * in as a parameter to the public methods of GinUser)
 */
public interface PreferenceDataCallback {

    /**
     * Called when the request results/data is returned from the API
     * */
    void requestDataReady(Map<String, String> results, final boolean completedSuccessfully);
}
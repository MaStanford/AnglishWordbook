package com.stanford.anglishwordbook.network;

/**
 * Created by m.stanford on 2/26/15.
 */
public class InputError {
    private String mId;
    private String mMessage;

    public InputError(String id, String message) {
        mId = id;
        mMessage = message;
    }

    public String getId() {
        return mId;
    }

    public String getMessage() {
        return mMessage;
    }
}

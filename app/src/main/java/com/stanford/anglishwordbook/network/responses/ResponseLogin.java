package com.stanford.anglishwordbook.network.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by m.stanford on 3/10/15.
 */
public class ResponseLogin {

    @SerializedName("session")
    private String mSession;

    @SerializedName("uuid")
    private String mUuid;

    @SerializedName("isValidOauth")
    private boolean mIsValidOAuth;

    @SerializedName("guid")
    private String mGuid;

    @SerializedName("time")
    private long mTime;

    @SerializedName("expiration")
    private long mExpiration;

    public ResponseLogin() {
    }

    public ResponseLogin(String session, String uuid, boolean isValidOAuth, String guid, long time, long expiration) {
        mSession = session;
        mUuid = uuid;
        mIsValidOAuth = isValidOAuth;
        mGuid = guid;
        mTime = time;
        mExpiration = expiration;
    }

    public String getSession() {
        return mSession;
    }

    public void setSession(String session) {
        mSession = session;
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    public boolean isValidOAuth() {
        return mIsValidOAuth;
    }

    public void setValidOAuth(boolean isValidOAuth) {
        mIsValidOAuth = isValidOAuth;
    }

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String guid) {
        mGuid = guid;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public long getExpiration() {
        return mExpiration;
    }

    public void setExpiration(long expiration) {
        mExpiration = expiration;
    }
}

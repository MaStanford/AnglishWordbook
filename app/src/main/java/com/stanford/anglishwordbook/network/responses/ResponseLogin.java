package com.stanford.anglishwordbook.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by m.stanford on 3/10/15.
 */
public class ResponseLogin {

    private class BanEnd {
        @SerializedName("__type")
        private String mType;

        @SerializedName("iso")
        private Date mDate;

        private BanEnd(String type, Date date) {
            mType = type;
            mDate = date;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public Date getDate() {
            return mDate;
        }

        public void setDate(Date date) {
            mDate = date;
        }

        @Override
        public String toString() {
            return "BanEnd{" +
                    "mType='" + mType + '\'' +
                    ", mDate=" + mDate +
                    '}';
        }
    }

    @SerializedName("username")
    private String mUserName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("isAdmin")
    private boolean mIsAdmin;

    @SerializedName("objectId")
    private String mObjectId;

    @SerializedName("points")
    private long mPoints;

    @SerializedName("sessionToken")
    private String mSessionToken;

    @SerializedName("wordCount")
    private long mWordCount;

    @SerializedName("flags")
    private long mFlags;

    @SerializedName("banEnd")
    private BanEnd mBanEnd;

    public ResponseLogin(String userName, String email, boolean isAdmin, String objectId, long points, String sessionToken, long wordCount, long flags, BanEnd banEnd) {
        mUserName = userName;
        mEmail = email;
        mIsAdmin = isAdmin;
        mObjectId = objectId;
        mPoints = points;
        mSessionToken = sessionToken;
        mWordCount = wordCount;
        mFlags = flags;
        mBanEnd = banEnd;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public boolean isAdmin() {
        return mIsAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        mIsAdmin = isAdmin;
    }

    public String getObjectId() {
        return mObjectId;
    }

    public void setObjectId(String objectId) {
        mObjectId = objectId;
    }

    public long getPoints() {
        return mPoints;
    }

    public void setPoints(long points) {
        mPoints = points;
    }

    public String getSessionToken() {
        return mSessionToken;
    }

    public void setSessionToken(String sessionToken) {
        mSessionToken = sessionToken;
    }

    public long getWordCount() {
        return mWordCount;
    }

    public void setWordCount(long wordCount) {
        mWordCount = wordCount;
    }

    public long getFlags() {
        return mFlags;
    }

    public void setFlags(long flags) {
        mFlags = flags;
    }

    public BanEnd getBanEnd() {
        return mBanEnd;
    }

    public void setBanEnd(BanEnd banEnd) {
        mBanEnd = banEnd;
    }
}

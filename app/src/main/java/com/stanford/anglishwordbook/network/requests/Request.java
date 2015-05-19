package com.stanford.anglishwordbook.network.requests;

import android.util.Log;

import com.stanford.anglishwordbook.network.ISerializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.gsonfire.annotations.PostDeserialize;
import io.gsonfire.annotations.PreSerialize;

public class Request implements IRequest, ISerializable {

    transient private static final String TAG = Request.class.getSimpleName();

    transient List<String> tRequiredParams = new ArrayList<>();
    transient List<String> tRequiredQueryParams = new ArrayList<>();

    transient Map<String, Object> tQueryParams;

    public Request() {

    }

    @Override
    public void setParams(Map<String, Object> params) {

    }

    @Override
    public Map<String, Object> getParams() {
        return null;
    }

    @Override
    public List<String> getRequiredParams() {
        return tRequiredParams;
    }

    public void setRequiredParams(List<String> requiredParams) {
        tRequiredParams = requiredParams;
    }

    @Override
    public List<String> gettRequiredQueryParams() {
        return tRequiredQueryParams;
    }

    public void settRequiredQueryParams(List<String> tRequiredQueryParams) {
        this.tRequiredQueryParams = tRequiredQueryParams;
    }

    public Map<String, Object> gettQueryParams() {
        return tQueryParams;
    }

    public void settQueryParams(Map<String, Object> tQueryParams) {
        this.tQueryParams = tQueryParams;
    }

    @PostDeserialize
    @Override
    public void postDeserialize() {
        Log.d(TAG, "This is a post deserialize");
    }

    @PreSerialize
    @Override
    public void preSerialize() {
        Log.d(TAG, "This is a pre serialize");
    }
}

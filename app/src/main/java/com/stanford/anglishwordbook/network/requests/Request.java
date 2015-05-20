package com.stanford.anglishwordbook.network.requests;

import android.util.Log;

import com.stanford.anglishwordbook.network.ISerializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.gsonfire.annotations.PostDeserialize;
import io.gsonfire.annotations.PreSerialize;

public abstract class Request implements IRequest, ISerializable {

    transient private static final String TAG = Request.class.getSimpleName();

    transient List<String> tRequiredQueryParams = new ArrayList<>();
    transient Map<String, String> tQueryParams;

    transient List<String> tRequiredBodyParams = new ArrayList<>();
    Map<String, String> tBodyParams;

    public Request(Map<String, String> queryParams, Map<String, String> bodyParams) {
        this.tQueryParams = queryParams;
        this.tBodyParams = bodyParams;
        generateRequiredParams();
    }

    public abstract void generateRequiredParams();

    @Override
    public void setQueryParams(Map<String, String> params) {
        this.tQueryParams = params;
    }

    @Override
    public Map<String, String> getQueryParams() {
        return this.tQueryParams;
    }

    @Override
    public boolean isQueryParamsValid() {
        for(String key : tRequiredQueryParams){
            if(!this.tQueryParams.keySet().contains(key)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getRequiredQueryParams() {
        return this.tRequiredQueryParams;
    }

    @Override
    public List<String> getRequiredBodyParams() {
        return this.tRequiredBodyParams;
    }

    @Override
    public boolean isBodyParamsValid() {
        for(String key : tRequiredBodyParams){
            if(!this.tBodyParams.keySet().contains(key)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, String> getBodyParams() {
        return this.tBodyParams;
    }

    @Override
    public void setBodyParams(Map<String, String> params) {
        this.tBodyParams = params;
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

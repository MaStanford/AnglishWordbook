package com.stanford.anglishwordbook.network.responses;

import io.gsonfire.annotations.PostDeserialize;
import io.gsonfire.annotations.PreSerialize;

/**
 * Created by m.stanford on 3/13/15.
 */
public class ResponseLoadUserSettings extends BaseResponse {

    @PostDeserialize
    @Override
    public void postDeserialize() {

    }

    @PreSerialize
    @Override
    public void preSerialize() {

    }
}

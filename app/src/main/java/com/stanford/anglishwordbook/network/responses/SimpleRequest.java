package com.stanford.anglishwordbook.network.responses;

import com.stanford.anglishwordbook.network.requests.Request;

import java.util.HashMap;

/**
 * Created by m.stanford on 5/19/15.
 */
public class SimpleRequest extends Request {

    public SimpleRequest() {
        super(new HashMap<String, String>(), new HashMap<String, String>());
    }

    @Override
    public void generateRequiredParams() {

    }
}

package com.stanford.anglishwordbook.network.requests;

import java.util.Map;

/**
 * Created by m.stanford on 5/18/15.
 */
public class CreateUserRequest extends Request{

    public CreateUserRequest(Map<String, String> query, Map<String, String> body) {
        super(query, body);
    }

    @Override
    public void generateRequiredParams() {
        this.tRequiredBodyParams.add("username");
        this.tRequiredBodyParams.add("password");
        this.tRequiredBodyParams.add("email");
        this.tRequiredBodyParams.add("isAdmin");
    }
}

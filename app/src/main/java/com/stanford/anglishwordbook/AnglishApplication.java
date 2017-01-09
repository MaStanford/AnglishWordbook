package com.stanford.anglishwordbook;


import android.app.Application;

import com.parse.Parse;

/**
 * Created by m.stanford on 5/20/15.
 */
public class AnglishApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.Configuration.Builder builder = new Parse.Configuration.Builder(this);
        builder.applicationId("ApuxkukQC9mFuLIdIjG3qC27ms5kZ4XZbopxUohp");
        builder.server("https://anglishwordbook.herokuapp.com/anglishwordbook/");
        Parse.initialize(builder.build());
    }
}

package com.stanford.anglishwordbook;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

/**
 * Created by m.stanford on 5/20/15.
 */
public class AnglishApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "ApuxkukQC9mFuLIdIjG3qC27ms5kZ4XZbopxUohp", "tXpQPZMSGqZoKAg2DXBthWAyFfqSEEeJxX2O6HbO");
    }
}

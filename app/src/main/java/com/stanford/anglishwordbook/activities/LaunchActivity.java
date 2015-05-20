package com.stanford.anglishwordbook.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;

import com.stanford.anglishwordbook.R;

public class LaunchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

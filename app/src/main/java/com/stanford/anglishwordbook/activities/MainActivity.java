package com.stanford.anglishwordbook.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;
import com.stanford.anglishwordbook.fragments.EtymologyFragment;
import com.stanford.anglishwordbook.fragments.NameBitFragment;
import com.stanford.anglishwordbook.fragments.NavigationDrawerFragment;
import com.stanford.anglishwordbook.R;
import com.stanford.anglishwordbook.fragments.OnFragmentInteractionListener;
import com.stanford.anglishwordbook.fragments.WordBookFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String FRAG_TAG_WORD = "com.stanford.fragment.word";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * list of fragments
     */
    List<Fragment> mFragmentList;

    /**
     * Reference to the menu in order to hide options
     */
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        initFragmentList();

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getString(R.string.title_wordbook);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
    }

    private void checkifAnon() {
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            enableSignUpButton(true);
            enableLogOutButton(true);
        } else {
            enableSignUpButton(false);
            enableLogOutButton(true);
        }
    }

    private void enableLogOutButton(boolean show) {
        MenuItem item = mMenu.findItem(R.id.action_logout);
        item.setVisible(show);
    }

    private void enableSignUpButton(boolean show) {
        MenuItem item = mMenu.findItem(R.id.action_signup);
        item.setVisible(show);
    }

    private void initFragmentList() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(WordBookFragment.newInstance(1));
        mFragmentList.add(NameBitFragment.newInstance(2));
        mFragmentList.add(EtymologyFragment.newInstance(3));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Log.d(TAG, "onNavigationDrawerItemSelected");

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mFragmentList.get(position)).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_wordbook);
                break;
            case 2:
                mTitle = getString(R.string.title_names);
                break;
            case 3:
                mTitle = getString(R.string.title_etymology);
                break;
        }
        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            getMenuInflater().inflate(R.menu.main, menu);
//            checkifAnon();
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_logout:
                ParseUser.logOut();
                Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginIntent);
                finish();
                break;
            case R.id.action_signup:
                Intent regIntent = new Intent(getBaseContext(), RegisterActivity.class);
                regIntent.putExtra(RegisterActivity.ANON_REGISTER, true);
                regIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(regIntent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Object object) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

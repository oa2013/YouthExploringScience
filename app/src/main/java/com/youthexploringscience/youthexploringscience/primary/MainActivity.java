/*
 * Copyright (C) 2017 Janice Wildrick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youthexploringscience.youthexploringscience.primary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youthexploringscience.youthexploringscience.R;
import com.youthexploringscience.youthexploringscience.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity serving as app entry point; buttons afford user options of opening the following
 * web pages in a browser: paylocity, slsc.org, youthexploringscience.com
 * & yes.com student resources
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;

    @BindView(R.id.yes_logo_image)
    ImageView mYesImage;

    @BindView(R.id.paylocity_website_button)
    ImageButton mPaylocityButton;
    @BindView(R.id.slsc_website_button)
    ImageButton mSlscButton;
    @BindView(R.id.student_website_button)
    ImageButton mStudentButton;
    @BindView(R.id.yes_website_button)
    ImageButton mYesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpNavDrawer();
        setUpNavigationView();

        loadImages();


    }

    /**
     * load all images into ImageViews and ImageButtons
     */
    private void loadImages() {

        Glide.with(this)
                .load(R.drawable.yes_logo)
                .into(mYesImage);

        Glide.with(this)
                .load(R.drawable.pay)
                .into(mPaylocityButton);

        Glide.with(this)
                .load(R.drawable.science_center)
                .into(mSlscButton);

        Glide.with(this)
                .load(R.drawable.student)
                .into(mStudentButton);

        Glide.with(this)
                .load(R.drawable.yes_circle)
                .into(mYesButton);
    }

    /**
     * initializes NavigationView & sets an ItemSelectedListener; handles click events
     * for each item in NavigationView menu
     */
    private void setUpNavigationView() {
        mNavigationView = findViewById(R.id.navigation_view);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        if (item.isChecked()) {
                            return true;
                        }
                        //TODO if main activity is in back stack bring it to top of stack.
                        // Otherwise, launch new main activity instance
                        break;
                    case R.id.nav_events:
                        if (item.isChecked()) {
                            return true;
                        }
                        //TODO launch new events activity
                        break;
                    case R.id.nav_contacts:
                        //TODO  launch new contacts activity
                        break;
                    case R.id.nav_settings:
                        //TODO launch preferences settings
                        break;
                }
                adjustCheckedNavItems(item);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * Removes checked = true status for each MenuItem then sets
     * checked = true for item passed as parameter
     *
     * @param item menu item selected
     */
    private void adjustCheckedNavItems(MenuItem item) {
        int size = mNavigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            mNavigationView.getMenu().getItem(i).setChecked(false);
        }
        item.setChecked(true);
    }

    /**
     * initialize DrawerLayout and set ActionBarDrawerToggle as listener to
     * sync DrawerLayout state with ActionBar
     */
    private void setUpNavDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.nav_layout_open, R.string.nav_layout_close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * opens/closes NavigationDrawer
     *
     * @param item menu hamburger icon controlling NavDrawer
     * @return true if hamburger icon was clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * next four methods serve as onClickListeners for ImageButtons through Butterknife
     */
    @OnClick(R.id.paylocity_website_button)
    public void launchPaylocitySite() {
        launchSite(getResources().getString(R.string.link_paylocity));
    }

    /**
     * launches Saint Louis Science Center website in web browser
     */
    @OnClick(R.id.slsc_website_button)
    public void launchSlscSite() {
        launchSite(getResources().getString(R.string.link_slsc_website));
    }

    /**
     * launches YES student resources website in web browser
     */
    @OnClick(R.id.student_website_button)
    public void launchStudentSite() {
        launchSite(getResources().getString(R.string.link_students));
    }

    /**
     * launches Saint Youth Exploring Science website in web browser
     */
    @OnClick(R.id.yes_website_button)
    public void lauchYesSite() {
        launchSite(getResources().getString(R.string.link_yes_website));
    }

    /**
     * convert string to URI, create intent with it and launch intent
     *
     * @param url website which user wishes to navigate to in browser
     */
    private void launchSite(String url) {
        //ensure internet connection is available
        if (NetworkUtils.haveInternetConnection(this)) {
            //convert string to URI
            Uri uri = Uri.parse(url);
            //create intent to launch URI in web browser
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            //resove intent to ensure there is an installed app which can handle intent request
            PackageManager packageManager = this.getPackageManager();
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Log.d(TAG, "No App available to handle action");
            }
        } else {
            //TODO replace this toast with a more descriptive notification to user
            Toast.makeText(this, "No internet connection available", Toast.LENGTH_LONG).show();
        }
    }

}


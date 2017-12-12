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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.youthexploringscience.youthexploringscience.R;

/**
 * Activity serving as app entry point; buttons afford user options of opening the following
 * web pages in a browser: paylocity, slsc.org, youthexploringscience.com
 * & yes.com student resources
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpNavDrawer();
        setUpNavigationView();

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

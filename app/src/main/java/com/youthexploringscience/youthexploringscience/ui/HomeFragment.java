package com.youthexploringscience.youthexploringscience.ui;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youthexploringscience.youthexploringscience.R;
import com.youthexploringscience.youthexploringscience.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = Fragment.class.getName();

    //@BindView(R.id.paylocity_website_button)
    ImageButton mPaylocityButton;
    //@BindView(R.id.slsc_website_button)
    ImageButton mSlscButton;
    //@BindView(R.id.student_website_button)
    ImageButton mStudentButton;
    //@BindView(R.id.yes_website_button)
    ImageButton mYesButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPaylocityButton = (ImageButton) view.findViewById(R.id.paylocity_website_button);
        mSlscButton = (ImageButton) view.findViewById(R.id.slsc_website_button);
        mStudentButton = (ImageButton) view.findViewById(R.id.student_website_button);
        mYesButton = (ImageButton) view.findViewById(R.id.yes_website_button);


        if(view != null) {
            loadImages();
        }

        return view;
    }

    /**
     * load all images into ImageViews and ImageButtons
     */
    private void loadImages() {

        Glide.with(this)
                .load(R.drawable.ic_paylocity)
                .into(mPaylocityButton);

        Glide.with(this)
                .load(R.drawable.ic_stlouis)
                .into(mSlscButton);

        Glide.with(this)
                .load(R.drawable.ic_graduation)
                .into(mStudentButton);

        Glide.with(this)
                .load(R.drawable.ic_yescircle)
                .into(mYesButton);
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
        if (NetworkUtils.haveInternetConnection(getContext())) {
            //convert string to URI
            Uri uri = Uri.parse(url);
            //create intent to launch URI in web browser
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            //resolve intent to ensure there is an installed app which can handle intent request
            PackageManager packageManager = getContext().getPackageManager();
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Log.d(TAG, "No App available to handle action");
            }
        } else {
            //TODO replace this toast with a more descriptive notification to user
            Toast.makeText(getContext(), "No internet connection available", Toast.LENGTH_LONG).show();
        }
    }
}

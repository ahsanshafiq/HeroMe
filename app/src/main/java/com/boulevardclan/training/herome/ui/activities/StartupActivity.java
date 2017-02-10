package com.boulevardclan.training.herome.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.boulevardclan.training.herome.R;
import com.boulevardclan.training.herome.ui.common.enums.HeroPower;
import com.boulevardclan.training.herome.ui.common.enums.HeroPowerOrigin;
import com.boulevardclan.training.herome.ui.fragments.BackStoryFragment;
import com.boulevardclan.training.herome.ui.fragments.PickPowerFragment;
import com.boulevardclan.training.herome.ui.fragments.StartupFragment;

//[DONE]TODO: Remove BackgroundTint Styles
//[DONE]TODO: Style inheritance
//[DONE]TODO: Add fragment change transitions
//[DONE]TODO: Save and restore instance state upon pressing back button
//[DONE]TODO: Start over erases state while pressing back on individual fragment saves state
//TODO: Change package and app name

public class StartupActivity extends AppCompatActivity implements StartupFragment.RequestToLoadPickAPowerFragmentListener,
        PickPowerFragment.RequestToLoadBackStoryFragmentListener, BackStoryFragment.RequestToLoadStartupFragmentListener {

    private static final String LOG_TAG = "StartupActivity";

    private static final String PICK_POWER_FRAGMENT = "PICK_POWER_FRAGMENT";
    private static final String BACK_STORY_FRAGMENT = "BACK_STORY_FRAGMENT";
    private static final String STARTUP_FRAGMENT    = "STARTUP_FRAGMENT";

    private FragmentManager mFragmentManager;
    private StartupActivity self;

    private int mStartupFragmentBSId;
    private int mPickPowerBSId;
    private int mShowBackstoryBSId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        self = this;
        mFragmentManager = getSupportFragmentManager();
        initUI();
    }

    private void initUI() {
        StartupFragment startupFragment = (StartupFragment) mFragmentManager.findFragmentById(R.id.frMain);
        if (startupFragment == null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frMain, StartupFragment.newInstance());
            fragmentTransaction.addToBackStack(STARTUP_FRAGMENT);
            mStartupFragmentBSId = fragmentTransaction.commit();
        }

        /*mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.i(LOG_TAG, String.format("getBackStackEntryCount() == %d", mFragmentManager.getBackStackEntryCount()));
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        Log.i(LOG_TAG, String.format("getBackStackEntryCount() == %d", mFragmentManager.getBackStackEntryCount()));
        if (mFragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestToLoadPickAPowerFragment(HeroPowerOrigin heroPowerOrigin) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frMain, PickPowerFragment.newInstance(heroPowerOrigin));
        fragmentTransaction.addToBackStack(PICK_POWER_FRAGMENT);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        mPickPowerBSId = fragmentTransaction.commit();
    }

    @Override
    public void onRequestToLoadBackStoryFragment(HeroPowerOrigin heroPowerOrigin, HeroPower heroPower) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frMain, BackStoryFragment.newInstance(heroPowerOrigin, heroPower));
        fragmentTransaction.addToBackStack(BACK_STORY_FRAGMENT);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        mShowBackstoryBSId = fragmentTransaction.commit();
    }

    @Override
    public void onRequestToLoadStartupFragment() {
        mFragmentManager.popBackStackImmediate(mStartupFragmentBSId, 0);
        StartupFragment startupFragment = (StartupFragment) mFragmentManager.findFragmentById(R.id.frMain);
        startupFragment.setmHeroPowerOrigin(HeroPowerOrigin.None);
        startupFragment.setupPowerOriginButtons();
    }
}
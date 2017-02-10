package com.boulevardclan.training.herome.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.boulevardclan.training.herome.R;
import com.boulevardclan.training.herome.ui.common.enums.HeroPower;
import com.boulevardclan.training.herome.ui.common.enums.HeroPowerOrigin;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickPowerFragment extends Fragment {
    private static final String LOG_TAG = "PickPowerFragment";

    private static final String ARG_HERO_POWER_ORIGIN = "HeroPowerOrigin";

    private RequestToLoadBackStoryFragmentListener mListener;
    private HeroPowerOrigin mHeroPowerOrigin;
    private HeroPower mHeroPower;

    @BindView(R.id.btnTurtlePower)
    Button btnTurtlePower;
    @BindView(R.id.btnLightning)
    Button btnLightning;
    @BindView(R.id.btnFlight)
    Button btnFlight;
    @BindView(R.id.btnWebSlinging)
    Button btnWebSlinging;
    @BindView(R.id.btnLaserVision)
    Button btnLaserVision;
    @BindView(R.id.btnSuperStrength)
    Button btnSuperStrength;
    @BindView(R.id.btnShowBackStory)
    Button btnShowBackStory;

    @BindDrawable(R.drawable.turtle_power)
    Drawable drTurtlePower;
    @BindDrawable(R.drawable.thors_hammer)
    Drawable drLightning;
    @BindDrawable(R.drawable.super_man_crest)
    Drawable drFlight;
    @BindDrawable(R.drawable.spider_web)
    Drawable drWebSlinging;
    @BindDrawable(R.drawable.laser_vision)
    Drawable drLaserVision;
    @BindDrawable(R.drawable.super_strength)
    Drawable drSuperStrength;

    @BindDrawable(R.drawable.item_selected)
    Drawable drSelected;
    @BindDrawable(R.drawable.item_unselected)
    Drawable drUnSelected;

    @BindString(R.string.show_back_story)
    String showBackStory;

    @BindColor(R.color.colorDisabledButtonText)
    int disabledButtonTextColor;
    @BindColor(R.color.colorButtonText)
    int enabledButtonTextColor;

    public PickPowerFragment() {
        // Required empty public constructor
        mHeroPower = HeroPower.None;
    }

    public static PickPowerFragment newInstance(HeroPowerOrigin heroPowerOrigin) {
        PickPowerFragment fragment = new PickPowerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HERO_POWER_ORIGIN, heroPowerOrigin.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHeroPowerOrigin = HeroPowerOrigin.valueOf(getArguments().getString(ARG_HERO_POWER_ORIGIN));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pick_power, container, false);
        ButterKnife.bind(this, rootView);
        initUI();
        return rootView;
    }

    private void toggleButtonStyle(Button button, boolean enabled) {
        button.setEnabled(enabled);
        if (!enabled) {
            button.getBackground().setAlpha(128);
            button.setTextColor(disabledButtonTextColor);
        } else {
            button.getBackground().setAlpha(255);
            button.setTextColor(enabledButtonTextColor);
        }
    }

    private void initUI() {

        btnShowBackStory.setText(showBackStory);
        btnShowBackStory.setEnabled(false);
        btnShowBackStory.getBackground().setAlpha(128);

        btnTurtlePower.setText(HeroPower.TurtlePower.toString());
        btnLightning.setText(HeroPower.Lightning.toString());
        btnFlight.setText(HeroPower.Flight.toString());
        btnWebSlinging.setText(HeroPower.WebSlinging.toString());
        btnLaserVision.setText(HeroPower.LaserVision.toString());
        btnSuperStrength.setText(HeroPower.SuperStrength.toString());

        setupPowerButtons();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RequestToLoadBackStoryFragmentListener) {
            mListener = (RequestToLoadBackStoryFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @OnClick(R.id.btnShowBackStory)
    public void onClickShowBackStory(View view) {
        if (mListener != null) {
            mListener.onRequestToLoadBackStoryFragment(mHeroPowerOrigin, mHeroPower);
        }
    }

    @OnClick({R.id.btnTurtlePower, R.id.btnLightning, R.id.btnFlight, R.id.btnWebSlinging, R.id.btnLaserVision, R.id.btnSuperStrength})
    public void onClickHeroPowerButtons(View view) {
        if (view.getId() == R.id.btnTurtlePower) {
            mHeroPower = HeroPower.TurtlePower;
        } else if (view.getId() == R.id.btnLightning) {
            mHeroPower = HeroPower.Lightning;
        } else if (view.getId() == R.id.btnFlight) {
            mHeroPower = HeroPower.Flight;
        } else if (view.getId() == R.id.btnWebSlinging) {
            mHeroPower = HeroPower.WebSlinging;
        } else if (view.getId() == R.id.btnLaserVision) {
            mHeroPower = HeroPower.LaserVision;
        } else {
            mHeroPower = HeroPower.SuperStrength;
        }
        setupPowerButtons();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface RequestToLoadBackStoryFragmentListener {
        void onRequestToLoadBackStoryFragment(HeroPowerOrigin heroPowerOrigin, HeroPower heroPower);
    }

    private void resetPowerButtons() {
        btnTurtlePower.setCompoundDrawablesWithIntrinsicBounds(drTurtlePower, null, drUnSelected, null);
        btnLightning.setCompoundDrawablesWithIntrinsicBounds(drLightning, null, drUnSelected, null);
        btnFlight.setCompoundDrawablesWithIntrinsicBounds(drFlight, null, drUnSelected, null);
        btnWebSlinging.setCompoundDrawablesWithIntrinsicBounds(drWebSlinging, null, drUnSelected, null);
        btnLaserVision.setCompoundDrawablesWithIntrinsicBounds(drLaserVision, null, drUnSelected, null);
        btnSuperStrength.setCompoundDrawablesWithIntrinsicBounds(drSuperStrength, null, drUnSelected, null);

        switch (mHeroPowerOrigin) {
            case CameByAccident:
                toggleButtonStyle(btnTurtlePower, false);
                toggleButtonStyle(btnLightning, false);
                break;
            case GeneticMutation:
                toggleButtonStyle(btnFlight, false);
                toggleButtonStyle(btnWebSlinging, false);
                break;
            case BornWithThem:
                toggleButtonStyle(btnLaserVision, false);
                toggleButtonStyle(btnSuperStrength, false);
                break;
            case None:
                break;
        }
    }

    private void setupPowerButtons() {
        resetPowerButtons();
        if (!mHeroPower.equals(HeroPower.None)) {
            btnShowBackStory.setEnabled(true);
            btnShowBackStory.getBackground().setAlpha(255);
            if (mHeroPower.equals(HeroPower.TurtlePower)) {
                btnTurtlePower.setCompoundDrawablesWithIntrinsicBounds(drTurtlePower, null, drSelected, null);
            } else if (mHeroPower.equals(HeroPower.Lightning)) {
                btnLightning.setCompoundDrawablesWithIntrinsicBounds(drLightning, null, drSelected, null);
            } else if (mHeroPower.equals(HeroPower.Flight)) {
                btnFlight.setCompoundDrawablesWithIntrinsicBounds(drFlight, null, drSelected, null);
            } else if (mHeroPower.equals(HeroPower.WebSlinging)) {
                btnWebSlinging.setCompoundDrawablesWithIntrinsicBounds(drWebSlinging, null, drSelected, null);
            } else if (mHeroPower.equals(HeroPower.LaserVision)) {
                btnLaserVision.setCompoundDrawablesWithIntrinsicBounds(drLaserVision, null, drSelected, null);
            } else {
                btnSuperStrength.setCompoundDrawablesWithIntrinsicBounds(drSuperStrength, null, drSelected, null);
            }
        }
    }
}
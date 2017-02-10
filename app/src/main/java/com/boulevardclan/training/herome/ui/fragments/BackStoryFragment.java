package com.boulevardclan.training.herome.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.boulevardclan.training.herome.R;
import com.boulevardclan.training.herome.ui.common.enums.HeroPower;
import com.boulevardclan.training.herome.ui.common.enums.HeroPowerOrigin;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackStoryFragment extends Fragment {

    private static final String LOG_TAG = "BackStoryFragment";

    private static final String ARG_HERO_POWER_ORIGIN = "HeroPowerOrigin";
    private static final String ARG_HERO_POWER = "HeroPower";

    private HeroPowerOrigin mHeroPowerOrigin;
    private HeroPower mHeroPower;

    private RequestToLoadStartupFragmentListener mListener;
    private Context mParentActivity;

    @BindView(R.id.tvSubHeading)
    TextView tvSubHeading;

    @BindView(R.id.tvPowerOrigin)
    TextView tvPowerOrigin;
    @BindView(R.id.btnPowerOrigin)
    Button btnPowerOrigin;
    @BindView(R.id.tvPower)
    TextView tvPower;
    @BindView(R.id.btnPower)
    Button btnPower;
    @BindView(R.id.btnStartOver)
    Button btnStartOver;

    @BindDrawable(R.drawable.lightning)
    Drawable drCameByAccident;
    @BindDrawable(R.drawable.atomic)
    Drawable drGeneticMutation;
    @BindDrawable(R.drawable.rocket)
    Drawable drBornWithThem;

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

    @BindString(R.string.start_over)
    String startOver;


    public BackStoryFragment() {
        // Required empty public constructor
    }

    public static BackStoryFragment newInstance(HeroPowerOrigin mHeroPowerOrigin, HeroPower heroPower) {
        BackStoryFragment fragment = new BackStoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HERO_POWER_ORIGIN, mHeroPowerOrigin.name());
        args.putString(ARG_HERO_POWER, heroPower.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHeroPowerOrigin = HeroPowerOrigin.valueOf(getArguments().getString(ARG_HERO_POWER_ORIGIN));
            mHeroPower = HeroPower.valueOf(getArguments().getString(ARG_HERO_POWER));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_backstory, container, false);
        ButterKnife.bind(this, rootView);
        initUI();
        return rootView;
    }

    private void initUI() {

        btnStartOver.setText(startOver);

        Drawable leftDrawable;
        String buttonText;
        if (mHeroPowerOrigin.equals(HeroPowerOrigin.CameByAccident)) {
            leftDrawable = drCameByAccident;
            buttonText = HeroPowerOrigin.CameByAccident.getDisplayName(mParentActivity);
        } else if (mHeroPowerOrigin.equals(HeroPowerOrigin.BornWithThem)) {
            leftDrawable = drBornWithThem;
            buttonText = HeroPowerOrigin.BornWithThem.getDisplayName(mParentActivity);
        } else {
            leftDrawable = drGeneticMutation;
            buttonText = HeroPowerOrigin.GeneticMutation.getDisplayName(mParentActivity);
        }
        btnPowerOrigin.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, drUnSelected, null);
        btnPowerOrigin.setText(buttonText);

        if (mHeroPower.equals(HeroPower.TurtlePower)) {
            leftDrawable = drTurtlePower;
            buttonText = HeroPower.TurtlePower.getDisplayName(mParentActivity);
        } else if (mHeroPower.equals(HeroPower.Lightning)) {
            leftDrawable = drLightning;
            buttonText = HeroPower.Lightning.getDisplayName(mParentActivity);
        } else if (mHeroPower.equals(HeroPower.Flight)) {
            leftDrawable = drFlight;
            buttonText = HeroPower.Flight.getDisplayName(mParentActivity);
        } else if (mHeroPower.equals(HeroPower.WebSlinging)) {
            leftDrawable = drWebSlinging;
            buttonText = HeroPower.WebSlinging.getDisplayName(mParentActivity);
        } else if (mHeroPower.equals(HeroPower.LaserVision)) {
            leftDrawable = drLaserVision;
            buttonText = HeroPower.LaserVision.getDisplayName(mParentActivity);
        } else {
            leftDrawable = drSuperStrength;
            buttonText = HeroPower.SuperStrength.getDisplayName(mParentActivity);
        }
        btnPower.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, drUnSelected, null);
        btnPower.setText(buttonText);
    }

    @OnClick(R.id.btnStartOver)
    public void onClickStartOver(View view) {
        if (mListener != null) {
            mListener.onRequestToLoadStartupFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParentActivity = getActivity();
        if (context instanceof RequestToLoadStartupFragmentListener) {
            mListener = (RequestToLoadStartupFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mParentActivity = null;
    }

    public interface RequestToLoadStartupFragmentListener {
        void onRequestToLoadStartupFragment();
    }
}
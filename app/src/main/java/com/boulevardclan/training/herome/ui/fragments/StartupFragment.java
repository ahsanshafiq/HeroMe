package com.boulevardclan.training.herome.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.boulevardclan.training.herome.R;
import com.boulevardclan.training.herome.ui.common.enums.HeroPowerOrigin;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartupFragment extends Fragment {

    private static final String LOG_TAG = "StartupFragment";
    private static final String ARG_HERO_POWER_ORIGIN = "HeroPowerOrigin";

    private RequestToLoadPickAPowerFragmentListener mListener;

    public void setmHeroPowerOrigin(HeroPowerOrigin mHeroPowerOrigin) {
        this.mHeroPowerOrigin = mHeroPowerOrigin;
    }

    private HeroPowerOrigin mHeroPowerOrigin;

    @BindView(R.id.btnGeneticMutation)
    Button btnGeneticMutation;
    @BindView(R.id.btnCameByAccident)
    Button btnCameByAccident;
    @BindView(R.id.btnBornWithThem)
    Button btnBornWithThem;

    @BindView(R.id.btnChooseOne)
    Button btnChooseOne;

    @BindDrawable(R.drawable.lightning)
    Drawable drCameByAccident;
    @BindDrawable(R.drawable.atomic)
    Drawable drGeneticMutation;
    @BindDrawable(R.drawable.rocket)
    Drawable drBornWithThem;

    @BindDrawable(R.drawable.item_selected)
    Drawable drSelected;
    @BindDrawable(R.drawable.item_unselected)
    Drawable drUnSelected;

    @BindString(R.string.choose_powers)
    String choosePowers;

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(LOG_TAG, "StartupFragment.onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "StartupFragment.onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }*/

    public StartupFragment() {
        // Required empty public constructor
        mHeroPowerOrigin = HeroPowerOrigin.None;
    }

    public static StartupFragment newInstance() {
        StartupFragment fragment = new StartupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "StartupFragment.onCreateView()");
        View rootView = inflater.inflate(R.layout.fragment_startup, container, false);
        ButterKnife.bind(this, rootView);
        initUI();
        return rootView;
    }

    private void initUI() {
        btnChooseOne.setText(choosePowers);
        btnChooseOne.setEnabled(false);
        btnChooseOne.getBackground().setAlpha(128);

        btnCameByAccident.setText(HeroPowerOrigin.CameByAccident.toString());
        btnGeneticMutation.setText(HeroPowerOrigin.GeneticMutation.toString());
        btnBornWithThem.setText(HeroPowerOrigin.BornWithThem.toString());

        setupPowerOriginButtons();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RequestToLoadPickAPowerFragmentListener) {
            mListener = (RequestToLoadPickAPowerFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement requestToLoadPickAPowerFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface RequestToLoadPickAPowerFragmentListener {
        void onRequestToLoadPickAPowerFragment(HeroPowerOrigin heroPowerOrigin);
    }

    @OnClick(R.id.btnCameByAccident)
    public void onClickCameByAccident(View view) {
        mHeroPowerOrigin = HeroPowerOrigin.CameByAccident;
        setupPowerOriginButtons();
    }
    @OnClick(R.id.btnGeneticMutation)
    public void onClickGeneticMutation(View view) {
        mHeroPowerOrigin = HeroPowerOrigin.GeneticMutation;
        setupPowerOriginButtons();
    }
    @OnClick(R.id.btnBornWithThem)
    public void onClickBornWithThem(View view) {
        mHeroPowerOrigin = HeroPowerOrigin.BornWithThem;
        setupPowerOriginButtons();
    }

    @OnClick(R.id.btnChooseOne)
    public void onClickChooseOne(View view) {
        if (mListener != null) {
            mListener.onRequestToLoadPickAPowerFragment(mHeroPowerOrigin);
        }
    }

    private void resetPowerOriginButtons(){
        btnBornWithThem.setCompoundDrawablesWithIntrinsicBounds(drBornWithThem, null, drUnSelected, null);
        btnGeneticMutation.setCompoundDrawablesWithIntrinsicBounds(drGeneticMutation, null, drUnSelected, null);
        btnCameByAccident.setCompoundDrawablesWithIntrinsicBounds(drCameByAccident, null, drUnSelected, null);
    }

    public void setupPowerOriginButtons() {
        resetPowerOriginButtons();
        if(!mHeroPowerOrigin.equals(HeroPowerOrigin.None)){
            btnChooseOne.setEnabled(true);
            btnChooseOne.getBackground().setAlpha(255);
            if (mHeroPowerOrigin.equals(HeroPowerOrigin.CameByAccident)) {
                btnCameByAccident.setCompoundDrawablesWithIntrinsicBounds(drCameByAccident, null, drSelected, null);
            }else if (mHeroPowerOrigin.equals(HeroPowerOrigin.GeneticMutation)) {
                btnGeneticMutation.setCompoundDrawablesWithIntrinsicBounds(drGeneticMutation, null, drSelected, null);
            } else {
                btnBornWithThem.setCompoundDrawablesWithIntrinsicBounds(drBornWithThem, null, drSelected, null);
            }
        }
    }
}
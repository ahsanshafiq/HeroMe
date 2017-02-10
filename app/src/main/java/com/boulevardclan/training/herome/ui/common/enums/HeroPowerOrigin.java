package com.boulevardclan.training.herome.ui.common.enums;

/**
 * Created by Ahsan on 08-Feb-17.
 */

public enum HeroPowerOrigin {
    None("None"),
    GeneticMutation("Genetic Mutation"),
    CameByAccident("Came By Accident"),
    BornWithThem("Born With Them");

    private String text;

    HeroPowerOrigin(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

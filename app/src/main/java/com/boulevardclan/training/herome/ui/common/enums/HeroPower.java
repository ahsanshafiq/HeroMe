package com.boulevardclan.training.herome.ui.common.enums;

/**
 * Created by Ahsan on 08-Feb-17.
 */

public enum HeroPower {
    None("None"),
    TurtlePower("Turtle Power"),
    Lightning("Lightning"),
    Flight("Flight"),
    WebSlinging("Web Slinging"),
    LaserVision("Laser Vision"),
    SuperStrength("Super Strength");

    private String text;

    HeroPower(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
package com.boulevardclan.training.herome.ui.common.enums;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

/**
 * Created by Ahsan on 08-Feb-17.
 */

public enum HeroPowerOrigin {
    None,
    GeneticMutation,
    CameByAccident,
    BornWithThem;

    public String getDisplayName(@NonNull Context context){
        Resources resources = context.getResources();
        return resources.getString(resources.getIdentifier(this.name(), "string", context.getPackageName()));
    }
}

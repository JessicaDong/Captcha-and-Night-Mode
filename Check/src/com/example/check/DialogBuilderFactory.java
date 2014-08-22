
package com.example.check;

import android.app.AlertDialog;
import android.content.Context;

public class DialogBuilderFactory {

    public static AlertDialog.Builder createBuilder(Context context) {
        if (NightModeUtils.getDayNightMode(context) == NightModeUtils.THEME_SUN) {
            return new AlertDialog.Builder(context);
        } else {
            return new AlertDialog.Builder(context, R.style.NightDialog);
        }
    }
}

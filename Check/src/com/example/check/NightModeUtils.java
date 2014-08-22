
package com.example.check;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

public class NightModeUtils {
    public final static int THEME_SUN = 1;

    public final static int THEME_NIGHT = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity
     * of the same type.
     */
    public static void changeToTheme(Activity activity) {
    	int theme1=getDayNightMode(activity);
        int theme =( theme1 == THEME_SUN ? THEME_NIGHT : THEME_SUN);
        setDayNightMode(activity, theme);

        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity) {
        int theme = getDayNightMode(activity);
        switch (theme) {
            case THEME_SUN:
                activity.setTheme(R.style.AppSunTheme);
                break;
            case THEME_NIGHT:
                activity.setTheme(R.style.AppNightTheme);
                break;
            default:
                break;
        }
    }

    public static void setBackGroundColor(Context context, View view, int theme) {
        int color = context.getResources().getColor(
                theme == THEME_SUN ? R.color.light_color : R.color.night_color);
        view.setBackgroundColor(color);
    }

    public static void setTextColor(Context context, View view, int theme) {
        int color = context.getResources().getColor(
                theme == THEME_SUN ? R.color.night_color : R.color.light_color);
        TextView textView = (TextView)view;
        textView.setTextColor(color);
    }

    public static int getSwitchDayNightMode(Context context) {
        int mode = getDayNightMode(context);
        return mode == THEME_SUN ? THEME_NIGHT : THEME_SUN;
    }

    public static void setDayNightMode(Context context, int mode) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt("SUN_NIGHT_MODE", mode);
        sharedPreferencesEditor.apply();
    }

    public static int getDayNightMode(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt("SUN_NIGHT_MODE", THEME_SUN);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("NightModeDemo", Context.MODE_APPEND);
    }
}

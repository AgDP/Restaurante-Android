package com.dotta.agustin.restaurante.util;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;

import com.dotta.agustin.restaurante.R;


// Una implementación típica para nuestro "modo noche"
public class DefaultThemeDecorator {
    private Activity mActivity;
    private boolean mNightMode;

    public DefaultThemeDecorator(Activity activity) {
        mActivity = activity;
        mNightMode = PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(activity.getString(R.string.night_checkbox), false);
    }

    public void setDefaultTheme() {
        if (PreferenceManager.getDefaultSharedPreferences(mActivity).getBoolean(mActivity.getString(R.string.night_checkbox), false)) {
            mActivity.setTheme(R.style.AppTheme_Night);
        } else {
            mActivity.setTheme(R.style.AppTheme);
        }
    }

    public boolean checkThemeChanges() {
        boolean newNightMode = PreferenceManager.getDefaultSharedPreferences(mActivity)
                .getBoolean(mActivity.getString(R.string.night_checkbox), false);
        if (newNightMode != mNightMode) {
            mNightMode = newNightMode;
            mActivity.recreate();
            return true;
        }

        return false;
    }

    public void decorateSnackbar(Snackbar snackbar) {
        DefaultThemeDecorator.decorateSnackbar(snackbar, mActivity);
    }

    public static void decorateSnackbar(Snackbar snackbar, Activity activity) {
        boolean nightMode = PreferenceManager.getDefaultSharedPreferences(activity)
                .getBoolean(activity.getString(R.string.night_checkbox), false);
        snackbar.getView().setBackgroundResource(
                nightMode?
                R.color.snackBarNightBackground :
                R.color.snackBarBackground);
    }
}
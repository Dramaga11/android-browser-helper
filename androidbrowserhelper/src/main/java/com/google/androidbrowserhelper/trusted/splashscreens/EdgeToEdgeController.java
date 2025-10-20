package com.google.androidbrowserhelper.trusted.splashscreens;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.insets.ColorProtection;
import androidx.core.view.insets.ProtectionLayout;

import com.google.common.collect.ImmutableList;

public class EdgeToEdgeController {
    private Activity mActivity;
    private ColorProtection statusBarProtection;
    private ColorProtection navigationBarProtection;

    public EdgeToEdgeController(Activity activity, @ColorInt int defaultColor) {
        mActivity = activity;
        statusBarProtection = new ColorProtection(WindowInsetsCompat.Side.TOP, defaultColor);
        navigationBarProtection = new ColorProtection(WindowInsetsCompat.Side.BOTTOM, defaultColor);
    }

    public FrameLayout getWrapperView(View originalView) {
        FrameLayout rootView = new FrameLayout(mActivity);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        ProtectionLayout protectionLayout = new ProtectionLayout(mActivity,
                ImmutableList.of(statusBarProtection, navigationBarProtection));
        rootView.addView(protectionLayout);
        protectionLayout.addView(originalView);
        protectionLayout.setVisibility(View.VISIBLE);
        return rootView;
    }

    public void setStatusBarColor(@ColorInt int color) {
        statusBarProtection.setColor(color);
    }

    public void setNavigationBarColor(@ColorInt int color) {
        navigationBarProtection.setColor(color);
    }
}

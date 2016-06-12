package com.github.okbuilds.okbuck.example;

import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.facebook.buck.android.support.exopackage.ExopackageApplication;
import com.github.okbuilds.okbuck.example.common.Calc;
import com.github.okbuilds.okbuck.example.common.CalcMonitor;

public class AppShell extends ExopackageApplication {

    private static final String APP_NAME = "com.github.okbuilds.okbuck.example.MyApp";
    private final boolean mIsExopackageMode;

    public AppShell() {
        super(APP_NAME, BuildConfig.EXOPACKAGE_FLAGS != 0);
        mIsExopackageMode = BuildConfig.EXOPACKAGE_FLAGS != 0;
    }

    @Override
    protected void onBaseContextAttached() {
        if (!mIsExopackageMode) {
            MultiDex.install(this);
        }
        Calc calc = new Calc(new CalcMonitor(this));
        Toast.makeText(this, "calc: " + calc.add(1, 2), Toast.LENGTH_SHORT).show();
    }
}
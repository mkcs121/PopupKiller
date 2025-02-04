package com.popup.killer;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // Hook for com.hktv.android.hktvmall
        if (lpparam.packageName.equals("com.hktv.android.hktvmall")) {
            try {
                Class<?> MainActivity = lpparam.classLoader.loadClass(
                        "com.hktv.android.hktvmall.ui.activities.MainActivity"
                );

                // Hook resumeSplashAd
                XposedBridge.hookAllMethods(MainActivity, "resumeSplashAd", XC_MethodReplacement.DO_NOTHING);


                // Hook saveLastSeenCategorySplash
                XposedBridge.hookAllMethods(MainActivity, "saveLastSeenCategorySplash", XC_MethodReplacement.DO_NOTHING);

            } catch (Exception e) {
                XposedBridge.log("HKTV Mall hook error: " + e);
            }
        }
        // Hook for com.asw.moneyback
        else if (lpparam.packageName.equals("com.asw.moneyback")) {
            try {
                Class<?> FullScreenADAlert = lpparam.classLoader.loadClass(
                        "com.parknshop.moneyback.view.FullScreenADAlert"
                );

                // Hook popup method
                XposedBridge.hookAllMethods(FullScreenADAlert, "popup", XC_MethodReplacement.DO_NOTHING);

            } catch (Exception e) {
                XposedBridge.log("Moneyback hook error: " + e);
            }
        }
    }
}
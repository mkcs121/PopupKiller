package com.popup.killer;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
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
        // Hook for com.mem.MacaoLife
        else if (lpparam.packageName.equals("com.mem.MacaoLife")) {
            try {
                Class<?> FullScreenADAlert = lpparam.classLoader.loadClass(
                        "com.mem.life.ui.launch.fragment.LaunchAdsFragment"
                );

                // Hook setLaunchAd method
                XposedBridge.hookAllMethods(FullScreenADAlert, "setLaunchAd", XC_MethodReplacement.DO_NOTHING);

                // Hook checkNeedShow method
                XposedHelpers.findAndHookMethod(
                        "com.mem.life.ui.home.popup.HomeAdsFragment", // Full class name
                        lpparam.classLoader,
                        "checkNeedShow",                // Method name to hook
                        // Add parameter types if the method takes any, e.g., String.class, int.class
                        new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                                // Force the method to return false
                                param.setResult(false);
                            }
                        }
                );

            } catch (Exception e) {
                XposedBridge.log("MacaoLife hook error: " + e);
            }
        }
    }
}
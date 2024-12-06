package com.popup.killer;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        String pkgName = loadPackageParam.packageName;
        switch (pkgName) {
            case "com.hktv.android.hktvmall":
                Class a = loadPackageParam.classLoader.loadClass("com.hktv.android.hktvmall.ui.activities.MainActivity");
                XposedBridge.hookAllMethods(a, "resumeSplashAd", new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        return "";
                    }
                });
                XposedBridge.hookAllMethods(a, "saveLastSeenCategorySplash", new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        return "";
                    }
                });
                break;
            case "com.asw.moneyback":
                Class b = loadPackageParam.classLoader.loadClass("com.parknshop.moneyback.view.FullScreenADAlert");
                XposedBridge.hookAllMethods(b, "popup", new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        return "";
                    }
                });
                break;
            default:
                return;
        }
    }
}
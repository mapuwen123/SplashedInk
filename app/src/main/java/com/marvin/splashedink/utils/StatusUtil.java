package com.marvin.splashedink.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.R.attr.type;

/**
 * Created by Administrator on 2017/7/12.
 */

public class StatusUtil {
    private static StatusUtil statusUtil = null;

    private Activity activity;

    private enum SystemName {
        OTHER,
        MIUI,
        FLYME
    }

    public StatusUtil(Activity activity) {
        this.activity = activity;
    }

    public static synchronized StatusUtil getInstance(Activity activity) {
        if (statusUtil == null) {
            statusUtil = new StatusUtil(activity);
        }
        return statusUtil;
    }

    public static String getSystemBrandName() {
        return android.os.Build.BRAND;
    }

    public static SystemName getSystemType() {
        SystemName type;
        if (getSystemBrandName().equalsIgnoreCase("xiaomi")) {
            type = SystemName.MIUI;
        } else if (getSystemBrandName().equalsIgnoreCase("meizu")) {
            type = SystemName.FLYME;
        } else {
            type = SystemName.OTHER;
        }
        return type;
    }

    public void setStatus(SystemName type) {
        switch (type) {
            case OTHER:
                setAndroidMStatus(this.activity, true);
                break;
            case MIUI:
                setMIUIStatus(this.activity, true);
                break;
            case FLYME:
                setFlyMeStatus(this.activity, true);
                break;
        }
    }

    private boolean setAndroidMStatus(Activity activity, boolean isFontColorDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isFontColorDark) {
                // 沉浸式
                //                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //非沉浸式
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                //非沉浸式
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            return true;
        }
        return false;
    }

    private boolean setMIUIStatus(Activity activity, boolean isFontColorDark) {
        Window window = activity.getWindow();
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (isFontColorDark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private boolean setFlyMeStatus(Activity activity, boolean isFontColorDark) {
        Window window = activity.getWindow();
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (isFontColorDark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

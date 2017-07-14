package com.marvin.splashedink.common;

import com.marvin.splashedink.utils.SDCardUtil;

/**
 * Created by Administrator on 2017/6/10.
 */

public class BuildConfig {
    public static final String AppDir = SDCardUtil.getSDCardPath() + "/SplashedInk";

    public static final String UM_App_Key = "59681991e88bad15ac00126b";
    public static final String CHANNEL = "OFFICIAL";

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    public static boolean INTERNER = true;

//    public static String BuglyAppID = "f4fba8d324";
}

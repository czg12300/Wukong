package com.jake.wukong.hook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

/**
 * 描述:
 *
 * @author jakechen
 * @since 2016/9/27 17:25
 */
public class DexClassLoaderHook {
    private static String TAG = DexClassLoaderHook.class.getSimpleName();

    public static void useDexClassLoader(Context context, ActivityInfo activityInfo) {
        String div = System.getProperty("path.separator");
        String activityName = activityInfo.packageName + activityInfo.name;
        String dexPath = activityInfo.applicationInfo.sourceDir;
        //目标类所在的apk或者jar的路径，class loader会通过这个路径来加载目标类文件
        String dexOutputDir = context.getApplicationInfo().dataDir;
        //由于dex文件是包含在apk或者jar文件中的,所以在加载class之前就需要先将dex文件解压出来，dexOutputDir为解压路径
        String libPath = activityInfo.applicationInfo.nativeLibraryDir;
        //目标类可能使用的c或者c++的库文件的存放路径

        Log.i(TAG, "div:" + div + "   " + "packageName:" + activityName + "   " + "dexPath:" + dexPath + "   " +
                "dexOutputDir:" + dexOutputDir + "   " + "libPath:" + libPath);

        DexClassLoader dcLoader = new DexClassLoader(dexPath, dexOutputDir, libPath, DexClassLoaderHook.class.getClassLoader());
        try {
            Class<?> clazz = dcLoader.loadClass(activityName);
            Activity activity = (Activity) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "ClassNotFoundException");
        } catch (InstantiationException e) {
            Log.i(TAG, "InstantiationException");
        } catch (IllegalAccessException e) {
            Log.i(TAG, "IllegalAccessException");
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "IllegalArgumentException");
        }
    }
}

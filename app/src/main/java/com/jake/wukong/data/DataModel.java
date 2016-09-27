package com.jake.wukong.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description：
 *
 * @author jake
 * @since 2016/9/26 23:27
 */


public class DataModel {
    public static List<ApplicationInfo> getAllAppInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> list = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<ApplicationInfo> temp = new ArrayList<>();
        for (ApplicationInfo info : list) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                temp.add(info);
            }
        }
        list = temp;
        Collections.sort(list, new ApplicationInfo.DisplayNameComparator(pm));
        return list;
    }

    public static List<ApplicationInfo> getDoubleOpenAppInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> list = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<ApplicationInfo> temp = new ArrayList<>();
        for (ApplicationInfo info : list) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                if (!TextUtils.isEmpty(SharedPreferencesCache.getInstance().get(info.packageName))) {
                    temp.add(info);
                }
            }
        }
        list = temp;
        Collections.sort(list, new ApplicationInfo.DisplayNameComparator(pm));
        return list;
    }

    public static List<ApplicationInfo> getSelectAppInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> list = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<ApplicationInfo> temp = new ArrayList<>();
        for (ApplicationInfo info : list) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                if (TextUtils.isEmpty(SharedPreferencesCache.getInstance().get(info.packageName))) {
                    temp.add(info);
                }
            }
        }
        list = temp;
        Collections.sort(list, new ApplicationInfo.DisplayNameComparator(pm));
        return list;
    }

    public static String appInfoToJson(ApplicationInfo info) {
        if (info != null) {
            JSONObject object = new JSONObject();
            try {
                object.put("packageName", info.packageName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return object.toString();
        }
        return null;
    }
}

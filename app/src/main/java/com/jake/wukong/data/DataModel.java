package com.jake.wukong.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

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
}

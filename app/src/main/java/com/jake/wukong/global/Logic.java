package com.jake.wukong.global;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * 描述:
 *
 * @author jakechen
 * @since 2016/9/27 16:00
 */
public class Logic {
    public static void addShortcut(Context context, ResolveInfo info, String shortCutName) {
        // 安装的Intent
        PackageManager pm = context.getPackageManager();
        // 快捷图标是允许重复
        Drawable drawable = info.loadIcon(pm);
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bitmap = bd.getBitmap();
        }
        addShortcut(context, info.activityInfo.packageName, info.activityInfo.name, shortCutName, bitmap);
    }

    public static void addShortcut(Context context, String pks, String actName, String shortCutName, Bitmap bitmap) {
        Intent itShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        itShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, createResultIntent(pks, actName));
        // 快捷名称
        itShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
        // 快捷图标是允许重复
        itShortcut.putExtra("duplicate", false);
        itShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
        // 发送广播
        context.sendBroadcast(itShortcut);
    }

    private static Intent createResultIntent(String packageName, String activityName) {
        Intent itResult = new Intent();
        itResult.setComponent(new ComponentName(packageName, activityName));
        itResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return itResult;
    }

}

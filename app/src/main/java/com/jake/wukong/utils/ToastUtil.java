package com.jake.wukong.utils;

import android.widget.Toast;

import com.jake.wukong.global.AppController;
import com.jake.wukong.global.ResourceController;

/**
 * description：common toast style
 *
 * @author jake
 * @since 2016/9/26 23:35
 */


public class ToastUtil {
    public static void show(CharSequence charSequence) {
        Toast.makeText(AppController.getInstance().getContext(), charSequence, Toast.LENGTH_SHORT).show();
    }

    public static void show(int resId) {
        Toast.makeText(AppController.getInstance().getContext(), ResourceController.getInstance().getString(resId), Toast.LENGTH_SHORT).show();
    }
}

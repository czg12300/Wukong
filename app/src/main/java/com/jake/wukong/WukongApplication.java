package com.jake.wukong;

import android.app.Application;
import android.content.Context;

import com.jake.wukong.data.SharedPreferencesCache;
import com.jake.wukong.global.AppController;
import com.jake.wukong.global.ResourceController;
import com.morgoo.droidplugin.PluginHelper;

/**
 * 描述：程序入口
 *
 * @author jake
 * @since 2016/9/26 21:30
 */


public class WukongApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppController.getInstance().install(this);
        //这里必须在super.onCreate方法之后，顺序不能变
        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
    }
    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }
}

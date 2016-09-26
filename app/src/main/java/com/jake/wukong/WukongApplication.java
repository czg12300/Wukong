package com.jake.wukong;

import android.app.Application;

import com.jake.wukong.data.SharedPreferencesCache;
import com.jake.wukong.global.AppController;
import com.jake.wukong.global.ResourceController;

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
    }
}

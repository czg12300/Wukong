package com.jake.wukong.global;

import android.content.Context;

import com.jake.wukong.data.SharedPreferencesCache;

/**
 * 描述：全局变量
 *
 * @author jakechen
 * @since 2016/9/16 13:06
 */

public class AppController {
    private Context mContext;

    private AppController() {
    }

    public void install(Context context) {
        mContext = context;
        ResourceController.getInstance().install(context);
        SharedPreferencesCache.getInstance().install(context);
    }

    public Context getContext() {
        return mContext;
    }

    public static AppController getInstance() {
        return InstanceBuilder.instance;
    }

    private static class InstanceBuilder {
        protected static AppController instance = new AppController();
    }
}

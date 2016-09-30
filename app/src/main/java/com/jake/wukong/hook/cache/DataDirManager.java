package com.jake.wukong.hook.cache;

import android.content.Context;

import java.io.File;

/**
 * 描述:应用的缓存数据管理
 *
 * @author jakechen
 * @since 2016/9/30 10:19
 */
public class DataDirManager {
    private File mDataRootDir;
    private Context mContext;

    private DataDirManager() {
    }

    public static DataDirManager getInstance() {
        return InstanceBuild.instance;
    }

    static class InstanceBuild {
        static DataDirManager instance = new DataDirManager();
    }

    public void install(Context context) {
        mContext = context;
        mDataRootDir = context.getCacheDir();
    }

    public File getDataDir(String packageName) {
        File dir = new File(mDataRootDir, packageName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }


}

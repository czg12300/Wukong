package com.jake.wukong.hook.proxy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 描述:代理的activity
 *
 * @author jakechen
 * @since 2016/9/29 16:02
 */
public class ProxyActivity extends Activity {
    public static void start(Context context, ComponentName target) {
        if (context != null) {
            Intent it = new Intent();
            it.setComponent(target);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(it);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static class P0 extends ProxyActivity {
    }

    public static class P1 extends ProxyActivity {
    }

    public static class P2 extends ProxyActivity {
    }

    public static class P3 extends ProxyActivity {
    }

    public static class P4 extends ProxyActivity {
    }

    public static class P5 extends ProxyActivity {
    }


}

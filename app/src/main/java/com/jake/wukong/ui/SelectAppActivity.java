package com.jake.wukong.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jake.wukong.R;
import com.jake.wukong.data.DataModel;

/**
 * 描述:选择器
 *
 * @author jakechen
 * @since 2016/9/27 14:11
 */
public class SelectAppActivity extends AppCompatActivity {
    private ListView listView;
    private SelectAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加应用");
        setContentView(R.layout.activity_select);
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new SelectAdapter(this, new SelectAdapter.IClickListener() {
            @Override
            public void clickAdd(ApplicationInfo applicationInfo) {
                if (applicationInfo != null) {
                    Intent it = new Intent();
                    it.putExtra("appinfo", applicationInfo);
                    setResult(RESULT_OK, it);
                    finish();
                }
            }
        });
        mAdapter.add(DataModel.getSelectAppInfo(this));
        listView.setAdapter(mAdapter);
    }

    public static void startActivityForResult(Context context, int requestCode) {
        if (context != null && context instanceof Activity) {
            Intent it = new Intent(context, SelectAppActivity.class);
            ((Activity) context).startActivityForResult(it, requestCode);
        }
    }
}

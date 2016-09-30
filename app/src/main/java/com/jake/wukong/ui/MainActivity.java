package com.jake.wukong.ui;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jake.wukong.R;
import com.jake.wukong.data.DataModel;
import com.jake.wukong.data.SharedPreferencesCache;
import com.jake.wukong.global.Logic;
import com.jake.wukong.hook.proxy.ProxyActivity;
import com.jake.wukong.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private GridView gridView;
    private LauncherAdapter mAdapter;
    private static final int REQUEST_CODE = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("应用双开");
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        gridView = (GridView) findViewById(R.id.gridView);
        mAdapter = new LauncherAdapter(this);
        mAdapter.add(DataModel.getResolveInfoLists(this));
        gridView.setAdapter(mAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectAppActivity.startActivityForResult(v.getContext(), REQUEST_CODE);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo info = mAdapter.getItem(position);
                // 应用的包名
                String pkg = info.activityInfo.packageName;
                // 应用的主Activity
                String cls = info.activityInfo.name;
                ComponentName componentName = new ComponentName(pkg, cls);
                ProxyActivity.P0.start(getApplicationContext(), componentName);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showOptDialog(mAdapter.getItem(position));
                return true;
            }
        });
    }

    private void showOptDialog(final ResolveInfo info) {
        if (info == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setMessage("请选择以下操作"); //设置内容
        builder.setPositiveButton("创建快捷方式", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Logic.addShortcut(MainActivity.this, info, "+" + info.loadLabel(getPackageManager()));
            }
        });
        builder.setNegativeButton("删除", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SharedPreferencesCache.getInstance().remove(info.resolvePackageName);
                mAdapter.del(info);
                mAdapter.notifyDataSetChanged();
            }
        });

        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ApplicationInfo info = data.getParcelableExtra("appinfo");
            if (info != null) {
                SharedPreferencesCache.getInstance().put(info.packageName, DataModel.appInfoToJson(info));
//                mAdapter.add(info);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }
}

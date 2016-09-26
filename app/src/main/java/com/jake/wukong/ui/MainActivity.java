package com.jake.wukong.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.jake.wukong.R;
import com.jake.wukong.data.DataModel;
import com.jake.wukong.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private GridView gridView;
    private LauncherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        gridView = (GridView) findViewById(R.id.gridView);
        mAdapter = new LauncherAdapter(this);
        mAdapter.add(DataModel.getAllAppInfo(this));
        gridView.setAdapter(mAdapter);
        floatingActionButton.setRippleColor(getResources().getColor(R.color.colorAccent));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("添加应用l");
            }
        });
    }
}

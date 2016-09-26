package com.jake.wukong.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jake.wukong.R;
import com.jake.wukong.global.ResourceController;

/**
 * description：
 *
 * @author jake
 * @since 2016/9/26 23:45
 */


public class LauncherAdapter extends BaseListAdapter<ApplicationInfo,LauncherAdapter.ViewHolder> {

    public LauncherAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApplicationInfo info = getItem(position);
        if (info != null) {
            holder.ivIcon.setImageResource(info.icon);
            holder.tvName.setText(info.name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LauncherAdapter.ViewHolder(ResourceController.getInstance().inflate(R.layout.item_launcher));
    }

    static class ViewHolder extends BaseListAdapter.ViewHolder {
        ImageView ivIcon;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) findViewById(R.id.iv_icon);
            tvName = (TextView) findViewById(R.id.tv_name);
        }
    }
}

package com.jake.wukong.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class SelectAdapter extends BaseListAdapter<ApplicationInfo, SelectAdapter.ViewHolder> {
    IClickListener listener;

    public SelectAdapter(Context context, IClickListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApplicationInfo info = getItem(position);
        holder.btnAdd.setTag(info);
        if (info != null) {
            holder.ivIcon.setImageDrawable(info.loadIcon(mContext.getPackageManager()));
            holder.tvName.setText(info.loadLabel(mContext.getPackageManager()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectAdapter.ViewHolder(ResourceController.getInstance().inflate(R.layout.item_select), listener);
    }

    static class ViewHolder extends BaseViewHolder {
        ImageView ivIcon;
        TextView tvName;
        Button btnAdd;

        public ViewHolder(View itemView, final IClickListener listener) {
            super(itemView);
            ivIcon = (ImageView) findViewById(R.id.iv_icon);
            tvName = (TextView) findViewById(R.id.tv_name);
            btnAdd = (Button) findViewById(R.id.btn_add);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.clickAdd((ApplicationInfo) v.getTag());
                    }
                }
            });
        }
    }

    interface IClickListener {
        void clickAdd(ApplicationInfo applicationInfo);
    }
}

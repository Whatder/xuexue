package com.nkbh.xuexue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.HomeworkBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/3/15.
 */

public class HomeworkItemAdapter extends RecyclerView.Adapter<HomeworkItemAdapter.ViewHolder> {
    private Context mContext;
    private List<HomeworkBean> data;

    public HomeworkItemAdapter(Context mContext, List<HomeworkBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_homework, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(data.get(position).getPicUrl()).into(holder.ivHomeworkPic);
        holder.tvHomeworkHeader.setText(data.get(position).getTitle());
        holder.tvHomeworkContent.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivHomeworkPic)
        ImageView ivHomeworkPic;
        @BindView(R.id.tvHomeworkHeader)
        TextView tvHomeworkHeader;
        @BindView(R.id.tvHomeworkContent)
        TextView tvHomeworkContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

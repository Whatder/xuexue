package com.nkbh.xuexue.adapter.admin;

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
import com.nkbh.xuexue.bean.CourseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/4/3.
 */

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {
    private Context mContext;
    private List<CourseBean> data;
    private OnClickListener listener;

    public MovieItemAdapter(Context mContext, List<CourseBean> data, OnClickListener listener) {
        this.mContext = mContext;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_admin_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(data.get(position).getThumbnail()).into(holder.ivPic);
        holder.tvName.setText("标题：" + data.get(position).getTitle());
        holder.tvSummary.setText("简介：" + data.get(position).getSummary());
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnDel(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        ImageView ivPic;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvSummary)
        TextView tvSummary;
        @BindView(R.id.tvEdit)
        TextView tvEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void OnDel(CourseBean data);
    }
}

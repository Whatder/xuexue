package com.nkbh.xuexue.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.CourseDetailActivity;
import com.nkbh.xuexue.base.CourseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/3/15.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {
    private Context mContext;
    private List<CourseBean> data;

    public CourseItemAdapter(Context mContext, List<CourseBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(data.get(position).getPicUrl()).into(holder.ivCoursePic);
        holder.tvCourseHeader.setText(data.get(position).getTitle());
        holder.tvCourseContent.setText(data.get(position).getContent());
        holder.rlCourseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CourseDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rlCourseItem)
        RelativeLayout rlCourseItem;
        @BindView(R.id.ivCoursePic)
        ImageView ivCoursePic;
        @BindView(R.id.tvCourseHeader)
        TextView tvCourseHeader;
        @BindView(R.id.tvCourseContent)
        TextView tvCourseContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

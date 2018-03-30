package com.nkbh.xuexue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.PlanBean;
import com.nkbh.xuexue.dialog.PlanDetailDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/3/11.
 */

public class PlanItemAdapter extends RecyclerView.Adapter<PlanItemAdapter.ViewHolder> {

    private Context context;
    private List<PlanBean> data;

    public PlanItemAdapter(Context context, List<PlanBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvContent.setText(data.get(position).getContent());
        holder.tvTime.setText("" + data.get(position).getCreate_time());
        holder.viewStatus.setBackgroundColor("FINISH".equals(data.get(position).getStatus())
                ? context.getResources().getColor(R.color.colorGreen)
                : context.getResources().getColor(R.color.colorAccent));
        holder.planItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanDetailDialog dialog = new PlanDetailDialog(context, data.get(position));
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.planItem)
        CardView planItem;
        @BindView(R.id.viewStatus)
        View viewStatus;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvTime)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

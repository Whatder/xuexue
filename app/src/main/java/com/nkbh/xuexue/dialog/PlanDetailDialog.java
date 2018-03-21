package com.nkbh.xuexue.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.PlanBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2018/3/21.
 */

public class PlanDetailDialog extends Dialog {
    PlanBean mData;
    @BindView(R.id.tvPlanTitle)
    TextView tvPlanTitle;
    @BindView(R.id.tvPlanContent)
    TextView tvPlanContent;
    @BindView(R.id.tvPlanTime)
    TextView tvPlanTime;
    @BindView(R.id.btnGiveUp)
    Button btnGiveUp;
    @BindView(R.id.btnComplete)
    Button btnComplete;

    public PlanDetailDialog(@NonNull Context context, PlanBean data) {
        super(context);
        this.mData = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_plan_detail);
        ButterKnife.bind(this);
        tvPlanTitle.setText(mData.getTitle());
        tvPlanContent.setText(mData.getContent());
        tvPlanTime.setText(mData.getTime());
    }
}

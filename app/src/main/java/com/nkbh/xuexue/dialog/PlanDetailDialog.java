package com.nkbh.xuexue.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.PlanBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.TimeUtils;
import com.nkbh.xuexue.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    private Context mContext;
    private Listener listener;

    public PlanDetailDialog(@NonNull Context context, PlanBean data, Listener listener) {
        super(context);
        this.mContext = context;
        this.mData = data;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_plan_detail);
        ButterKnife.bind(this);
        tvPlanTitle.setText(mData.getTitle());
        tvPlanContent.setText(mData.getContent());
        tvPlanTime.setText(TimeUtils.stamp2String(mData.getCreate_time()));
    }

    @OnClick(R.id.btnComplete)
    void complete() {
        Map<String, String> params = new HashMap<>();
        params.put("status", "FINISH");
        params.put("id", mData.getId() + "");
        ServiceApi service = RetrofitHelper.getService();
        service.updatePlan(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        if ("succ".equals(value.getStatus())) {
                            listener.onStatusChanged();
                            ToastUtils.show(mContext, value.getData());
                            dismiss();
                        } else
                            ToastUtils.show(mContext, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(mContext, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface Listener {
        void onStatusChanged();
    }
}

package com.nkbh.xuexue.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.StringUtils;
import com.nkbh.xuexue.utils.TimeUtils;
import com.nkbh.xuexue.utils.ToastUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 2018/3/21.
 */

public class AddPlanActivity extends BaseActivity {
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.etContent)
    EditText etContent;

    long stamp;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_plan;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }

    @OnClick(R.id.ivAdd)
    void add() {
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        validateInfo(title, content, stamp);
    }

    @OnClick(R.id.tvTime)
    void selectedTime() {
        final StringBuffer timeStr = new StringBuffer();
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(AddPlanActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                timeStr.append(i + "-" + (i1 + 1) + "-" + i2 + " ");
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddPlanActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        timeStr.append((i > 10 ? i : "0" + i) + ":" + (i1 > 10 ? i1 : "0" + i1));
                        tvTime.setText(timeStr.toString());
                        stamp = TimeUtils.string2Stamp(timeStr.toString());
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), 30, true);
                timePickerDialog.show();
            }
        }, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void validateInfo(String title, String content, long stamp) {
        if (StringUtils.isNotBlank(title)
                && StringUtils.isNotBlank(content)
                && stamp != 0) {
            Map<String, String> params = new HashMap<>();
            params.put("user_id", ((UserBean) aCache.getAsObject("user")).getId() + "");
            params.put("title", title);
            params.put("content", content);
            params.put("create_time", stamp + "");
            params.put("status", "UNFINISH");
            ServiceApi service = RetrofitHelper.getService();
            service.addPlan(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBean<String>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBean<String> value) {
                            if ("succ".equals(value.getStatus())) {
                                ToastUtils.show(AddPlanActivity.this, value.getData());
                                finish();
                            } else
                                ToastUtils.show(AddPlanActivity.this, value.getMsg());
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else
            ToastUtils.show(AddPlanActivity.this, "信息不完整");
    }

}

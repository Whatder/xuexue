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
import com.nkbh.xuexue.utils.TimeUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

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
        finish();
    }

    @OnClick(R.id.tvTime)
    void selectedTime() {
        final StringBuffer timeStr = new StringBuffer();
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(AddPlanActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                timeStr.append(i + "-" + i1 + "-" + i2 + " ");
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

}

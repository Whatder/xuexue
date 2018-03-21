package com.nkbh.xuexue.activity;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;

import java.util.Calendar;

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
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(AddPlanActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tvTime.setText(datePicker.getYear() + "年" + (datePicker.getMonth() + 1) + "月" + datePicker.getDayOfMonth() + "日");
            }
        }, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
        dialog.show();
    }

}

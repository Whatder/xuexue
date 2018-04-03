package com.nkbh.xuexue.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.admin.Admin;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
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
 * Created by User on 2018/4/2.
 */

public class AdminChangePwdDialog extends Dialog {
    Context context;
    Admin currAdmin;
    @BindView(R.id.etOldPwd)
    TextInputEditText etOldPwd;
    @BindView(R.id.etNewPwd)
    TextInputEditText etNewPwd;
    @BindView(R.id.etRepeatNewPwd)
    TextInputEditText etRepeatNewPwd;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    public AdminChangePwdDialog(@NonNull Context context, Admin currAdmin) {
        super(context);
        this.context = context;
        this.currAdmin = currAdmin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_pwd);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCommit)
    void commit() {
        String oldPwd = etOldPwd.getText().toString().trim();
        String newPwd = etNewPwd.getText().toString().trim();
        String repeatPwd = etRepeatNewPwd.getText().toString().trim();
        if (null == oldPwd || null == newPwd || null == repeatPwd) {
            ToastUtils.show(context, "请输入完整");
            return;
        }
        if (!newPwd.equals(repeatPwd)) {
            ToastUtils.show(context, "两次密码输入不一致");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("id", currAdmin.getId() + "");
        params.put("old_pwd", oldPwd);
        params.put("new_pwd", newPwd);
        ServiceApi service = RetrofitHelper.getService();
        service.changePwdAdmin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        if ("succ".equals(value.getStatus())) {
                            ToastUtils.show(context, value.getData());
                            dismiss();
                        } else
                            ToastUtils.show(context, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

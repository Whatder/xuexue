package com.nkbh.xuexue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.StringUtils;
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
 * Created by User on 2018/3/3.
 */

public class LogUpActivity extends BaseActivity {
    @BindView(R.id.etUserName)
    TextInputEditText etUserName;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogup)
    Button btnLogup;
    @BindView(R.id.etUserAccount)
    TextInputEditText etUserAccount;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_logup;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.btnLogup)
    void logup() {
        String account = etUserAccount.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etUserName.getText().toString().trim();
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(name))
            logup(account, password, name);
        else
            ToastUtils.show(LogUpActivity.this, "输入错误");
    }

    private void logup(String account, String password, String name) {
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", password);
        params.put("name", name);
        ServiceApi service = RetrofitHelper.getService();
        service.logup(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(LogUpActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> userBeanResponseBean) {
                        loadingDialog.dismiss();
                        if ("error".equals(userBeanResponseBean.getStatus())) {
                            ToastUtils.show(LogUpActivity.this, userBeanResponseBean.getMsg());
                        } else {
                            ToastUtils.show(LogUpActivity.this, userBeanResponseBean.getData());
                            finish();
                        }
                    }
                });
    }
}

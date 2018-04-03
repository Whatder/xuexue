package com.nkbh.xuexue.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.admin.Admin;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.StringUtils;
import com.nkbh.xuexue.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 2018/3/3.
 */

public class AdminLoginActivity extends BaseActivity {


    @BindView(R.id.etUserName)
    TextInputEditText etUserName;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_admin_login;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.btnLogin)
    void login() {
        String account = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password))
            validateLogin(account, password);
        else
            ToastUtils.show(AdminLoginActivity.this, "输入错误");
    }

    private void validateLogin(String account, String password) {
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", password);
        ServiceApi service = RetrofitHelper.getService();
        service.adminLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<Admin>>() {
                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(AdminLoginActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<Admin> data) {
                        loadingDialog.dismiss();
                        if ("error".equals(data.getStatus())) {
                            ToastUtils.show(AdminLoginActivity.this, data.getMsg());
                        } else {
                            aCache.put("admin", data.getData());
                            Intent intent = new Intent(AdminLoginActivity.this, AdminManagerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}

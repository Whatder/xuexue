package com.nkbh.xuexue.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;
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

public class LoginActivity extends BaseActivity {
    @BindView(R.id.etUserName)
    TextInputEditText etUserName;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.inputWindow)
    CardView inputWindow;
    @BindView(R.id.tvLogup)
    TextView tvLogup;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.tvLogup)
    void logup() {
        Intent intent = new Intent(LoginActivity.this, LogUpActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, inputWindow, "inputWindow").toBundle());
    }

    @OnClick(R.id.btnLogin)
    void login() {
        String account = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password))
            validateLogin(account, password);
        else
            ToastUtils.show(LoginActivity.this, "输入错误");
    }

    @OnClick(R.id.tvAdminLogin)
    void adminLogin() {
        Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void validateLogin(String account, String password) {
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", password);
        ServiceApi service = RetrofitHelper.getService();
        service.login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<UserBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(LoginActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<UserBean> userBeanResponseBean) {
                        loadingDialog.dismiss();
                        if ("error".equals(userBeanResponseBean.getStatus())) {
                            ToastUtils.show(LoginActivity.this, userBeanResponseBean.getMsg());
                        } else {
                            aCache.put("user", userBeanResponseBean.getData());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });
    }


}

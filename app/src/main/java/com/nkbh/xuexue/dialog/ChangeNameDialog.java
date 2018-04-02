package com.nkbh.xuexue.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;
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

public class ChangeNameDialog extends Dialog {
    Context context;
    UserBean currUser;
    @BindView(R.id.etNewName)
    TextInputEditText etNewName;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    OnNameChanged onNameChanged;

    public ChangeNameDialog(@NonNull Context context, UserBean currUser, OnNameChanged nameChanged) {
        super(context);
        this.context = context;
        this.currUser = currUser;
        this.onNameChanged = nameChanged;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_name);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCommit)
    void commit() {
        final String newName = etNewName.getText().toString().trim();
        if (newName == null) {
            ToastUtils.show(context, "昵称不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("id", currUser.getId() + "");
        params.put("name", newName);
        ServiceApi service = RetrofitHelper.getService();
        service.changeName(params)
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
                            onNameChanged.nameChanged(newName);
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

    public interface OnNameChanged {
        void nameChanged(String newName);
    }
}

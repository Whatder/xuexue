package com.nkbh.xuexue.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.StringUtils;
import com.nkbh.xuexue.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 2018/3/20.
 */

public class PostArticleActivity extends BaseActivity {
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvPostTitle)
    TextView tvPostTitle;
    @BindView(R.id.tvPost)
    TextView tvPost;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.etPostContent)
    EditText etPostContent;
    UserBean currentUser;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_post_article;
    }

    @Override
    protected void initParameter() {
        currentUser = (UserBean) aCache.getAsObject("user");
        if (currentUser == null)
            finish();
    }

    @OnClick(R.id.ivClose)
    void close() {
        finish();
    }

    @OnClick(R.id.tvPost)
    void post() {
        String content = etPostContent.getText().toString().trim();
        if (StringUtils.isNotBlank(content)) {
            put2Remote(content);
        } else {
            Toast.makeText(PostArticleActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void put2Remote(String content) {
        loadingDialog.show();
        HashMap<String, String> params = new HashMap<>();
        params.put("author_id", currentUser.getId() + "");
        params.put("title", "");
        params.put("content", content);

        ServiceApi service = RetrofitHelper.getService();
        service.addTopic(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        loadingDialog.dismiss();
                        if ("succ".equals(value.getStatus())) {
                            ToastUtils.show(PostArticleActivity.this, value.getData());
                            finish();
                        } else
                            ToastUtils.show(PostArticleActivity.this, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(PostArticleActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

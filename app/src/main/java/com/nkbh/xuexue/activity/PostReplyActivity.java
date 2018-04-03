package com.nkbh.xuexue.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.TopicBean;
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
 * Created by User on 2018/4/1.
 */

public class PostReplyActivity extends BaseActivity {
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
    TopicBean currentTopic;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_post_reply;
    }

    @Override
    protected void initParameter() {
        currentUser = (UserBean) aCache.getAsObject("user");
        if (currentUser == null)
            finish();
        currentTopic = (TopicBean) getIntent().getSerializableExtra("topic");
        if (currentTopic == null)
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
            putReply2Remote(content);
        } else {
            Toast.makeText(PostReplyActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void putReply2Remote(String content) {
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("author_id", currentUser.getId() + "");
        params.put("topic_id", currentTopic.getId() + "");
        params.put("content", content);
        ServiceApi service = RetrofitHelper.getService();
        service.addReply(params)
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
                            ToastUtils.show(PostReplyActivity.this, value.getData());
                            finish();
                        } else
                            ToastUtils.show(PostReplyActivity.this, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(PostReplyActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}

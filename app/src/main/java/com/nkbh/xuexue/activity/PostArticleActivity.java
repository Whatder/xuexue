package com.nkbh.xuexue.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_post_article;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.ivClose)
    void close() {
        finish();
    }

    @OnClick(R.id.tvPost)
    void post() {
        String content = etPostContent.getText().toString().trim();
        if (StringUtils.isNotBlank(content)) {
            Toast.makeText(PostArticleActivity.this, content, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PostArticleActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
        }
    }
}

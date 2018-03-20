package com.nkbh.xuexue.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.PostArticleActivity;
import com.nkbh.xuexue.adapter.CommunityAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.base.CommentBean;
import com.nkbh.xuexue.utils.DmUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by User on 2018/3/6.
 */

public class CommunityFragment extends BaseFragment {
    @BindView(R.id.ivBanner)
    ImageView ivBanner;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.collapsingLayout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rvCommunity)
    RecyclerView rvCommunity;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    List<CommentBean> data = new ArrayList<>();
    CommunityAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initParameter() {
        adapter = new CommunityAdapter(mActivity, data);
        rvCommunity.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCommunity.setAdapter(adapter);
        rvCommunity.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = DmUtils.dp2px(mActivity, 10);
            }
        });
        getData();
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            CommentBean temp = new CommentBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521058060689&di=495ab52ad681d0354713975dc826a7dd&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1306%2F09%2F3336552_143T2JU-0.jpg",
                    "用户" + i,
                    "2018-10-1",
                    "终于毕业要出来工作了，老爸从抽屉里拿出一个箱子，语重心长的说儿啊，二十多年前，我和你妈放弃了国企工作出来创业，从一开始身无分文，然后3000，5000，10000，到后来20万，50万，直到现在，粗略统计应该有500万了！儿啊，这些借据你收好，爸妈这辈子估计还不清了，要靠你了儿子。");
            data.add(temp);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab)
    void postArticle() {
        Intent intent = new Intent(mActivity, PostArticleActivity.class);
        startActivity(intent);
    }
}

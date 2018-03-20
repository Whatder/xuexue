package com.nkbh.xuexue.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.CourseItemAdapter;
import com.nkbh.xuexue.adapter.HomeworkItemAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.base.CourseBean;
import com.nkbh.xuexue.base.HomeworkBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by User on 2018/3/6.
 */

public class StudyFragment extends BaseFragment {
    @BindView(R.id.tvCourseTitle)
    TextView tvCourseTitle;
    @BindView(R.id.tvCourseMore)
    TextView tvCourseMore;
    @BindView(R.id.rvCourseList)
    RecyclerView rvCourseList;
    @BindView(R.id.tvHomeworkTitle)
    TextView tvHomeworkTitle;
    @BindView(R.id.tvHomeworkMore)
    TextView tvHomeworkMore;
    @BindView(R.id.rvHomeworkList)
    RecyclerView rvHomeworkList;

    CourseItemAdapter courseItemAdapter;
    HomeworkItemAdapter homeworkItemAdapter;
    List<CourseBean> courseBeans = new ArrayList<>();
    List<HomeworkBean> homeworkBeans = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initParameter() {
        courseItemAdapter = new CourseItemAdapter(mActivity, courseBeans);
        rvCourseList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        rvCourseList.setAdapter(courseItemAdapter);

        homeworkItemAdapter = new HomeworkItemAdapter(mActivity, homeworkBeans);
        rvHomeworkList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        rvHomeworkList.setAdapter(homeworkItemAdapter);

        getCourseData();
        getHomeworkData();
    }

    private void getCourseData() {
        courseBeans.clear();
        for (int i = 0; i < 5; i++) {
            CourseBean temp = new CourseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521058060689&di=495ab52ad681d0354713975dc826a7dd&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1306%2F09%2F3336552_143T2JU-0.jpg", "Android开发" + (i + 1), "开发的艺术");
            courseBeans.add(temp);
        }
        courseItemAdapter.notifyDataSetChanged();
    }

    private void getHomeworkData() {
        for (int i = 0; i < 8; i++) {
            HomeworkBean temp = new HomeworkBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521058060689&di=495ab52ad681d0354713975dc826a7dd&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1306%2F09%2F3336552_143T2JU-0.jpg", "开发作业" + (i + 1), "按时完成");
            homeworkBeans.add(temp);
        }
        homeworkItemAdapter.notifyDataSetChanged();

    }
}

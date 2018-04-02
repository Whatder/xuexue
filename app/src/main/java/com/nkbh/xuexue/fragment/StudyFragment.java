package com.nkbh.xuexue.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.MainActivity;
import com.nkbh.xuexue.adapter.CourseItemAdapter;
import com.nkbh.xuexue.adapter.HomeworkItemAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.base.CourseBean;
import com.nkbh.xuexue.base.HomeworkBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        ServiceApi service = RetrofitHelper.getService();
        service.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<List<CourseBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<List<CourseBean>> value) {
                        if ("succ".equals(value.getStatus())) {
                            courseBeans.clear();
                            courseBeans.addAll(value.getData());
                            courseItemAdapter.notifyDataSetChanged();
                        } else
                            ToastUtils.show(mActivity, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(mActivity, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getHomeworkData() {
        for (int i = 0; i < 8; i++) {
            HomeworkBean temp = new HomeworkBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521720021877&di=a00a5065f2149956b5f65ab0a34b2afa&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F9922720e0cf3d7cac4837d51f91fbe096b63a9a4.jpg", "开发作业" + (i + 1), "按时完成");
            homeworkBeans.add(temp);
        }
        homeworkItemAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.toolBarTitle)
    void openMenu() {
        ((MainActivity) mActivity).openMenu();
    }
}

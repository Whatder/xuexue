package com.nkbh.xuexue.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;
import com.nkbh.xuexue.fragment.CommunityFragment;
import com.nkbh.xuexue.fragment.PlanFragment;
import com.nkbh.xuexue.fragment.StudyFragment;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bottomNav)
    BottomNavigationBar bottomNav;
    @BindView(R.id.ivProfilePic)
    CircleImageView ivProfilePic;
    @BindView(R.id.tvChangePic)
    TextView tvChangePic;
    @BindView(R.id.tvChangeName)
    TextView tvChangeName;
    @BindView(R.id.tvLogOut)
    TextView tvLogOut;
    @BindView(R.id.nav_menu)
    NavigationView navMenu;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    List<Fragment> fragments = new ArrayList<>();
    UserBean currentUser;
    @BindView(R.id.topImage)
    ImageView topImage;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    private int IMAGE_PICKER = 510;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        currentUser = (UserBean) aCache.getAsObject("user");
        if (currentUser == null)
            finish();
        fragments.add(new PlanFragment());
        fragments.add(new StudyFragment());
        fragments.add(new CommunityFragment());
        ChangeFragmentView(fragments.get(0));
        initBottomNav();
        initNavMenu();
    }

    private void initBottomNav() {
        bottomNav.addItem(new BottomNavigationItem(R.drawable.icon_list, "计划")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.icon_study, "学习")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.icon_community, "交流")).setActiveColor(R.color.colorPrimary)
                .initialise();
        bottomNav.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                ChangeFragmentView(fragments.get(position));
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void initNavMenu() {
        tvUserName.setText(currentUser.getName());
        Glide.with(MainActivity.this).load(currentUser.getProfile_pic()).into(ivProfilePic);
        Glide.with(MainActivity.this)
                .load(currentUser.getProfile_pic())
                .apply(bitmapTransform(new BlurTransformation(25)))
                .into(topImage);

    }

    @OnClick(R.id.tvChangePic)
    void changePic() {
        ImagePicker picker = ImagePicker.getInstance();
        picker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(activity).load(path).into(imageView);
            }

            @Override
            public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

            }

            @Override
            public void clearMemoryCache() {

            }
        });
        picker.setShowCamera(true);
        picker.setSelectLimit(1);

        Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    @OnClick(R.id.tvChangeName)
    void changeName() {

    }

    @OnClick(R.id.tvLogOut)
    void logOut() {
        aCache.remove("user");
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void ChangeFragmentView(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainPanel, fragment);
        ft.commit();
    }

    public void openMenu() {
        drawerLayout.openDrawer(Gravity.START);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                updateProfilePic(images.get(0).path);
            } else {
                ToastUtils.show(MainActivity.this, "没有选择图片");
            }
        }
    }

    private void updateProfilePic(final String path) {
        File file = new File(path);
        MultipartBody.Builder build = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        build.addFormDataPart("file", file.getName(), imageBody);
        build.addFormDataPart("user_id", currentUser.getId() + "");
        List<MultipartBody.Part> part = build.build().parts();
        ServiceApi service = RetrofitHelper.getService();
        service.updateProfilePic(part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        if ("succ".equals(value.getStatus())) {
//                            更新本地头像缓存
                            currentUser.setProfile_pic(path);
                            aCache.put("user", currentUser);
                            initNavMenu();
                            ToastUtils.show(MainActivity.this, value.getData());
                        } else
                            ToastUtils.show(MainActivity.this, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(MainActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

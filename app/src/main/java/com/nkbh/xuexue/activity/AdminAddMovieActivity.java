package com.nkbh.xuexue.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.StringUtils;
import com.nkbh.xuexue.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by User on 2018/4/5.
 */

public class AdminAddMovieActivity extends BaseActivity {
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvSave)
    ImageView tvSave;
    @BindView(R.id.ivThumb)
    ImageView ivThumb;
    @BindView(R.id.etSrcTitle)
    TextInputEditText etSrcTitle;
    @BindView(R.id.btnGetFilePath)
    Button btnGetFilePath;
    @BindView(R.id.tvFilePath)
    TextView tvFilePath;
    @BindView(R.id.divLine)
    View divLine;
    @BindView(R.id.etSummary)
    EditText etSummary;

    private int IMAGE_PICKER = 510;
    private int VIDEO_PICKER = 520;
    private String thumbPath, videoPath, srcTitle, srcSummary;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_admin_add_movie;
    }

    @Override
    protected void initParameter() {

    }


    @OnClick(R.id.ivThumb)
    public void onIvThumbClicked() {
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
        picker.setShowCamera(false);
        picker.setMultiMode(false);
        picker.setCrop(false);
        Intent intent = new Intent(AdminAddMovieActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    @OnClick(R.id.btnGetFilePath)
    public void onBtnGetFilePathClicked() {
        FilePicker.from(this)
                .chooseMedia()
                .isSingle()
                .onlyShowVideos()
                .requestCode(VIDEO_PICKER)
                .start();
    }


    private void save2Remote() {
        loadingDialog.show();
        File image = new File(thumbPath);
        File video = new File(videoPath);
        MultipartBody.Builder build = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        RequestBody videoBody = RequestBody.create(MediaType.parse("multipart/form-data"), video);

        build.addFormDataPart("image", image.getName(), imageBody);
        build.addFormDataPart("file", video.getName(), videoBody);
        build.addFormDataPart("title", srcTitle);
        build.addFormDataPart("summary", srcSummary);
        List<MultipartBody.Part> part = build.build().parts();

        ServiceApi service = RetrofitHelper.getService();
        service.updateMovie(part)
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
                            ToastUtils.show(AdminAddMovieActivity.this, value.getData());
                            finish();
                        } else
                            ToastUtils.show(AdminAddMovieActivity.this, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(AdminAddMovieActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @OnClick(R.id.ivBack)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.tvSave)
    public void onTvSaveClicked() {
        srcTitle = etSrcTitle.getText().toString().trim();
        srcSummary = etSummary.getText().toString().trim();
        if (!StringUtils.isNotBlank(thumbPath)
                || !StringUtils.isNotBlank(videoPath)
                || !StringUtils.isNotBlank(srcTitle)
                || !StringUtils.isNotBlank(srcSummary)) {
            ToastUtils.show(AdminAddMovieActivity.this, "请输入完整");
        } else {
            save2Remote();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Glide.with(AdminAddMovieActivity.this).load(images.get(0).path).into(ivThumb);
                thumbPath = images.get(0).path;
            } else {
                ToastUtils.show(AdminAddMovieActivity.this, "没有选择图片");
            }
        }
        if (data != null && requestCode == VIDEO_PICKER) {
            ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
            videoPath = essFileList.get(0).getAbsolutePath();
            tvFilePath.setText("选择了：" + essFileList.get(0).getAbsolutePath());
        }
    }
}

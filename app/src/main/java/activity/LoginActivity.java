package activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.nkbh.xuexue.MainActivity;
import com.nkbh.xuexue.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.btnLogin)
    void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

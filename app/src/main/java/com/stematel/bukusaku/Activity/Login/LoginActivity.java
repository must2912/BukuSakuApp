package com.stematel.bukusaku.Activity.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;
import com.stematel.bukusaku.Activity.ChangePass.ChangePassAcitvity;
import com.stematel.bukusaku.Activity.Home.HomeActivity;
import com.stematel.bukusaku.Activity.Pelanggaran.PelanggaranPresenter;
import com.stematel.bukusaku.Activity.SplashScreen.SplashActivity;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginView {

    int level = 1;

    @BindView(R.id.EdNis)
    EditText ednis;
    @BindView(R.id.EdPassword)
    EditText edpass;
    @BindView(R.id.btnMasuk)
    Button btnmasuk;

    private LoginPresenter loginPresenter;
    int nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.injectView(this);

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.postSignin(Long.valueOf(ednis.getText().toString()), edpass.getText().toString(), level);
            }
        });
    }
    /*@OnClick(R.id.btnMasuk)
    public void btnMasuk(View view){
        loginPresenter.postSignin(Integer.parseInt(ednis.getText().toString()), edpass.getText().toString());
    }*/

    @Override
    public void onLoading() {

    }

    @Override
    public void ondLoading() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(ResponseService<UserResponse> responseService) {
        LocalService.saveLogin(responseService.getData());
        Toast.makeText(this, responseService.getMessage().toLowerCase(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ChangePassAcitvity.class);
        intent.putExtra("status", responseService.getData().getStatus());
        intent.putExtra("nis", responseService.getData().getNis());
        startActivity(intent);
    }
}

package com.stematel.bukusaku.Activity.ChangePass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stematel.bukusaku.Activity.Home.HomeActivity;
import com.stematel.bukusaku.Activity.Login.LoginActivity;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassAcitvity extends AppCompatActivity implements ChangePassPresenter.ChangePassView{

    long nis;
    int level = 1;

    @BindView(R.id.EdGantiPass)
    EditText edgantipass;
    @BindView(R.id.EdGantiEmail)
    EditText edgantiemail;
    @BindView(R.id.btngantipass)
    Button btngantipass;

    private ChangePassPresenter changePassPresenter;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        ButterKnife.bind(this);

        changePassPresenter = new ChangePassPresenter();
        changePassPresenter.injectView(this);

        checklogin();

        btngantipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassPresenter.postPassword(nis, edgantiemail.getText().toString(), edgantipass.getText().toString(), level);
            }
        });
    }

    private void checklogin() {

        Bundle bundle = getIntent().getExtras();

        int statusextra = bundle.getInt("status");
        nis = bundle.getLong("nis");

            if (status == 1 || statusextra == 1){
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void ondLoading() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(ResponseService<UserResponse> responseService) {
        Toast.makeText(this, responseService.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}

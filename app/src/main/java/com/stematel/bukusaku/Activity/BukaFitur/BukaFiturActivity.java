package com.stematel.bukusaku.Activity.BukaFitur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stematel.bukusaku.Activity.Peraturan.DetailPasalActivityPresenter;
import com.stematel.bukusaku.Activity.TambahPoin.TambahPoinActivity;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.ResponseService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BukaFiturActivity extends AppCompatActivity implements BukaFiturPresenter.VerifikasiKode {

    BukaFiturPresenter presenter;

    @BindView(R.id.EdKodeGuru)
    EditText edkodeguru;
    @BindView(R.id.btnVerif)
    Button btnVerif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buka_fitur);
        ButterKnife.bind(this);


        presenter = new BukaFiturPresenter();
        presenter.injectView(this);

        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postVeriv(String.valueOf(edkodeguru.getText().toString()));
            }
        });
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void ondLoading() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(ResponseService responseService) {
        Toast.makeText(getApplicationContext(), responseService.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), TambahPoinActivity.class));
    }
}

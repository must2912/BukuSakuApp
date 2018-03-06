package com.stematel.bukusaku.Activity.Peraturan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stematel.bukusaku.Activity.Home.HomeActivity;
import com.stematel.bukusaku.Activity.Login.LoginPresenter;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPasalActivity extends AppCompatActivity implements DetailPasalActivityPresenter.TambahPoin{

    long nis, date;
    String datestring;
    SimpleDateFormat sdf;

    PasalResponse userResponse;

    DetailPasalActivityPresenter presenter;

    @BindView(R.id.btnTambahKeranjang)
    TextView btntambah;
    @BindView(R.id.kode)
    TextView kode;
    @BindView(R.id.kategori)
    TextView kategori;
    @BindView(R.id.keterangan)
    TextView keterangan;
    @BindView(R.id.poin)
    TextView poin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasal);

        ButterKnife.bind(this);
        presenter = new DetailPasalActivityPresenter();
        presenter.injectView(this);

        init();

        UserResponse user = LocalService.getLogin();
        if (user != null){
            nis = user.getNis();
        }

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postpoin(nis, kode.getText().toString(), Integer.valueOf(poin.getText().toString()), keterangan.getText().toString(), datestring);
            }
        });
    }

    private void init() {

        date = System.currentTimeMillis();

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        datestring = sdf.format(date);

        Bundle bundle = getIntent().getExtras();
        userResponse = (PasalResponse) bundle.getSerializable("pasal");

        kode.setText(userResponse.getKode());
        kategori.setText(userResponse.getKategori());
        keterangan.setText(userResponse.getKeterangan());
        poin.setText(userResponse.getPoin());

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
        Toast.makeText(this, responseService.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}

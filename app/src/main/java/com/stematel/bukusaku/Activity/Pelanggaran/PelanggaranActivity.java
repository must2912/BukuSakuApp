package com.stematel.bukusaku.Activity.Pelanggaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.stematel.bukusaku.Adapter.Pelanggaran.PelanggaranAdapter;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import java.util.List;

import butterknife.OnClick;

public class PelanggaranActivity extends AppCompatActivity implements PelanggaranPresenter.PelanggaranView{

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private PelanggaranPresenter pelanggaranPresenter;
    long nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggaran);

        pelanggaranPresenter = new PelanggaranPresenter();
        pelanggaranPresenter.injectView(this);

        init();
        refresh();
    }

    private void refresh() {
        pelanggaranPresenter.postNis(nis);
    }

    private void init() {
        UserResponse user = LocalService.getLogin();
        if (user != null){
            nis = user.getNis();
        }
    }

    /*@OnClick(R.id.btnlihat)
    public void btnlihat(View view){
        pelanggaranPresenter.postNis(nis);
    }*/


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
    public void onSuccess(ResponseService<PelanggaranResponse> responseService) {
        List<PelanggaranResponse> pelanggaranResponseList = responseService.getDatalist();
        mRecyclerView = (RecyclerView) findViewById(R.id.pelanggaranlist);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PelanggaranAdapter(pelanggaranResponseList, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}

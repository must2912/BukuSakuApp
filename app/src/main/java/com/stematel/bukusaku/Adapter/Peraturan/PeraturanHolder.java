package com.stematel.bukusaku.Adapter.Peraturan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stematel.bukusaku.Activity.Peraturan.DetailPeraturan;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PeraturanHolder extends RecyclerView.ViewHolder{

    private final Context context;

    @BindView(R.id.tvKategori)
    TextView tvKategori;
    @BindView(R.id.tvKode)
    TextView tvKode;
    @BindView(R.id.tvPoin)
    TextView tvPoin;
    @BindView(R.id.tvKeterangan)
    TextView tvKeterangan;

    public PeraturanHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();


    }

}

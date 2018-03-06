package com.stematel.bukusaku.Adapter.Peraturan.Penghargaan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stematel.bukusaku.Activity.Peraturan.DetailPeraturan;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tofa-pc on 2/21/2018.
 */

public class PeraturanPenghargaanHolder extends RecyclerView.ViewHolder {

    private final Context context;
    @BindView(R.id.tvKategori)
    TextView tvKategori;
    @BindView(R.id.tvKode)
    TextView tvKode;
    @BindView(R.id.tvPoin)
    TextView tvPoin;
    @BindView(R.id.tvKeterangan)
    TextView tvKeterangan;

    public PeraturanPenghargaanHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();


    }

    /*@Override
    public void onClick(View itemView) {
        Intent intent = new Intent(context, DetailPeraturan.class);
        PasalResponse pasal = (PasalResponse) itemView.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("produk", pasal);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }*/

}

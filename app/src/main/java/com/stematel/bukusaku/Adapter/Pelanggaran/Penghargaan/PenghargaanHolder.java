package com.stematel.bukusaku.Adapter.Pelanggaran.Penghargaan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stematel.bukusaku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tofa-pc on 2/18/2018.
 */

public class PenghargaanHolder extends RecyclerView.ViewHolder {

    private final Context context;

    @BindView(R.id.tv_kategori)
    TextView kategori;
    @BindView(R.id.tv_keterangan)
    TextView keterangan;
    @BindView(R.id.tv_poin)
    TextView poin;

    public PenghargaanHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();

//        itemView.setOnClickListener(this);

    }
}

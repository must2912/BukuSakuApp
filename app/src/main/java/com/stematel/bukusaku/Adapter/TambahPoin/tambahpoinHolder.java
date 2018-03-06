package com.stematel.bukusaku.Adapter.TambahPoin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stematel.bukusaku.Activity.Peraturan.DetailPasalActivity;
import com.stematel.bukusaku.Activity.Peraturan.DetailPeraturan;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tofa-pc on 3/4/2018.
 */

public class tambahpoinHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final Context context;

    @BindView(R.id.tvKategori)
    TextView tvKategori;
    @BindView(R.id.tvKode)
    TextView tvKode;
    @BindView(R.id.tvPoin)
    TextView tvPoin;
    @BindView(R.id.tvKeterangan)
    TextView tvKeterangan;

    public tambahpoinHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();

        itemView.setOnClickListener(this);
        }

    @Override
    public void onClick(View itemView) {
        Intent intent = new Intent(context, DetailPasalActivity.class);
        PasalResponse pelanggaran = (PasalResponse) itemView.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("pasal", pelanggaran);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

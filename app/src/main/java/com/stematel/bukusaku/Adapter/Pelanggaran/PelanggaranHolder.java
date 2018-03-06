package com.stematel.bukusaku.Adapter.Pelanggaran;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stematel.bukusaku.Activity.Peraturan.DetailPeraturan;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PelanggaranHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final Context context;
    @BindView(R.id.tvKdSis)
    TextView tvKdsis;
    @BindView(R.id.tvKategoriBusak)
    TextView tvKategoriBusak;
    @BindView(R.id.tvKodeBusak)
    TextView tvKodeBusak;
    @BindView(R.id.tvPelanggaraBusak)
    TextView tvPelanggaranBusak;
    @BindView(R.id.tvPoinBusak)
    TextView tvPoinBusak;
    @BindView(R.id.tvKeteranganBusak)
    TextView tvKeteranganBusak;

    public PelanggaranHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View itemView) {
        Intent intent = new Intent(context, DetailPeraturan.class);
        PelanggaranResponse pelanggaran = (PelanggaranResponse) itemView.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("produk", pelanggaran);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

package com.stematel.bukusaku.Adapter.Notif;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stematel.bukusaku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tofa-pc on 2/20/2018.
 */

public class NotifHolder extends RecyclerView.ViewHolder{
    private final Context context;

    @BindView(R.id.tvTanggal)
    TextView tanggal;
    @BindView(R.id.tvAksi)
    TextView aksi;
    @BindView(R.id.tvPoin)
    TextView poin;
    @BindView(R.id.tvTimdis)
    TextView timdis;

    public NotifHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();

//        itemView.setOnClickListener(this);

    }
}

package com.stematel.bukusaku.Adapter.Pelanggaran.Penghargaan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stematel.bukusaku.Adapter.Pelanggaran.Pelanggaran.PelanggaranPoinHolder;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.R;

import java.util.List;

/**
 * Created by tofa-pc on 2/18/2018.
 */

public class PenghargaanPoinAdapter extends RecyclerView.Adapter<PenghargaanHolder>{

    private List<PelanggaranResponse> mItems;
    private Context context;

    public PenghargaanPoinAdapter(Context context, List<PelanggaranResponse> mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public PenghargaanHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poin, parent, false);
        PenghargaanHolder riwayatHolder = new PenghargaanHolder(mView);
        return riwayatHolder;
    }

    @Override
    public void onBindViewHolder (final PenghargaanHolder holder, final int position){

        holder.kategori.setText(mItems.get(position).getKategori());
        holder.keterangan.setText(mItems.get(position).getPelanggaran());
        holder.poin.setText(mItems.get(position).getPoin());

        //holder.itemView.setTag(history);

    }

    @Override
    public int getItemCount () {
        return mItems.size();
    }


}

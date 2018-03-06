package com.stematel.bukusaku.Adapter.Pelanggaran;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stematel.bukusaku.Adapter.Peraturan.PeraturanHolder;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;

import java.util.List;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PelanggaranAdapter extends RecyclerView.Adapter<PelanggaranHolder>{
    List<PelanggaranResponse> mPelanggaranList;
    private Context context;

    public PelanggaranAdapter(List <PelanggaranResponse> PasalList, Context context) {
        mPelanggaranList = PasalList;
        this.context = context;
    }

    @Override
    public PelanggaranHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelanggaran_list, parent, false);
        PelanggaranHolder pelanggaranHolder = new PelanggaranHolder(mView);
        return pelanggaranHolder;
    }

    @Override
    public void onBindViewHolder (final PelanggaranHolder holder, final int position){

        PelanggaranResponse pasal = mPelanggaranList.get(position);

        holder.tvKategoriBusak.setText(pasal.getJenis());
        holder.tvKodeBusak.setText(pasal.getKode());
        holder.tvPoinBusak.setText(pasal.getPoin());
        holder.tvKeteranganBusak.setText(pasal.getTimdis());
        /*holder.tvNo.setText(pasal.getNo());
        holder.tvJenis.setText(pasal.getJenis());
        holder.tvKategori.setText(pasal.getKategori());
        holder.tvKode.setText(pasal.getKode());
        holder.tvPoin.setText(pasal.getPoin());
        holder.tvKeterangan.setText(pasal.getKeterangan());

        holder.itemView.setTag(pasal);*/

    }

    @Override
    public int getItemCount () {
        return mPelanggaranList.size();
    }


}


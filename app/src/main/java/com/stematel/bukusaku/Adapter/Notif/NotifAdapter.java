package com.stematel.bukusaku.Adapter.Notif;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stematel.bukusaku.Adapter.Pelanggaran.Pelanggaran.PelanggaranPoinHolder;
import com.stematel.bukusaku.Model.Notif.NotifResponse;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.R;

import java.util.List;

/**
 * Created by tofa-pc on 2/20/2018.
 */

public class NotifAdapter extends RecyclerView.Adapter<NotifHolder>{

    private List<NotifResponse> mItems;
    private Context context;

    public NotifAdapter(Context context, List<NotifResponse> mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public NotifHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notif, parent, false);
        NotifHolder riwayatHolder = new NotifHolder(mView);
        return riwayatHolder;
    }

    @Override
    public void onBindViewHolder (final NotifHolder holder, final int position){

        holder.tanggal.setText(mItems.get(position).getTanggal());
        holder.aksi.setText(mItems.get(position).getAksi());
        holder.timdis.setText(mItems.get(position).getTimdis());

        holder.poin.setText(mItems.get(position).getTpoin());


        //holder.itemView.setTag(history);

    }

    @Override
    public int getItemCount () {
        return mItems.size();
    }


}

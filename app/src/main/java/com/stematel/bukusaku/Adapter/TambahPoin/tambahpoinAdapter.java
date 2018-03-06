package com.stematel.bukusaku.Adapter.TambahPoin;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stematel.bukusaku.Adapter.Pelanggaran.PelanggaranHolder;
import com.stematel.bukusaku.Adapter.Peraturan.PeraturanHolder;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Utils.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tofa-pc on 3/4/2018.
 */

public class tambahpoinAdapter extends RecyclerView.Adapter<tambahpoinHolder> implements Filterable{
    ArrayList<PasalResponse> mPasalList;
    ArrayList<PasalResponse> mFilteredList;
    private Context context;

    public tambahpoinAdapter(ArrayList <PasalResponse> PasalList, Context context) {
        mPasalList = PasalList;
        this.context = context;
        mFilteredList = PasalList;
    }

    @Override
    public tambahpoinHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pasal_list, parent, false);
        tambahpoinHolder pelanggaranHolder = new tambahpoinHolder(mView);
        return pelanggaranHolder;
    }

    @Override
    public void onBindViewHolder (final tambahpoinHolder holder, final int position){

        PasalResponse pasal = mPasalList.get(position);
        PasalResponse filter = mFilteredList.get(position);

        holder.tvKategori.setText(filter.getKategori());
        holder.tvKode.setText(filter.getKode());
        holder.tvPoin.setText(filter.getPoin());
        holder.tvKeterangan.setText(filter.getKeterangan());

        holder.itemView.setTag(pasal);

    }

    @Override
    public int getItemCount () {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mPasalList;
                } else {

                    ArrayList<PasalResponse> filteredList = new ArrayList<>();

                    for (PasalResponse androidVersion : mPasalList) {

                        if (androidVersion.getKategori().toLowerCase().contains(charString) || androidVersion.getKeterangan().toLowerCase().contains(charString) || androidVersion.getKode().toLowerCase().contains(charString) || androidVersion.getPoin().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<PasalResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

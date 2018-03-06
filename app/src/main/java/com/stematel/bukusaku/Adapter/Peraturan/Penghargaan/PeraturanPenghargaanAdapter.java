package com.stematel.bukusaku.Adapter.Peraturan.Penghargaan;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stematel.bukusaku.Adapter.Peraturan.PeraturanHolder;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Utils.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tofa-pc on 2/21/2018.
 */

public class PeraturanPenghargaanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<PasalResponse> mPasalList;
    ArrayList<PasalResponse> mFilteredList;
    private Context context;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final int HERO = 2;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;

    private String errorMsg;

    public PeraturanPenghargaanAdapter(Context context) {
        this.mCallback = (PaginationAdapterCallback) context;
        mPasalList = new ArrayList<>();
        this.context = context;
    }

    public List<PasalResponse> getMovies() {
        return mPasalList;
    }

    public void setMovies(List<PasalResponse> movieResults) {
        this.mPasalList = movieResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.pasal_list, parent, false);
                viewHolder = new PeraturanHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new PeraturanPenghargaanAdapter.LoadingHolder(viewLoading);
                break;
        }

        /*View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pasal_list, parent, false);
        PeraturanHolder peraturanHolder = new PeraturanHolder(mView);*/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (final RecyclerView.ViewHolder holder, final int position){

        PasalResponse pasal = mPasalList.get(position);

        switch (getItemViewType(position)) {

            case ITEM:
                final PeraturanPenghargaanHolder movieVH = (PeraturanPenghargaanHolder) holder;

                movieVH.tvKategori.setText(pasal.getKategori());
                movieVH.tvKode.setText(pasal.getKode());
                movieVH.tvPoin.setText(pasal.getPoin());
                movieVH.tvKeterangan.setText(pasal.getKeterangan());

                break;

            case LOADING:
                PeraturanPenghargaanAdapter.LoadingHolder loadingVH = (PeraturanPenghargaanAdapter.LoadingHolder) holder;

                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                    loadingVH.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    context.getString(R.string.error_msg_unknown));

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;

        }

    }

    @Override
    public int getItemCount() {
        return mPasalList == null ? 0 : mPasalList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mPasalList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


     /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */

    public void add(PasalResponse r) {
        mPasalList.add(r);
        notifyItemInserted(mPasalList.size() - 1);
    }

    public void addAll(List<PasalResponse> moveResults) {
        for (PasalResponse result : moveResults) {
            add(result);
        }
    }

    public void remove(PasalResponse r) {
        int position = mPasalList.indexOf(r);
        if (position > -1) {
            mPasalList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new PasalResponse());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mPasalList.size() - 1;
        PasalResponse result = getItem(position);

        if (result != null) {
            mPasalList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PasalResponse getItem(int position) {
        return mPasalList.get(position);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */
    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(mPasalList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }

    /*Holder Dibawah nya neh >>>>>>>>><<<<<<<<<<<<<*/

    /*public class PeraturanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context context;
        @BindView(R.id.tvNo)
        TextView tvNo;
        @BindView(R.id.tvJenis)
        TextView tvJenis;
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

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View itemView) {
            Intent intent = new Intent(context, DetailPeraturan.class);
            PasalResponse pasal = (PasalResponse) itemView.getTag();
            Bundle bundle = new Bundle();
            bundle.putSerializable("produk", pasal);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }

    }*/

    public class LoadingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingHolder(View itemView) {
            super(itemView);

            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = (ImageButton) itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = (TextView) itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = (LinearLayout) itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:

                    showRetry(false, null);
                    mCallback.retryPageLoad();

                    break;
            }
        }
    }
}

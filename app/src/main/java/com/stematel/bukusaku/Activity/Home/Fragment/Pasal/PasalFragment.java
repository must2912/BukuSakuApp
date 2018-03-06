package com.stematel.bukusaku.Activity.Home.Fragment.Pasal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stematel.bukusaku.Adapter.Peraturan.PeraturanAdapter;
import com.stematel.bukusaku.Model.Peraturan.PagePost;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.Network.NetworkServices;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.ResponseService;
import com.stematel.bukusaku.Utils.PaginationAdapterCallback;
import com.stematel.bukusaku.Utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasalFragment extends Fragment /*implements SearchView.OnQueryTextListener*/ implements PaginationAdapterCallback  {

    RecyclerView mRecyclerView;
    PeraturanAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private static final String TAG = "PeraturanActivity";

    LinearLayout error_layout;
    Button btnRetry;
    TextView txtError;
    EditText editText;
    ProgressBar progressBar;

    private static final int PAGE_START = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 4;
    private int currentPage = PAGE_START;

    public PasalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_pasal, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.pasallist);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        error_layout = (LinearLayout) view.findViewById(R.id.error_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        btnRetry = (Button) view.findViewById(R.id.error_btn_retry);

        mAdapter = new PeraturanAdapter(getContext());


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        refresh();
        return view;
    }

    private void loadNextPage() {

        Log.d(TAG, "loadNextPage: " + currentPage);

        PagePost pagePost = new PagePost(currentPage);
        Call<ResponseService<PasalResponse>> produkCall = NetworkClient.getInstance().getApiService().getPasal(pagePost);
        produkCall.enqueue(new Callback<ResponseService<PasalResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<PasalResponse>> call, Response<ResponseService<PasalResponse>> response) {

                if (response.body().getError().equals("false")){
                    mAdapter.removeLoadingFooter();
                    isLoading = false;

                    List<PasalResponse> pasalResponses = response.body().getDatalist();
                    mAdapter.addAll(pasalResponses);

                    if (currentPage != TOTAL_PAGES) mAdapter.addLoadingFooter();
                    else isLastPage = true;
                } else {
                    isLastPage = true;
                    mAdapter.removeLoadingFooter();
                }


                /*ArrayList<PasalResponse> PasalList = response.body().getDatalist();
                mAdapter = new PeraturanAdapter(getContext());
                mRecyclerView.setAdapter(mAdapter);*/
            }

            @Override
            public void onFailure(Call<ResponseService<PasalResponse>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                mAdapter.showRetry(true, fetchErrorMessage(t));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_option_menu, menu);

    }

    public void refresh() {

        hideErrorView();

        PagePost pagePost = new PagePost(currentPage);
        Call<ResponseService<PasalResponse>> produkCall = NetworkClient.getInstance().getApiService().getPasal(pagePost);
        produkCall.enqueue(new Callback<ResponseService<PasalResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<PasalResponse>> call, Response<ResponseService<PasalResponse>> response) {

                hideErrorView();

                List<PasalResponse> pasalResponses = response.body().getDatalist();
                progressBar.setVisibility(View.GONE);
                mAdapter.addAll(pasalResponses);

                if (currentPage <= TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;


                /*ArrayList<PasalResponse> PasalList = response.body().getDatalist();
                mAdapter = new PeraturanAdapter(getContext());
                mRecyclerView.setAdapter(mAdapter);*/
            }

            @Override
            public void onFailure(Call<ResponseService<PasalResponse>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public void retryPageLoad() {
        loadNextPage();
    }


    /**
     * @param throwable required for {@link #fetchErrorMessage(Throwable)}
     * @return
     */
    private void showErrorView(Throwable throwable) {

        if (error_layout.getVisibility() == View.GONE) {
            error_layout.setVisibility(View.VISIBLE);
            error_layout.setVisibility(View.GONE);

            txtError.setText(fetchErrorMessage(throwable));
        }
    }

    /**
     * @param throwable to identify the type of error
     * @return appropriate error message
     */
    private String fetchErrorMessage(Throwable throwable) {
        String errorMsg = getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkConnected()) {
            errorMsg = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMsg = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }

    // Helpers -------------------------------------------------------------------------------------


    private void hideErrorView() {
        if (error_layout.getVisibility() == View.VISIBLE) {
            error_layout.setVisibility(View.GONE);
            error_layout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Remember to add android.permission.ACCESS_NETWORK_STATE permission.
     *
     * @return
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}

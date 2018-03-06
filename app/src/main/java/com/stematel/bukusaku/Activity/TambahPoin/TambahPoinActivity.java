package com.stematel.bukusaku.Activity.TambahPoin;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stematel.bukusaku.Adapter.Peraturan.PeraturanAdapter;
import com.stematel.bukusaku.Adapter.TambahPoin.tambahpoinAdapter;
import com.stematel.bukusaku.Model.Peraturan.PagePost;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.Network.NetworkClient;
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

public class TambahPoinActivity extends AppCompatActivity {

    private static final String TAG = "TambahPoin";

    RecyclerView mRecyclerView;
    tambahpoinAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    LinearLayout error_layout;
    Button btnRetry;
    TextView txtError;
    EditText editText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_poin);

        init();
        refresh();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        btnRetry = (Button) findViewById(R.id.error_btn_retry);

    }

    public void refresh() {

        Call<ResponseService<PasalResponse>> produkCall = NetworkClient.getInstance().getApiService().getpasal();
        produkCall.enqueue(new Callback<ResponseService<PasalResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<PasalResponse>> call, Response<ResponseService<PasalResponse>> response) {

                ArrayList<PasalResponse> PasalList = response.body().getDatalist();
                mAdapter = new tambahpoinAdapter(PasalList, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ResponseService<PasalResponse>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}

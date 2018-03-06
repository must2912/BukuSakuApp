package com.stematel.bukusaku.Activity.Home.Fragment.Home.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stematel.bukusaku.Adapter.Pelanggaran.Pelanggaran.PelanggaranPoinAdapter;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.User.PelanggaranPost;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PelanggaranFragment extends Fragment {

    long id;
    RecyclerView list;

    public PelanggaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pelanggaran, container, false);

        initViews(v);
        refresh();
        return v;
    }

    private void refresh() {
        PelanggaranPost pelanggaranPost = new PelanggaranPost(id);
        Call<ResponseService<PelanggaranResponse>> responseServiceCall = NetworkClient.getInstance().getApiService().postPelanggaran(pelanggaranPost);
        responseServiceCall.enqueue(new Callback<ResponseService<PelanggaranResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<PelanggaranResponse>> call, Response<ResponseService<PelanggaranResponse>> response) {
                if (response.body().getError().equals("false")){
                    List<PelanggaranResponse> pelanggaranResponseList = response.body().getDatalist();
                    PelanggaranPoinAdapter adapter = new PelanggaranPoinAdapter(getActivity(), pelanggaranResponseList);
                    list.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseService<PelanggaranResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(View v) {

        UserResponse user = LocalService.getLogin();

        if (user!=null){
            id = user.getNis();
        }

        list = (RecyclerView)v.findViewById(R.id.recycle);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}

package com.stematel.bukusaku.Activity.Home.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.stematel.bukusaku.Adapter.Notif.NotifAdapter;
import com.stematel.bukusaku.Model.Notif.NotifResponse;
import com.stematel.bukusaku.Model.User.PelanggaranPost;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayout error_layout;
    long nis;

    public NotifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notif, container, false);

        UserResponse user = LocalService.getLogin();
        if (user!=null){
            nis = user.getNis();
        }

        recyclerView = (RecyclerView)v.findViewById(R.id.recycle);
        error_layout = (LinearLayout) v.findViewById(R.id.nodatalayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        refresh();

        return v;
    }

    private void refresh() {

        hideErrorView();

        PelanggaranPost post = new PelanggaranPost(nis);
        Call<ResponseService<NotifResponse>> serviceCall = NetworkClient.getInstance().getApiService().postNotif(post);
        serviceCall.enqueue(new Callback<ResponseService<NotifResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<NotifResponse>> call, Response<ResponseService<NotifResponse>> response) {
                if (response.body().getError().equals("false")){
                    List<NotifResponse> notifResponses = response.body().getDatalist();

                    hideErrorView();

                    NotifAdapter notifAdapter = new NotifAdapter(getContext(), notifResponses);
                    recyclerView.setAdapter(notifAdapter);

                } else {
                    ShowErrorView();
                }
            }

            @Override
            public void onFailure(Call<ResponseService<NotifResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowErrorView() {
        if (error_layout.getVisibility() == View.GONE) {
            error_layout.setVisibility(View.VISIBLE);
        }
    }

    private void hideErrorView() {
        if (error_layout.getVisibility() == View.VISIBLE) {
            error_layout.setVisibility(View.GONE);
            error_layout.setVisibility(View.VISIBLE);
        }
    }

}

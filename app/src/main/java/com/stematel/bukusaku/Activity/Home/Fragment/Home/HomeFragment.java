package com.stematel.bukusaku.Activity.Home.Fragment.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stematel.bukusaku.Activity.BukaFitur.BukaFiturActivity;
import com.stematel.bukusaku.Activity.Home.Fragment.Home.content.PelanggaranFragment;
import com.stematel.bukusaku.Activity.Home.Fragment.Home.content.PenghargaanFragment;
import com.stematel.bukusaku.Activity.TambahPoin.TambahPoinActivity;
import com.stematel.bukusaku.Model.Pelanggaran.JumlahPelanggaran;
import com.stematel.bukusaku.Model.User.PelanggaranPost;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Activity.Pelanggaran.PelanggaranActivity;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Service.ResponseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private long nis;
    TextView tvNis,tvKelas,tvNama, tvPoinPelanggaran, tvPoinPenghargaan, btnPelanggaran;
    String nama,kelas;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnPelanggaran = (TextView) view.findViewById(R.id.btnTambah);

        UserResponse user = LocalService.getLogin();
        if (user != null){
            nis = user.getNis();
            nama = user.getNama();
            kelas = user.getKelas();
        }

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = null;
                if(view == view.findViewById(R.id.BtnPelanggaran)){
                    fragment = new PelanggaranFragment();
                } else {
                    fragment = new PenghargaanFragment();
                }
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.output, fragment);
                transaction.commit();
            }
        };

        btnPelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BukaFiturActivity.class));
            }
        });

        LinearLayout btnPelanggaran = (LinearLayout) view.findViewById(R.id.BtnPelanggaran);
        btnPelanggaran.setOnClickListener(listener);
        LinearLayout btnPenghargaan = (LinearLayout) view.findViewById(R.id.BtnPenghargaan);
        btnPenghargaan.setOnClickListener(listener);

        init(view);
        refresh();
        return view;
    }

    private void refresh() {
        PelanggaranPost pelanggaranPost = new PelanggaranPost(nis);
        Call<ResponseService<JumlahPelanggaran>> responseServiceCall = NetworkClient.getInstance().getApiService().postJumlah(pelanggaranPost);
        responseServiceCall.enqueue(new Callback<ResponseService<JumlahPelanggaran>>() {
            @Override
            public void onResponse(Call<ResponseService<JumlahPelanggaran>> call, Response<ResponseService<JumlahPelanggaran>> response) {
                if (response.body().getError().equals("false")){
                    tvPoinPelanggaran.setText(String.valueOf(response.body().getData().getPelanggaran()));
                    tvPoinPenghargaan.setText(String.valueOf(response.body().getData().getPenghargaan()));
                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseService<JumlahPelanggaran>> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void init(View view) {
        tvNis = (TextView) view.findViewById(R.id.tvNis);
        tvNama = (TextView) view.findViewById(R.id.tvNama);
        tvKelas = (TextView) view.findViewById(R.id.tvKelas);
        tvPoinPelanggaran = (TextView) view.findViewById(R.id.tvPoinPelanggaran);
        tvPoinPenghargaan = (TextView) view.findViewById(R.id.tvPoinPenghargaan);


        UserResponse user = LocalService.getLogin();
        if (user != null){
            tvNis.setText(String.valueOf(nis));
            tvNama.setText(nama);
            tvKelas.setText(kelas);
        }
    }

}

package com.stematel.bukusaku.Activity.Pelanggaran;

import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.Model.User.PelanggaranPost;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.Service.PresenterService;
import com.stematel.bukusaku.Service.ResponseService;
import com.stematel.bukusaku.Service.Service;
import com.stematel.bukusaku.Service.ViewService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PelanggaranPresenter extends PresenterService<PelanggaranPresenter.PelanggaranView>{

    public void postNis(long nis){
        PelanggaranPost postNis = new PelanggaranPost(nis);
        getApiView().onLoading();

        Call<ResponseService<PelanggaranResponse>> responseServiceCall = NetworkClient.getInstance().getApiService().postPelanggaran(postNis);
        responseServiceCall.enqueue(new Callback<ResponseService<PelanggaranResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<PelanggaranResponse>> call, Response<ResponseService<PelanggaranResponse>> response) {
                getApiView().ondLoading();
                if (response.code() == 200) {
                    if (response.body().getError().equals("false")){
                        getApiView().onSuccess(response.body());
                    } else {
                        getApiView().onFailed(response.body().getMessage());
                    }

                } else if (response.code() == 520) {

                    String proses1 = response.errorBody().source().toString();
                    proses1 = proses1.replace("[text={\"message\":\"", "");
                    String msgReturn = proses1.replace("\",\"data\":null}]", "");

                    getApiView().onFailed(msgReturn.toString());
                } else {
                    getApiView().onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseService<PelanggaranResponse>> call, Throwable t) {
                getApiView().onFailed(t.getMessage());
            }
        });
    }

    public interface PelanggaranView extends ViewService {
        void onSuccess(ResponseService<PelanggaranResponse> responseService);
    }
}


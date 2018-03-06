package com.stematel.bukusaku.Activity.BukaFitur;

import com.stematel.bukusaku.Model.tambahPoin.TambahPoinPost;
import com.stematel.bukusaku.Model.tambahPoin.VerifPost;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.Service.PresenterService;
import com.stematel.bukusaku.Service.ResponseService;
import com.stematel.bukusaku.Service.ViewService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tofa-pc on 3/4/2018.
 */

public class BukaFiturPresenter extends PresenterService<BukaFiturPresenter.VerifikasiKode> {


    public void postVeriv(String kode){
        VerifPost signinPost = new VerifPost(kode);
        getApiView().onLoading();

        Call<ResponseService> responseServiceCall = NetworkClient.getInstance().getApiService().postKode(signinPost);
        responseServiceCall.enqueue(new Callback<ResponseService>() {
            @Override
            public void onResponse(Call<ResponseService> call, Response<ResponseService> response) {
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
            public void onFailure(Call<ResponseService> call, Throwable t) {
                getApiView().onFailed(t.getMessage());
            }
        });
    }

    public interface VerifikasiKode extends ViewService {
        void onSuccess(ResponseService responseService);
    }
}

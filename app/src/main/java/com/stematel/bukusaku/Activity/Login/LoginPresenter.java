package com.stematel.bukusaku.Activity.Login;

import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.User.PelanggaranPost;
import com.stematel.bukusaku.Model.User.SigninPost;
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
 * Created by tofa-pc on 1/24/2018.
 */

public class LoginPresenter extends PresenterService<LoginPresenter.LoginView> {


    public void postSignin(long nis, String password, int level){
        SigninPost signinPost = new SigninPost(nis, password, level);
        getApiView().onLoading();

        Call<ResponseService<UserResponse>> responseServiceCall = NetworkClient.getInstance().getApiService().postLogin(signinPost);
        responseServiceCall.enqueue(new Callback<ResponseService<UserResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<UserResponse>> call, Response<ResponseService<UserResponse>> response) {
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
            public void onFailure(Call<ResponseService<UserResponse>> call, Throwable t) {
                getApiView().onFailed(t.getMessage());
            }
        });
    }

    public interface LoginView extends ViewService {
        void onSuccess(ResponseService<UserResponse> responseService);
    }
}

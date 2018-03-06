package com.stematel.bukusaku.Activity.ChangePass;

import com.stematel.bukusaku.Model.User.PasswordPost;
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
 * Created by tofa-pc on 2/4/2018.
 */

public class ChangePassPresenter extends PresenterService<ChangePassPresenter.ChangePassView> {

    public void postPassword(Long nis, String email, String password, int level){
        PasswordPost passwordPost = new PasswordPost(nis, email, password, level);
        getApiView().onLoading();

        Call<ResponseService<UserResponse>> responseServiceCall = NetworkClient.getInstance().getApiService().postPassword(passwordPost);
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

    public interface ChangePassView extends ViewService {
        void onSuccess(ResponseService<UserResponse> responseService);
    }
}

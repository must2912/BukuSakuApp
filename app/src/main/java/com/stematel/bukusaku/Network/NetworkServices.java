package com.stematel.bukusaku.Network;

import com.stematel.bukusaku.Model.Notif.NotifResponse;
import com.stematel.bukusaku.Model.Pelanggaran.JumlahPelanggaran;
import com.stematel.bukusaku.Model.Pelanggaran.PelanggaranResponse;
import com.stematel.bukusaku.Model.Peraturan.PagePost;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;
import com.stematel.bukusaku.Model.Upload.FileResponse;
import com.stematel.bukusaku.Model.User.PasswordPost;
import com.stematel.bukusaku.Model.User.PelanggaranPost;
import com.stematel.bukusaku.Model.User.SigninPost;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.Model.tambahPoin.TambahPoinPost;
import com.stematel.bukusaku.Model.tambahPoin.VerifPost;
import com.stematel.bukusaku.Service.ResponseService;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by tofa-pc on 2/3/2018.
 */

public interface NetworkServices {

    @POST("changepass.php")
    Call<ResponseService<UserResponse>> postPassword (@Body PasswordPost passwordPost);

    @POST("login.php")
    Call<ResponseService<UserResponse>> postLogin (@Body SigninPost signinPost);

    @POST("pelanggaran/historipelanggaran.php")
    Call<ResponseService<PelanggaranResponse>> postPelanggaran (@Body PelanggaranPost pelanggaranPost);

    @POST("pelanggaran/historypenghargaan.php")
    Call<ResponseService<PelanggaranResponse>> postPenghargaan (@Body PelanggaranPost pelanggaranPost);

    @POST("pelanggaran/getjumlahpoin.php")
    Call<ResponseService<JumlahPelanggaran>> postJumlah (@Body PelanggaranPost pelanggaranPost);

    @POST("peraturan/getperaturan.php")
    Call<ResponseService<PasalResponse>> getPasal(@Body PagePost pagePost);

    @POST("peraturan/getpenghargaan.php")
    Call<ResponseService<PasalResponse>> getPenghargaan (@Body PagePost pagePost);

    @POST("notif/getNotif.php")
    Call<ResponseService<NotifResponse>> postNotif (@Body PelanggaranPost post);

    @POST("pelanggaran/tambahpoin.php")
    Call<ResponseService> postPoin (@Body TambahPoinPost tambahPoinPost);

    @POST("pelanggaran/verifKode.php")
    Call<ResponseService> postKode (@Body VerifPost verifPost);

    @GET("peraturan/getallpasal.php")
    Call<ResponseService<PasalResponse>> getpasal();

    @Multipart
    @POST("upload.php")
    Call<ResponseService<FileResponse>> uploadPhoto(@Part MultipartBody.Part file, @Part("name") RequestBody name, @Part("kelas") RequestBody kelas, @Part("keterangan") RequestBody keterangan);

}

package com.stematel.bukusaku.Activity.Home.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stematel.bukusaku.Model.Upload.FileResponse;
import com.stematel.bukusaku.Network.NetworkClient;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.ActivityUtils;
import com.stematel.bukusaku.Service.ResponseService;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporFragment extends Fragment {

    private static final int READ_STORAGE_PERMISSION = 4000;
    private static final int LIMIT = 1;

    TextView btnKirim;
    ImageView addphoto;
    EditText EdKeterangan, EdNama, EdKelas;
    String urlPhotoNew = "";

    MultipartBody.Part fileToUpload;
    RequestBody keterangan, nama, kelas;

    public LaporFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ButterKnife.bind(getActivity());
        View v = inflater.inflate(R.layout.fragment_lapor, container, false);

        EdKeterangan = (EditText) v.findViewById(R.id.EdKeterangan);
        EdNama = (EditText) v.findViewById(R.id.EdNama);
        EdKelas = (EditText) v.findViewById(R.id.EdKelas);
        addphoto = (ImageView) v.findViewById(R.id.addPhoto);

        Glide.with(this).load(R.drawable.add)
                .placeholder(R.color.white)
                .override(1280, 1232)
                .crossFade()
                .centerCrop()
                .into(addphoto);

        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!ActivityUtils.checkPermissionForExternalStorage(getActivity())) {
                        ActivityUtils.requestStoragePermission(getActivity(), READ_STORAGE_PERMISSION);
                    } else {
                        // opining custom gallery
                        Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
                        intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, LIMIT);
                        startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
                    }
                }else{
                    Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
                    intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, LIMIT);
                    startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
                }
            }
        });

        btnKirim = (TextView) v.findViewById(R.id.btnKirim);
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keterangan = RequestBody.create(MediaType.parse("text/plain"), EdKeterangan.getText().toString());
                nama = RequestBody.create(MediaType.parse("text/plain"), EdNama.getText().toString());
                kelas = RequestBody.create(MediaType.parse("text/plain"), EdKelas.getText().toString());


                if (EdKeterangan.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Keterangan Harus Diisi", Toast.LENGTH_SHORT).show();

                } else if ( nama==null && kelas==null) {
                    EdNama.setText("Tidak Tahu");
                    EdKelas.setText("Tidak Tahu");
                    kelas = RequestBody.create(MediaType.parse("text/plain"), EdKelas.getText().toString());
                    nama = RequestBody.create(MediaType.parse("text/plain"), EdNama.getText().toString());
                    sendphoto(fileToUpload, nama, kelas, keterangan);
                } else if (nama == null){
                    EdNama.setText("Tidak Tahu");
                    nama = RequestBody.create(MediaType.parse("text/plain"), EdNama.getText().toString());
                    sendphoto(fileToUpload, nama, kelas, keterangan);
                } else if (kelas == null){
                    EdKelas.setText("Tidak Tahu");
                    kelas = RequestBody.create(MediaType.parse("text/plain"), EdNama.getText().toString());
                    sendphoto(fileToUpload, nama, kelas, keterangan);
                } else {
                    sendphoto(fileToUpload, nama, kelas, keterangan);
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);

            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));

                Glide.with(this).load(uri)
                        .placeholder(R.color.colorAccent)
                        .override(400, 280)
                        .crossFade()
                        .centerCrop()
                        .into(addphoto);
                String filePath = ActivityUtils.getRealPathFromURIPath(uri, getActivity());
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            }

        }
    }

    private void sendphoto(MultipartBody.Part uri, RequestBody nama, RequestBody keterangan, RequestBody kelas) {
        Call<ResponseService<FileResponse>> serviceCall = NetworkClient.getInstance().getApiService().uploadPhoto(uri, nama, kelas, keterangan);
        serviceCall.enqueue(new Callback<ResponseService<FileResponse>>() {
            @Override
            public void onResponse(Call<ResponseService<FileResponse>> call, Response<ResponseService<FileResponse>> response) {
                if (response.body().getError().equals("false")){
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseService<FileResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

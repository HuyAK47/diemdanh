package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.adapter.DanhSachHocPhanAdapter;
import com.mta.diemdanhandroid.entity.GiaoVienEntity;
import com.mta.diemdanhandroid.entity.LopHocPhanEntity;
import com.mta.diemdanhandroid.server.ApiConnect;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainGiaoVienActivity extends AppCompatActivity implements DanhSachHocPhanAdapter.HocPhanClickListener{
    private TextView mTvTenGiaoVien;
    private TextView mTvHocVi;
    private RecyclerView mRecyclerView;
    private ImageButton mBtnLogout;

    private OkHttpClient mClient = new OkHttpClient();
    private MediaType mMediaType = MediaType.parse("application/json");

    private String mMaGiaoVien;
    private GiaoVienEntity mGiaoVien;
    private List<LopHocPhanEntity> mListLopHocPhan;
    private DanhSachHocPhanAdapter mDanhSachHocPhanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_giao_vien);

        mMaGiaoVien = getIntent().getStringExtra(KeyUtils.TEN_DANG_NHAP);
        initView();
        getInfoGiaoVien();
        getDanhSachHocPhan();
    }

    private void initView(){
        mTvTenGiaoVien = findViewById(R.id.tv_ho_ten);
        mTvHocVi = findViewById(R.id.tv_hoc_vi);
        mRecyclerView = findViewById(R.id.id_list_lop_hoc_phan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBtnLogout = findViewById(R.id.img_log_out);
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(logout);
                finish();
            }
        });
    }

    private void setDataToHeader(){
        if(mGiaoVien != null){
            mTvTenGiaoVien.setText(getString(R.string.txt_ten_giao_vien, mGiaoVien.getTenGiaoVien()));
            mTvHocVi.setText(getString(R.string.txt_hoc_vi, mGiaoVien.getHocVi()));
        }
    }

    private void getInfoGiaoVien(){
        Moshi moshi = new Moshi.Builder().build();
        Type dataType = Types.newParameterizedType(GiaoVienEntity.class);
        JsonAdapter<GiaoVienEntity> adapter = moshi.adapter(dataType);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", mMaGiaoVien)
                .build();
        Request request = new Request.Builder()
                .url(ApiConnect.API_GET_GIAO_VIEN_BY_ID)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if(json != null){
                    mGiaoVien = adapter.fromJson(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setDataToHeader();
                        }
                    });
                }
            }
        });
    }


    private void getDanhSachHocPhan(){
        Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
        Type dataType = Types.newParameterizedType(List.class, LopHocPhanEntity.class);
        JsonAdapter<List<LopHocPhanEntity>> adapter = moshi.adapter(dataType);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", mMaGiaoVien)
                .build();
        Request request = new Request.Builder()
                .url(ApiConnect.API_GET_TKB_GIAO_VIEN)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if(json != null){
                    mListLopHocPhan = adapter.fromJson(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mDanhSachHocPhanAdapter = new DanhSachHocPhanAdapter(getApplicationContext(), mListLopHocPhan);
                            mDanhSachHocPhanAdapter.setListener(MainGiaoVienActivity.this);
                            mRecyclerView.setAdapter(mDanhSachHocPhanAdapter);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void itemOnClick(int position) {
        Intent intent = new Intent(getApplicationContext(),LopHocPhanGiaoVienActivity.class);
        intent.putExtra(KeyUtils.MA_LOP_HOC_PHAN, mListLopHocPhan.get(position).getMaLopHocPhan());
        intent.putExtra(KeyUtils.TEN_HOC_PHAN, mListLopHocPhan.get(position).getHocPhan().getTenHocPhan());
        intent.putExtra(KeyUtils.SI_SO, mListLopHocPhan.get(position).getSiSo());
        intent.putExtra(KeyUtils.SO_TIN_CHI, mListLopHocPhan.get(position).getHocPhan().getSoTinChi());
        intent.putExtra(KeyUtils.MA_HOC_PHAN, mListLopHocPhan.get(position).getHocPhan().getMaHocPhan());
        startActivity(intent);
    }
}
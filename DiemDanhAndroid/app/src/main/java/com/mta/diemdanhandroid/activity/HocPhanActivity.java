package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.adapter.DanhSachDiemDanhAdapter;
import com.mta.diemdanhandroid.entity.DiemDanhEntity;
import com.mta.diemdanhandroid.entity.DiemEntity;
import com.mta.diemdanhandroid.entity.HocPhanEntity;
import com.mta.diemdanhandroid.server.ApiConnect;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import org.json.JSONException;
import org.json.JSONObject;

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

/*
* HuyNQN activity hoc phan cua sinh vien
* */
public class HocPhanActivity extends AppCompatActivity {
    private TextView mTvMaHocPhan;
    private TextView mTvTenHocPhan;
    private TextView mTvSoTC;
    private TextView mTvDiemCC;
    private TextView mTvDiemTX;
    private TextView mTvDiemThi;
    private TextView mTvSoBuoiDiemDanh;
    private RecyclerView mRecyclerView;

    private HocPhanEntity mHocPhan;
    private DiemEntity mDiemQuaTrinh;
    private List<DiemDanhEntity> mListDiemDanh;
    private DanhSachDiemDanhAdapter mDiemDanhAdapter;

    private OkHttpClient mClient = new OkHttpClient();
    MediaType mMediaType = MediaType.parse("application/json");
    String mMaLHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_phan);
        Bundle bundle = getIntent().getExtras();
        String maHp= bundle.getString(KeyUtils.MA_HOC_PHAN);
        mMaLHP = bundle.getString(KeyUtils.MA_LOP_HOC_PHAN);
        String tenHp= bundle.getString(KeyUtils.TEN_HOC_PHAN);
        int soTC= bundle.getInt(KeyUtils.SO_TIN_CHI);
        mHocPhan = new HocPhanEntity(maHp, tenHp, soTC);
        initView();
        getBangDiem();
        setDataMonHoc();
        getDanhSachDiemDanh();
    }

    private void initView(){
        mTvMaHocPhan = findViewById(R.id.tv_ma_hoc_phan);
        mTvTenHocPhan = findViewById(R.id.tv_ten_hoc_phan);
        mTvSoTC = findViewById(R.id.tv_so_tin_chi);
        mTvDiemCC =  findViewById(R.id.tv_diem_cc);
        mTvDiemTX = findViewById(R.id.tv_diem_tx);
        mTvDiemThi = findViewById(R.id.tv_diem_thi);
        mRecyclerView = findViewById(R.id.id_list_diem_danh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mTvSoBuoiDiemDanh = findViewById(R.id.tv_so_buoi_diem_danh_cua_sv);
    }

    private void setDataMonHoc(){
        mTvMaHocPhan.setText(getString(R.string.txt_ma_hoc_phan, mHocPhan.getMaHocPhan()));
        mTvTenHocPhan.setText(getString(R.string.txt_ten_hoc_phan, mHocPhan.getTenHocPhan()));
        mTvSoTC.setText(getString(R.string.txt_so_tin_chi, mHocPhan.getSoTinChi()));
    }

    private void setDataDiem(){
        mTvDiemCC.setText(getString(R.string.txt_diem_cc, mDiemQuaTrinh.diemCCtoString()));
        mTvDiemTX.setText(getString(R.string.txt_diem_tx, mDiemQuaTrinh.diemTXtoString()));
        mTvDiemThi.setText(getString(R.string.txt_diem_thi, mDiemQuaTrinh.diemThiToString()));
    }

    private void getBangDiem(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String maSinhVien = preferences.getString(KeyUtils.TEN_DANG_NHAP, "");
        Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
        Type typeListDiem = Types.newParameterizedType(DiemEntity.class);
        JsonAdapter<DiemEntity> jsonAdapter = moshi.adapter(typeListDiem);
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.MA_SINH_VIEN, maSinhVien);
            postData.put(KeyUtils.MA_HOC_PHAN, mHocPhan.getMaHocPhan());
            postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLHP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(mMediaType, postData.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_GET_DIEM_ONLY_STUDENT)
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
                    mDiemQuaTrinh = jsonAdapter.fromJson(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setDataDiem();
                        }
                    });
                }
            }
        });
    }

    private void getDanhSachDiemDanh(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String maSinhVien = preferences.getString(KeyUtils.TEN_DANG_NHAP, "");
        Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
        Type type = Types.newParameterizedType(List.class, DiemDanhEntity.class);
        JsonAdapter<List<DiemDanhEntity>> jsonAdapter = moshi.adapter(type);
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.MA_SINH_VIEN, maSinhVien);
            postData.put(KeyUtils.MA_HOC_PHAN, mHocPhan.getMaHocPhan());
            postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLHP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(mMediaType, postData.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_GET_CHECK_IN_SUBJECT)
                .post(body)
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
                    mListDiemDanh = jsonAdapter.fromJson(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mDiemDanhAdapter = new DanhSachDiemDanhAdapter(getApplicationContext(), mListDiemDanh);
                            mRecyclerView.setAdapter(mDiemDanhAdapter);

                            float soTietDiemDanh = 0.0f;
                            for (DiemDanhEntity entity : mListDiemDanh){
                                if(!TextUtils.isEmpty(entity.getKieuTietHoc())) {
                                    soTietDiemDanh += Float.valueOf(entity.getKieuTietHoc());
                                }
                            }
                            mTvSoBuoiDiemDanh.setText(getString(R.string.txt_so_tiet_diem_danh, String.valueOf(soTietDiemDanh)));
                        }
                    });
                }
            }
        });
    }
}
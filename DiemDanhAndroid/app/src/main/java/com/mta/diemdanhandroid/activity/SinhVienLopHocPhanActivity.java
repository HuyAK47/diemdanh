package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.adapter.DanhSachDiemDanhAdapter;
import com.mta.diemdanhandroid.entity.DiemDanhEntity;
import com.mta.diemdanhandroid.entity.DiemEntity;
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

public class SinhVienLopHocPhanActivity extends AppCompatActivity implements DanhSachDiemDanhAdapter.ItemDiemDanhListener {
    private TextView mTvHoTenSinhVien;
    private TextView mTvMaSinhVien;
    private TextView mTvDiemCC;
    private TextView mTvDiemTX;
    private TextView mTvDiemThi;
    private TextView mTvSoBuoiDiemDanh;
    private RecyclerView mRecyclerView;
    private ImageButton mBtnCapNhatDiem;

    private DiemEntity mDiemQuaTrinh;
    private OkHttpClient mClient = new OkHttpClient();
    MediaType mMediaType = MediaType.parse("application/json");
    private String mMaLopHocPhan;
    private String mMaSinhVien;
    private String mTenSinhVien;
    private String mMaHocPhan;
    private List<DiemDanhEntity> mListDiemDanh;
    private DanhSachDiemDanhAdapter mDiemDanhAdapter;
    private DiemDanhEntity mDiemDanhEntity;
    private AlertDialog mAlertDialogCapNhatDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien_lop_hoc_phan);
        Bundle bundle = getIntent().getExtras();
        mMaLopHocPhan = bundle.getString(KeyUtils.MA_LOP_HOC_PHAN);
        mMaSinhVien = bundle.getString(KeyUtils.MA_SINH_VIEN);
        mTenSinhVien = bundle.getString(KeyUtils.TEN_SINH_VIEN);
        mMaHocPhan = bundle.getString(KeyUtils.MA_HOC_PHAN);

        initView();
        getBangDiem();
        new GetListCheckinAsynTask().execute();
    }

    private void initView(){
        mTvHoTenSinhVien = findViewById(R.id.tv_ten_sv);
        mTvHoTenSinhVien.setText(getString(R.string.txt_ho_ten, mTenSinhVien));
        mTvMaSinhVien = findViewById(R.id.tv_ma_sv);
        mTvMaSinhVien.setText(getString(R.string.txt_ma_sinh_vien, mMaSinhVien));
        mTvDiemCC =  findViewById(R.id.tv_diem_cc);
        mTvDiemTX = findViewById(R.id.tv_diem_tx);
        mTvDiemThi = findViewById(R.id.tv_diem_thi);
        mRecyclerView = findViewById(R.id.id_list_diem_danh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mTvSoBuoiDiemDanh = findViewById(R.id.tv_so_buoi_diem_danh_cua_sv);
        mBtnCapNhatDiem = findViewById(R.id.img_cap_nhat_diem);
        mBtnCapNhatDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCapNhatDiem();
            }
        });
    }

    private void setDataDiem(){
        mTvDiemCC.setText(getString(R.string.txt_diem_cc, mDiemQuaTrinh.diemCCtoString()));
        mTvDiemTX.setText(getString(R.string.txt_diem_tx, mDiemQuaTrinh.diemTXtoString()));
        mTvDiemThi.setText(getString(R.string.txt_diem_thi, mDiemQuaTrinh.diemThiToString()));
    }

    private void getBangDiem(){
        Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
        Type typeListDiem = Types.newParameterizedType(DiemEntity.class);
        JsonAdapter<DiemEntity> jsonAdapter = moshi.adapter(typeListDiem);
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.MA_SINH_VIEN, mMaSinhVien);
            postData.put(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
            postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
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


    @Override
    public void onClickItemDiemDanh(View view, DiemDanhEntity entity, int position) {
        mDiemDanhEntity = entity;
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view, Gravity.END);
        popupMenu.inflate(R.menu.context_menu_item_diem_danh);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete_check_in:
                        new DeleteCheckInAsynTask().execute();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void xoaBuoiDiemDanh(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", String.valueOf(mDiemDanhEntity.getId()))
                .build();
        Request request = new Request.Builder()
                .url(ApiConnect.API_POST_XOA_BUOI_DIEM_DANH)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                new GetListCheckinAsynTask().execute();
            }
        });
    }

    private class DeleteCheckInAsynTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            xoaBuoiDiemDanh();
            return null;
        }
    }

    private class GetListCheckinAsynTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            getDanhSachDiemDanh();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }

        private void getDanhSachDiemDanh(){
            Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
            Type type = Types.newParameterizedType(List.class, DiemDanhEntity.class);
            JsonAdapter<List<DiemDanhEntity>> jsonAdapter = moshi.adapter(type);
            JSONObject postData = new JSONObject();
            try {
                postData.put(KeyUtils.MA_SINH_VIEN, mMaSinhVien);
                postData.put(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
                postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
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
                                mDiemDanhAdapter.setListener(SinhVienLopHocPhanActivity.this);
                                mRecyclerView.setAdapter(mDiemDanhAdapter);

                                float soTietDiemDanh = 0.0f;
                                for (DiemDanhEntity entity : mListDiemDanh){
                                    soTietDiemDanh += Float.valueOf(entity.getKieuTietHoc());
                                }
                                mTvSoBuoiDiemDanh.setText(getString(R.string.txt_so_tiet_diem_danh, String.valueOf(soTietDiemDanh)));
                            }
                        });
                    }
                }
            });
        }
    }


    private TextInputEditText mEdtDiemCC;
    private TextInputEditText mEdtDiemTX;
    private TextInputEditText mEdtDiemThi;
    private void DialogCapNhatDiem(){
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_update_diem, null);
        mEdtDiemCC = view.findViewById(R.id.edt_diem_cc);
        mEdtDiemCC.setText(mDiemQuaTrinh.diemCCtoString());
        mEdtDiemTX = view.findViewById(R.id.edt_diem_tx);
        mEdtDiemTX.setText(mDiemQuaTrinh.diemTXtoString());
        mEdtDiemThi = view.findViewById(R.id.edt_diem_thi);
        mEdtDiemThi.setText(mDiemQuaTrinh.diemThiToString());
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialogCapNhatDiem.cancel();
            }
        });
        Button btnUpdate = view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhatBangDiem();
            }
        });

        mAlertDialogCapNhatDiem = new AlertDialog.Builder(SinhVienLopHocPhanActivity.this).create();
        mAlertDialogCapNhatDiem.setTitle("Update diem");
        mAlertDialogCapNhatDiem.setView(view);
        mAlertDialogCapNhatDiem.setCancelable(false);
        mAlertDialogCapNhatDiem.show();
    }

    private void capNhatBangDiem(){
        Moshi moshi = new Moshi.Builder().build();
        Type typeListDiem = Types.newParameterizedType(DiemEntity.class);
        JsonAdapter<DiemEntity> jsonAdapter = moshi.adapter(typeListDiem);
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.ID, String.valueOf(mDiemQuaTrinh.getId()));
            postData.put(KeyUtils.MA_SINH_VIEN, mMaSinhVien);
            postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
            postData.put(KeyUtils.DIEM_CHUYEN_CAN, updateDiemCC());
            postData.put(KeyUtils.DIEM_THUONG_XUYEN, updateDiemTX());
            postData.put(KeyUtils.DIEM_THI, updateDiemThi());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(mMediaType, postData.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_POST_CAP_NHAT_DIEM)
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

    private String updateDiemCC(){
        String diemCC = mEdtDiemCC.getText().toString();
        if(!TextUtils.isEmpty(diemCC)){
            return diemCC;
        }
        return "";
    }

    private String updateDiemTX(){
        String diemCC = mEdtDiemTX.getText().toString();
        if(!TextUtils.isEmpty(diemCC)){
            return diemCC;
        }
        return "";
    }

    private String updateDiemThi(){
        String diemCC = mEdtDiemThi.getText().toString();
        if(!TextUtils.isEmpty(diemCC)){
            return diemCC;
        }
        return "";
    }

}
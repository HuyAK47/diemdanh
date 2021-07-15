package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.SessionService;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.adapter.DanhSachHocPhanAdapter;
import com.mta.diemdanhandroid.entity.HocPhanEntity;
import com.mta.diemdanhandroid.entity.LopHocPhanEntity;
import com.mta.diemdanhandroid.entity.SinhVienEntity;
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

public class MainSinhVienActivity extends AppCompatActivity implements View.OnClickListener, DanhSachHocPhanAdapter.HocPhanClickListener {
    private static final int TIME_SHOWING_BLUETOOTH = 120;
    private TextView mTvHoTen;
    private TextView mTvMaSv;
    private TextView mTvLopCn;
    private RecyclerView mRecyclerView;
    private ImageButton mBtnLogout;
    private ImageButton mBtnShowBluetooth;

    private String mMaSv;
    private SinhVienEntity mSinhVienEntity;
    private List<LopHocPhanEntity> mListLopHocPhan;
    private DanhSachHocPhanAdapter mHocPhanAdapter;

    private OkHttpClient mClient = new OkHttpClient();
    MediaType mMediaType = MediaType.parse("application/json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sinh_vien);

        mMaSv = getIntent().getStringExtra(KeyUtils.TEN_DANG_NHAP);

//        Intent intent = new Intent(getApplicationContext(), SessionService.class);
//        startService(intent);

        initView();
        registerReceiverBL();
        getSinhVienById();
        getThoiKhoaBieu();
    }

    private void initView() {
        mTvHoTen = findViewById(R.id.tv_ho_ten);
        mTvMaSv = findViewById(R.id.tv_ma_sv);
        mTvLopCn = findViewById(R.id.tv_lop);
        mRecyclerView = findViewById(R.id.id_list_lop_hoc_phan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBtnLogout = findViewById(R.id.img_log_out);
        mBtnLogout.setOnClickListener(this);
        mBtnShowBluetooth = findViewById(R.id.img_chia_se_bluetooth);
        mBtnShowBluetooth.setOnClickListener(this);
    }

    // MTA HuyNQn Lay ra thong tin sinh vien
    private void getSinhVienById() {
        if (!TextUtils.isEmpty(mMaSv)) {
            final Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
            Type typeSv = Types.newParameterizedType(SinhVienEntity.class);
            JsonAdapter<SinhVienEntity> jsonAdapter = moshi.adapter(typeSv);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", mMaSv)
                    .build();
            Request request = new Request.Builder()
                    .url(ApiConnect.API_GET_SINH_VIEN_BY_ID)
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
                    if (json != null) {
                        mSinhVienEntity = jsonAdapter.fromJson(json);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvHoTen.setText(getString(R.string.txt_ho_ten, mSinhVienEntity.getTenSinhVien()));
                                mTvMaSv.setText(getString(R.string.txt_ma_sinh_vien, mSinhVienEntity.getMaSinhVien()));
                                mTvLopCn.setText(getString(R.string.txt_lop, mSinhVienEntity.getMaChuyenNganh()));
                            }
                        });
                    }
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.error_get_ma_sv), Toast.LENGTH_SHORT).show();
        }
    }

    // MTA HuyNQn Lay ra thong tin hoc phan cua hoc ki hien tai
    private void getThoiKhoaBieu() {
        if (!TextUtils.isEmpty(mMaSv)) {
            final Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
            Type typeSv = Types.newParameterizedType(List.class, LopHocPhanEntity.class);
            JsonAdapter<List<LopHocPhanEntity>> jsonAdapter = moshi.adapter(typeSv);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", mMaSv)
                    .build();
            Request request = new Request.Builder()
                    .url(ApiConnect.API_GET_TKB_SINH_VIEN)
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
                    if (json != null) {
                        mListLopHocPhan = jsonAdapter.fromJson(json);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mHocPhanAdapter = new DanhSachHocPhanAdapter(getApplicationContext(), mListLopHocPhan);
                                mHocPhanAdapter.setListener(MainSinhVienActivity.this);
                                mRecyclerView.setAdapter(mHocPhanAdapter);
                            }
                        });
                    }
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.error_get_ma_sv), Toast.LENGTH_SHORT).show();
        }
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        if(mAlertDialog != null) {
                            mAlertDialog.cancel();
                        }
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        showDialogCheckIn();
                        break;
                }
            }
        }
    };

    private void registerReceiverBL() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_chia_se_bluetooth:
                Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, TIME_SHOWING_BLUETOOTH);
                startActivityForResult(discoverableIntent, 99);
                break;
            case R.id.img_log_out:
                Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(logout);
                finish();
                break;
        }
    }

    @Override
    public void itemOnClick(int position) {
        String maHp = mListLopHocPhan.get(position).getMaHocPhan();
        String maLHP = mListLopHocPhan.get(position).getMaLopHocPhan();
        String tenHP = mListLopHocPhan.get(position).getHocPhan().getTenHocPhan();
        int soTC = mListLopHocPhan.get(position).getHocPhan().getSoTinChi();
        Intent intent = new Intent(getApplicationContext(), HocPhanActivity.class);
        Bundle hocPhan = new Bundle();
        hocPhan.putString(KeyUtils.MA_HOC_PHAN, maHp);
        hocPhan.putString(KeyUtils.TEN_HOC_PHAN, tenHP);
        hocPhan.putString(KeyUtils.MA_LOP_HOC_PHAN, maLHP);
        hocPhan.putInt(KeyUtils.SO_TIN_CHI, soTC);
        intent.putExtras(hocPhan);
        startActivity(intent);
    }

    private AlertDialog mAlertDialog;
    private void showDialogCheckIn(){
        mAlertDialog = new AlertDialog.Builder(MainSinhVienActivity.this).create();
        mAlertDialog.setTitle(getString(R.string.txt_diem_danh));
        mAlertDialog.setMessage(getString(R.string.txt_dang_thuc_hien_diem_danh));
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
    }
}
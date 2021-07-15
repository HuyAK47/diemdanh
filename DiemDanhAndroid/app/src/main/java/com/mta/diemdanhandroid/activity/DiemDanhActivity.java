package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.Utils.FilterExecutorsUtils;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.adapter.DanhSachSinhVienAdapter;
import com.mta.diemdanhandroid.entity.BluetoothEntity;
import com.mta.diemdanhandroid.entity.SinhVienEntity;
import com.mta.diemdanhandroid.server.ApiConnect;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiemDanhActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int COUNT_CHECK_IN = 1;
    private Spinner mSpnKieuDiemDanh;
    private Spinner mSpnKieuTietHoc;
    private Button mBtnQuet;
    private Button mBtnDiemDanh;
    private TextView mTvTenHocPhan;
    private TextView mTvMaLopHocPhan;
    private TextView mTvSiSo;
    private TextView mTvSoTinChi;
    private RecyclerView mRecyclerView;
    private DanhSachSinhVienAdapter mDanhSachSinhVienAdapter;
    private List<SinhVienEntity> mListSinhVienScan;
    private List<SinhVienEntity> mListSinhVien;// MTA HuyNQn danh sach sinh vien cua lop hoc
    private ArrayList<BluetoothDevice> mListBluetoothDevices = new ArrayList<>();
    private List<BluetoothEntity> mListBluetoothEntityScan = new ArrayList<>();// MTA HuyNQn danh sach quet dươc
    private List<BluetoothEntity> mListBluetoothEntityDefault = new ArrayList<>();// MTA HuyNQn danh sach dung cho logic khong diem danh
    private BluetoothAdapter mBluetoothAdapter;
    private int mCountCheckIn = 0;
    private OkHttpClient mClient = new OkHttpClient();
    private String mMaLopHocPhan;
    private String mTenLopHocPhan;
    private int mSiSo;
    private int mSoTinChi;
    private ArrayList<String> mListKieuDiemDanh;
    private ArrayList<String> mListKieuTietHoc;
    private String mKieuDiemDanh;// MTA HuyNQn gia tri lay ra tu spinner
    private String mKieuTietHoc;// MTA HuyNQn gia tri lay ra tu spinner
    private List<String> mDanhSachSinhVienTrungMac;
    private int mSoChukyQuet = 1;
    private void setSoChukyQuet(int siSo){
        if(siSo % 40 > 1){
            mSoChukyQuet = (siSo/40) + 1;
        }
    }
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            Log.d("HuyNQn", "onReceive: "+action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                Log.d("HuyNQn", "onReceive: name: "+device.getName()+"  RSSI: " + rssi + "dBm");
                filter(device);
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
//                mTvStatus.setText("Scanning...");
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
//                mTvStatus.setText("Scan finished");
                if (mCountCheckIn < COUNT_CHECK_IN) {
                    mCountCheckIn++;
                    mBluetoothAdapter.startDiscovery();
                } else {
                    if(mAlertDialogScanDevice != null) {
                        mAlertDialogScanDevice.cancel();
                    }
                    FilterStudentAsyncTask asyncTask = new FilterStudentAsyncTask();
                    asyncTask.execute();
                }
            } else if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
//                        mTvStatus.setText("Showing STATE_OFF");
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
//                        mTvStatus.setText("Showing... STATE_ON");
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh);

        Intent intent = getIntent();
        mMaLopHocPhan = intent.getStringExtra(KeyUtils.MA_LOP_HOC_PHAN);
        mTenLopHocPhan = intent.getStringExtra(KeyUtils.TEN_HOC_PHAN);
        mSiSo = intent.getIntExtra(KeyUtils.SI_SO, 0);
        mSoTinChi = intent.getIntExtra(KeyUtils.SO_TIN_CHI, 0);
        setSoChukyQuet(mSiSo);
        initDataSpinner();
        initView();
        setupBluetooth();
        registerReceiverBL();
        getListStudentInClass();
    }

    private void initView() {
        mTvMaLopHocPhan = findViewById(R.id.tv_ma_lop_hoc_phan);
        mTvMaLopHocPhan.setText(getString(R.string.txt_ma_lop_hoc_phan, mMaLopHocPhan));
        mTvTenHocPhan = findViewById(R.id.tv_ten_hoc_phan);
        mTvTenHocPhan.setText(getString(R.string.txt_ten_hoc_phan, mTenLopHocPhan));
        mTvSiSo = findViewById(R.id.tv_si_so);
        mTvSiSo.setText(getString(R.string.txt_si_so, mSiSo));
        mTvSoTinChi = findViewById(R.id.tv_so_tin_chi);
        mTvSoTinChi.setText(getString(R.string.txt_so_tin_chi, mSoTinChi));
        mBtnDiemDanh = findViewById(R.id.btn_diem_danh);
        mBtnDiemDanh.setOnClickListener(this);
        mBtnQuet = findViewById(R.id.btn_quet);
        mBtnQuet.setOnClickListener(this);
        mRecyclerView = findViewById(R.id.list_sinh_vien);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void initDataSpinner() {
        mListKieuDiemDanh = new ArrayList<>();
        mListKieuDiemDanh.add(getString(R.string.txt_diem_danh_mot_tiet));
        mListKieuDiemDanh.add(getString(R.string.txt_diem_danh_ca_buoi));
        mListKieuDiemDanh.add(getString(R.string.txt_diem_danh_dau_buoi));
        mListKieuDiemDanh.add(getString(R.string.txt_diem_danh_cuoi_buoi));
        mListKieuDiemDanh.add(getString(R.string.txt_khong_diem_danh));

        mSpnKieuDiemDanh = findViewById(R.id.sp_kieu_diem_danh);
        ArrayAdapter<String> arrayAdapterKieuDiemDanh = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, mListKieuDiemDanh);
        arrayAdapterKieuDiemDanh.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpnKieuDiemDanh.setAdapter(arrayAdapterKieuDiemDanh);
        mSpnKieuDiemDanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mKieuDiemDanh = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mListKieuTietHoc = new ArrayList<>();
        mListKieuTietHoc.add(getString(R.string.txt_1_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_1_5_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_2_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_2_5_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_3_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_4_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_5_tiet));
        mListKieuTietHoc.add(getString(R.string.txt_6_tiet));

        mSpnKieuTietHoc = findViewById(R.id.sp_so_tiet_hoc);
        ArrayAdapter<String> arrayAdapterSoTiet = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, mListKieuTietHoc);
        arrayAdapterSoTiet.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpnKieuTietHoc.setAdapter(arrayAdapterSoTiet);
        mSpnKieuTietHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mKieuTietHoc = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Device not support", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    }

    private void registerReceiverBL() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastReceiver, filter);
    }

    private void filter(BluetoothDevice device) {
        FilterExecutorsUtils.runOnBGThread(new Runnable() {
            @Override
            public void run() {
                if (!mListBluetoothDevices.contains(device)) {
                    mListBluetoothDevices.add(device);
                }
            }
        });
    }

    // MTA HuyNQn kiem tra xem thiet bi quet duoc co phai sinh vien trong lop do hay ko
    private SinhVienEntity checkSinhVien(BluetoothDevice device) {
        if (TextUtils.isEmpty(device.getName())) {
            return null;
        }
        for (SinhVienEntity entity : mListSinhVien) {
            if (entity.getMaSinhVien().contains(device.getName())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_quet:
                mCountCheckIn = 0;
                mListSinhVienScan = new ArrayList<>();
                mBluetoothAdapter.startDiscovery();
                showDialogScanDevice();
                break;
            case R.id.btn_diem_danh:
                showDialogConfirmBeforeCheckIn();
                break;
        }
    }

    private void getListStudentInClass() {
        Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
        Type dataType = Types.newParameterizedType(List.class, SinhVienEntity.class);
        JsonAdapter<List<SinhVienEntity>> adapter = moshi.adapter(dataType);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", mMaLopHocPhan)
                .build();
        Request request = new Request.Builder()
                .url(ApiConnect.API_GET_DANH_SACH_SINH_VIEN_LHP)
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
                    mListSinhVien = adapter.fromJson(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (SinhVienEntity sv : mListSinhVien) {
                                BluetoothEntity entity = new BluetoothEntity(sv.getMaSinhVien(), KeyUtils.MAC_DEVICE_DEFAULT);
                                mListBluetoothEntityDefault.add(entity);
                            }
                        }
                    });
                }
            }
        });
    }

    // MTA HuyNQn ===============luong tien hanh loc du lieu sinh vien quet duoc==============
    private class FilterStudentAsyncTask extends AsyncTask<ArrayList<BluetoothDevice>, Void, List<SinhVienEntity>> {
        @Override
        protected List<SinhVienEntity> doInBackground(ArrayList<BluetoothDevice>... arrayLists) {
            for (BluetoothDevice device : mListBluetoothDevices) {
                SinhVienEntity entity = checkSinhVien(device);
                if (entity != null) {
                    mListSinhVienScan.add(entity);
                    BluetoothEntity bluetoothEntity = new BluetoothEntity(device.getName(), device.getAddress());
                    mListBluetoothEntityScan.add(bluetoothEntity);
                }
            }
            return mListSinhVienScan;
        }

        @Override
        protected void onPostExecute(List<SinhVienEntity> listSinhVien) {
            mDanhSachSinhVienAdapter = new DanhSachSinhVienAdapter(getApplicationContext(), mListSinhVienScan, mListBluetoothEntityScan);
            mRecyclerView.setAdapter(mDanhSachSinhVienAdapter);
        }
    }

    // MTA HuyNQn =======================luồng tiến hành điểm danh=========================
    private class CheckInAsynTask extends AsyncTask<Void, Void, Void> {
        private JSONObject jsonObject;
        private MediaType mediaType = MediaType.parse("application/json");
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDanhSachSinhVienTrungMac = new ArrayList<>();
            if(mKieuDiemDanh.equals(getString(R.string.txt_khong_diem_danh))){
                for (BluetoothEntity entity : mListBluetoothEntityDefault) {
                    checkIn(entity);
                }
            }else {
                for (BluetoothEntity entity : mListBluetoothEntityScan) {
                    for (BluetoothEntity entity1 : mListBluetoothEntityScan) {
                        if(!entity.getNameDevice().equals(entity1.getNameDevice()) &&
                        entity.getMacDevice().equals(entity1.getMacDevice())){
                            mDanhSachSinhVienTrungMac.add("MaSV: "+entity.getNameDevice() + "Mac: " +entity.getMacDevice());
                        }else {
                            checkIn(entity);
                        }
                    }
                }
            }
            capNhatSoTietHocDiemDanh();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAlertDialogCheckIn.cancel();
            if (mDanhSachSinhVienTrungMac.size() > 0) {
                showDialogDoubleMac();
            }
        }

        private void checkIn(BluetoothEntity entity) {
            jsonObject = new JSONObject();
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(date);

            try {
                jsonObject.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
                jsonObject.put(KeyUtils.MA_SINH_VIEN, entity.getNameDevice());
                jsonObject.put(KeyUtils.NGAY_DIEM_DANH, formattedDate);
                jsonObject.put(KeyUtils.MAC_DEVICE, entity.getMacDevice());
                jsonObject.put(KeyUtils.KIEU_DIEM_DANH, mKieuDiemDanh);
                jsonObject.put(KeyUtils.KIEU_TIET_HOC, mKieuTietHoc);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(ApiConnect.API_POST_LHP_DIEM_DANH)
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
                }
            });
        }

        private void capNhatSoTietHocDiemDanh(){
            jsonObject = new JSONObject();
            try {
                jsonObject.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
                jsonObject.put(KeyUtils.TONG_SO_TIET_HOC_DIEM_DANH, mKieuTietHoc);
            }catch (JSONException e){
                e.printStackTrace();
            }

            RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(ApiConnect.API_POST_CAP_NHAT_LHP_VA_SO_TIET_DIEM_DANH)
                    .build();
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                }
            });
        }
    }

    private AlertDialog mAlertDialogCheckIn;

    private void showDialogCheckIn() {
        mAlertDialogCheckIn = new AlertDialog.Builder(DiemDanhActivity.this).create();
        mAlertDialogCheckIn.setTitle(getString(R.string.txt_diem_danh));
        mAlertDialogCheckIn.setMessage(getString(R.string.txt_dang_thuc_hien_diem_danh));
        mAlertDialogCheckIn.setCancelable(false);
        mAlertDialogCheckIn.show();
    }

    private AlertDialog mAlertDialogScanDevice;
    private void showDialogScanDevice() {
        mAlertDialogScanDevice = new AlertDialog.Builder(DiemDanhActivity.this).create();
        mAlertDialogScanDevice.setTitle(getString(R.string.txt_scan_device));
        mAlertDialogScanDevice.setMessage(getString(R.string.txt_dang_thuc_hien_quet_thiet_bi));
        mAlertDialogScanDevice.setCancelable(false);
        mAlertDialogScanDevice.show();
    }

    private AlertDialog mAlertDialogDoubleMac;
    private void showDialogDoubleMac() {
        mAlertDialogDoubleMac = new AlertDialog.Builder(DiemDanhActivity.this)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialogDoubleMac.cancel();
                    }
                }).create();
        mAlertDialogDoubleMac.setTitle(getString(R.string.txt_double_device));
        String message = "";
        for (String str : mDanhSachSinhVienTrungMac){
            message += str +"\n";
        }
        mAlertDialogDoubleMac.setMessage(message);
        mAlertDialogDoubleMac.setCancelable(false);
        mAlertDialogDoubleMac.show();
    }

    private void showDialogConfirmBeforeCheckIn() {
        AlertDialog mAlertDialogConfirmBeforeCheckIn = new AlertDialog.Builder(DiemDanhActivity.this)
                .setPositiveButton("Điểm danh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CheckInAsynTask checkInAsynTask = new CheckInAsynTask();
                        showDialogCheckIn();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                checkInAsynTask.execute();
                            }
                        }, 1000);
                    }
                }).setNegativeButton("Đặt lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        mAlertDialogConfirmBeforeCheckIn.setCancelable(false);
        mAlertDialogConfirmBeforeCheckIn.setTitle(getString(R.string.txt_xac_nhan_truoc_diem_danh));
        mAlertDialogConfirmBeforeCheckIn.setMessage(getString(R.string.txt_mesage_xac_nhan_truoc_diem_danh, mKieuDiemDanh, mKieuTietHoc));
        mAlertDialogConfirmBeforeCheckIn.show();
    }
}
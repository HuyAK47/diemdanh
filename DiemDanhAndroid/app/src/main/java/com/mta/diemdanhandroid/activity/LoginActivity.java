package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.Utils.PermissionUtils;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.entity.TaiKhoanEntity;
import com.mta.diemdanhandroid.server.ApiConnect;
import com.scottyab.rootbeer.RootBeer;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText mEdtTenDangNhap;
    private TextInputEditText mEdtMatKhau;
    private Button mBtnDangNhap;
    private TextView mTxtChangePassword;
    private TaiKhoanEntity mTaiKhoan;
    private BluetoothAdapter mBluetoothAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mCheckRoot = "";
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(!mTaiKhoan.getTenDangNhap().equals(mBluetoothAdapter.getName())){
                mBluetoothAdapter.setName(mTaiKhoan.getTenDangNhap());
                mHandler.postDelayed(mRunnable, 300);
            }else {
                mHandler.removeCallbacks(mRunnable);
                goToActivity();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCheckRoot = CheckRoot();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initView();
        if(!PermissionUtils.hasPermissions(getApplicationContext())){
            ActivityCompat.requestPermissions(this, PermissionUtils.PERMISSIONS, PermissionUtils.PERMISSION_CODE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkBluetooth();
    }

    private void initView(){
        mEdtTenDangNhap = findViewById(R.id.edt_ten_dang_nhap);
        mEdtMatKhau = findViewById(R.id.edt_mat_khau);
        mTxtChangePassword = findViewById(R.id.id_text_change_password);
        mTxtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDoiMatKhau();
            }
        });
        mBtnDangNhap = findViewById(R.id.btn_dang_nhap);
        mBtnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootBeer rootBeer = new RootBeer(getApplicationContext());
                if(rootBeer.checkSuExists() || rootBeer.checkForSuBinary() || rootBeer.checkForRootNative()){
                    CheckRoot();
                }else {
                    checkLogin();
                }
            }
        });
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mSharedPreferences.edit();
        setDataPreferences();

    }

    private void setDataPreferences(){
        String taiKhoan = mSharedPreferences.getString(KeyUtils.TEN_DANG_NHAP, "");
        String matKhau = mSharedPreferences.getString(KeyUtils.MAT_KHAU, "");
        if(!TextUtils.isEmpty(taiKhoan)){
            mEdtTenDangNhap.setText(taiKhoan);
            if(!TextUtils.isEmpty(matKhau)){
                mEdtMatKhau.setText(matKhau);
            }
        }
    }

    private void checkLogin(){
        String tenDangNhap = mEdtTenDangNhap.getText().toString();
        String matKhau = mEdtMatKhau.getText().toString();
       if(TextUtils.isEmpty(tenDangNhap)){
           mEdtTenDangNhap.setError(getString(R.string.err_not_null));
       }else if(TextUtils.isEmpty(mEdtMatKhau.getText().toString())){
           mEdtMatKhau.setError(getString(R.string.err_not_null));
       }else {
           login(tenDangNhap, matKhau);
       }
    }

    private void login(String tenDangNhap, String matKhau){
        Moshi moshi = new Moshi.Builder().build();
        Type taiKhoanType = Types.newParameterizedType(TaiKhoanEntity.class);
        JsonAdapter<TaiKhoanEntity> taiKhoanAdapter = moshi.adapter(taiKhoanType);
        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.TEN_DANG_NHAP, tenDangNhap);
            postData.put(KeyUtils.MAT_KHAU, matKhau);
        }catch (JSONException e){
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(mediaType, postData.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_LOGIN)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mess = e.getMessage();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if(json != null){
                    mTaiKhoan = taiKhoanAdapter.fromJson(json);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(TextUtils.isEmpty(mTaiKhoan.getTenDangNhap()) && TextUtils.isEmpty(mTaiKhoan.getMatKhau())){
                                Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                            }else{
                                mEditor.clear();
                                mEditor.putString(KeyUtils.TEN_DANG_NHAP, mTaiKhoan.getTenDangNhap());
                                mEditor.putString(KeyUtils.MAT_KHAU, mTaiKhoan.getMatKhau());
                                mEditor.putString(KeyUtils.QUYEN, mTaiKhoan.getMaQuyen());
                                mEditor.commit();
                                renameBluetoothDevice();
                            }
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void goToActivity(){
        Intent intent = null;
        if(mTaiKhoan.getMaQuyen().equals(KeyUtils.MA_QUYEN_SV)){
            intent = new Intent(getApplicationContext(), MainSinhVienActivity.class);
        }else if(mTaiKhoan.getMaQuyen().equals(KeyUtils.MA_QUYEN_GV)){
            intent = new Intent(getApplicationContext(), MainGiaoVienActivity.class);
        }
        intent.putExtra(KeyUtils.TEN_DANG_NHAP, mTaiKhoan.getTenDangNhap());
        startActivity(intent);
        finish();
    }
    
    private void checkBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            Toast.makeText(this, getString(R.string.not_bluettoth_in_device), Toast.LENGTH_SHORT).show();
            finish();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    }

    private void renameBluetoothDevice(){
        if(mBluetoothAdapter.getName().equals(mTaiKhoan.getTenDangNhap())){
            goToActivity();
        }else {
            mHandler.postDelayed(mRunnable, 100);
        }
    }

    private AlertDialog mAlertDialogChanPassword;
    private void showDialogDoiMatKhau(){
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_change_password, null);
        TextInputEditText edtTenDangNhap = view.findViewById(R.id.edt_ten_dang_nhap);
        TextInputEditText edtMatKhau = view.findViewById(R.id.edt_mat_khau);
        TextInputEditText edtMatKhauMoi = view.findViewById(R.id.edt_mat_khau_moi);
        TextInputEditText edtXacNhanMatKhauMoi = view.findViewById(R.id.edt_xac_nhan_mat_khau_moi);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialogChanPassword.cancel();
            }
        });
        Button btnUpdate = view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtTenDangNhap.getText().toString();
                String matKhauCu = edtMatKhau.getText().toString();
                String matKhauMoi = edtMatKhauMoi.getText().toString();
                String xacNhanMatKhau = edtXacNhanMatKhauMoi.getText().toString();
                if(TextUtils.isEmpty(tenDangNhap)){
                    edtTenDangNhap.setError(getString(R.string.err_not_null));
                }
                if(TextUtils.isEmpty(matKhauCu)){
                    edtMatKhau.setError(getString(R.string.err_not_null));
                }
                if(TextUtils.isEmpty(matKhauMoi)){
                    edtMatKhauMoi.setError(getString(R.string.err_not_null));
                }
                if(TextUtils.isEmpty(xacNhanMatKhau)){
                    edtXacNhanMatKhauMoi.setError(getString(R.string.err_not_null));
                }

                if(matKhauMoi.equals(xacNhanMatKhau)){
                    changePassword(tenDangNhap, matKhauCu, matKhauMoi);
                }else {
                    edtMatKhauMoi.setError("");
                    edtXacNhanMatKhauMoi.setText("");
                    Toast.makeText(LoginActivity.this, getString(R.string.txt_mat_khau_moi_khong_khop), Toast.LENGTH_SHORT).show();
                }


            }
        });

        mAlertDialogChanPassword = new AlertDialog.Builder(LoginActivity.this).create();
        mAlertDialogChanPassword.setTitle("Đổi mật khẩu");
        mAlertDialogChanPassword.setView(view);
        mAlertDialogChanPassword.setCancelable(false);
        mAlertDialogChanPassword.show();
    }

    private void changePassword(String tenDangNhap, String matKhauCu, String matKhauMoi){
        Moshi moshi = new Moshi.Builder().build();
        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.TEN_DANG_NHAP, tenDangNhap);
            postData.put(KeyUtils.MAT_KHAU, matKhauCu);
            postData.put(KeyUtils.MAT_KHAU_MOI, matKhauMoi);
        }catch (JSONException e){
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(mediaType, postData.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_CHANGE_PASSWORD)
                .post(requestBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(json.equals("true")){
                            mAlertDialogChanPassword.cancel();
                            Toast.makeText(LoginActivity.this, getString(R.string.txt_change_password_success), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private String CheckRoot(){
        RootBeer rootBeer = new RootBeer(getApplicationContext());
        if (rootBeer.checkSuExists() || rootBeer.checkForSuBinary() || rootBeer.checkForRootNative()) {
            //we found indication of root
            showDialogRooted();
            return "rooted";
        } else {
            //we didn't find indication of root
            return "safe";
        }
    }

    private AlertDialog mAlertDialogRooted;
    private void showDialogRooted(){
        mAlertDialogRooted = new AlertDialog.Builder(LoginActivity.this).create();
        mAlertDialogRooted.setTitle(getString(R.string.txt_canh_bao));
        mAlertDialogRooted.setMessage(getString(R.string.txt_rooted));
        mAlertDialogRooted.setCancelable(true);
        mAlertDialogRooted.show();
    }
}
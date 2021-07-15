package com.mta.diemdanhandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.adapter.DanhSachSinhVienVaDiemAdapter;
import com.mta.diemdanhandroid.asyntask.SoTietSinhVienDiemDanhAsynTask;
import com.mta.diemdanhandroid.asyntask.TinhDiemChuyenCanAsynTask;
import com.mta.diemdanhandroid.entity.DiemEntity;
import com.mta.diemdanhandroid.entity.ExportExcelEntity;
import com.mta.diemdanhandroid.entity.LhpVaSoTietDiemDanhEntity;
import com.mta.diemdanhandroid.entity.SinhVienEntity;
import com.mta.diemdanhandroid.server.ApiConnect;
import com.opencsv.CSVWriter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * HuyNQN create lop hoc phan cua giao vien
 * */
public class LopHocPhanGiaoVienActivity extends AppCompatActivity implements View.OnClickListener, DanhSachSinhVienVaDiemAdapter.SinhVienItemListener, SoTietSinhVienDiemDanhAsynTask.Listener, TinhDiemChuyenCanAsynTask.UpdateUiListener {
    private TextView mTvTenHocPhan;
    private TextView mTvMaLopHocPhan;
    private TextView mTvSiSo;
    private TextView mTvSoTinChi;
    private TextView mTvSoTietDiemDanh;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private ImageButton mBtnDiemDanh;
    private ImageButton mBtnXuatFile;
    private ImageButton mBtnThemSinhVien;
    private ImageButton mBtnTinhDiemCC;

    private String mMaLopHocPhan;
    private String mTenHocPhan;
    private String mMaHocPhan;
    private String mTextQuery;
    private int mSiSo;
    private int mSoTinChi;
    private DanhSachSinhVienVaDiemAdapter mDanhSachSinhVienAdapter;
    private SinhVienEntity mSinhVienEntity;
    private List<DiemEntity> mListDiemEntities = new ArrayList<>();
    private List<String[]> mListExport;
    private String mKieuDiemDanh;
    private String mKieuTietHoc;
    private LhpVaSoTietDiemDanhEntity mLhpVaStdd;
    private float mSoTietHocDaDiemDanh = 0.0f;
    private Map<String, String> mMaps = new HashMap<>(); // MTA HuyNQn map de luu so tiet hoc diem danh cua sinh vien
    private boolean mFlagCallBack = false;
    private OkHttpClient mClient = new OkHttpClient();
    private MediaType mMediaType = MediaType.parse("application/json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc_phan_giao_vien);
        Intent intent = getIntent();
        mMaLopHocPhan = intent.getStringExtra(KeyUtils.MA_LOP_HOC_PHAN);
        mTenHocPhan = intent.getStringExtra(KeyUtils.TEN_HOC_PHAN);
        mSiSo = intent.getIntExtra(KeyUtils.SI_SO, 0);
        mSoTinChi = intent.getIntExtra(KeyUtils.SO_TIN_CHI, 0);
        mMaHocPhan = intent.getStringExtra(KeyUtils.MA_HOC_PHAN);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetListPointAsynTask().execute();
        new GetAllCheckIn().execute();
    }

    private void initView() {
        mTvMaLopHocPhan = findViewById(R.id.tv_ma_lop_hoc_phan);
        mTvMaLopHocPhan.setText(getString(R.string.txt_ma_lop_hoc_phan, mMaLopHocPhan));
        mTvTenHocPhan = findViewById(R.id.tv_ten_hoc_phan);
        mTvTenHocPhan.setText(getString(R.string.txt_ten_hoc_phan, mTenHocPhan));
        mTvSiSo = findViewById(R.id.tv_si_so);
        mTvSiSo.setText(getString(R.string.txt_si_so, mSiSo));
        mTvSoTinChi = findViewById(R.id.tv_so_tin_chi);
        mTvSoTinChi.setText(getString(R.string.txt_so_tin_chi, mSoTinChi));
        mTvSoTietDiemDanh = findViewById(R.id.tv_so_tiet_diem_danh);
        mBtnDiemDanh = findViewById(R.id.btn_diem_danh);
        mBtnDiemDanh.setOnClickListener(this);
        mBtnXuatFile = findViewById(R.id.btn_xuat_file);
        mBtnXuatFile.setOnClickListener(this);
        mBtnThemSinhVien = findViewById(R.id.btn_them_sinh_vien);
        mBtnThemSinhVien.setOnClickListener(this);
        mBtnTinhDiemCC = findViewById(R.id.btn_tinh_diem_cc);
        mBtnTinhDiemCC.setOnClickListener(this);

        mSearchView = findViewById(R.id.id_search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mTextQuery = newText;
                if (TextUtils.isEmpty(mTextQuery)) {
                    new GetListPointAsynTask().execute();
                } else {
                    new SearchAsynTask().execute();
                }
                return false;
            }
        });
        mRecyclerView = findViewById(R.id.list_sinh_vien);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_diem_danh:
                Intent intent = new Intent(getApplicationContext(), DiemDanhActivity.class);
                intent.putExtra(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
                intent.putExtra(KeyUtils.SI_SO, mSiSo);
                intent.putExtra(KeyUtils.TEN_HOC_PHAN, mTenHocPhan);
                intent.putExtra(KeyUtils.SO_TIN_CHI, mSoTinChi);
                startActivity(intent);
                break;
            case R.id.btn_xuat_file:
                showDilogExportCSV();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new ExportCSVAsynTask().execute();
                    }
                }, 1000);
                break;
            case R.id.btn_tinh_diem_cc:
                showDialogTinhDiemCC();
                break;
            case R.id.btn_them_sinh_vien:
                showDialogAddStudent();
                break;
        }
    }

    @Override
    public void onClickItemSinhVien(View itemView, SinhVienEntity entity) {
        mSinhVienEntity = entity;
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), itemView, Gravity.END);
        popupMenu.inflate(R.menu.context_menu_item_sinh_vien);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_diem_danh:
                        showDialogCheckInManual();
                        break;
                    case R.id.item_thong_tin_sv:
                        Intent intent = new Intent(getApplicationContext(), SinhVienLopHocPhanActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(KeyUtils.MA_SINH_VIEN, mSinhVienEntity.getMaSinhVien());
                        bundle.putString(KeyUtils.TEN_SINH_VIEN, mSinhVienEntity.getTenSinhVien());
                        bundle.putString(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
                        bundle.putString(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.item_xoa_sinh_vien:
                        xoaSinhVien(entity.getMaSinhVien());
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void callBackListener() {
        if (true) {
            mFlagCallBack = false;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    bindViewRecyclerView();
                }
            });
        }
    }

    @Override
    public void callBackUpdateUi() {
        new GetListPointAsynTask().execute();
        Toast.makeText(this, "Hoàn thành tính điểm chuyên cần", Toast.LENGTH_SHORT).show();
    }


    public class CheckInAsynTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            checkIn(mSinhVienEntity);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAlertDialogCheckIn.cancel();
            new GetListPointAsynTask().execute();
        }

        private void checkIn(SinhVienEntity entity) {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(date);

            JSONObject jsonObject = new JSONObject();
            MediaType mediaType = MediaType.parse("application/json");
            try {
                jsonObject.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
                jsonObject.put(KeyUtils.MA_SINH_VIEN, entity.getMaSinhVien());
                jsonObject.put(KeyUtils.NGAY_DIEM_DANH, formattedDate);
                jsonObject.put(KeyUtils.MAC_DEVICE, KeyUtils.MAC_DEVICE_DEFAULT);
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
    }

    private AlertDialog mAlertDialogCheckIn;

    private void showDialogCheckIn() {
        mAlertDialogCheckIn = new AlertDialog.Builder(LopHocPhanGiaoVienActivity.this).create();
        mAlertDialogCheckIn.setTitle(getString(R.string.txt_diem_danh));
        mAlertDialogCheckIn.setMessage(getString(R.string.txt_dang_thuc_hien_diem_danh));
        mAlertDialogCheckIn.setCancelable(false);
        mAlertDialogCheckIn.show();
    }

    private AlertDialog mDialogResultAddStudent;

    private void showDialogResult(String mes) {
        mDialogResultAddStudent = new AlertDialog.Builder(LopHocPhanGiaoVienActivity.this).create();
        mDialogResultAddStudent.setTitle(getString(R.string.txt_dialog_tem_sinh_vien));
        mDialogResultAddStudent.setMessage(mes);
        mDialogResultAddStudent.show();
    }

    private AlertDialog mDialogAddSinhVien;
    private TextInputEditText mEdtMaSinhVien;

    private void showDialogAddStudent() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_sinh_vien, null);
        mEdtMaSinhVien = view.findViewById(R.id.edt_ma_sinh_vien);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogAddSinhVien.cancel();
            }
        });
        Button btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSinhVien();
            }
        });
        mDialogAddSinhVien = new AlertDialog.Builder(LopHocPhanGiaoVienActivity.this).create();
        mDialogAddSinhVien.setTitle(getString(R.string.txt_dialog_tem_sinh_vien));
        mDialogAddSinhVien.setView(view);
        mDialogAddSinhVien.setCancelable(false);
        mDialogAddSinhVien.show();
    }

    private void themSinhVien() {
        String maSinhVien = mEdtMaSinhVien.getText().toString();
        if (!TextUtils.isEmpty(maSinhVien)) {
            JSONObject postData = new JSONObject();
            try {
                postData.put(KeyUtils.MA_SINH_VIEN, maSinhVien);
                postData.put(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
                postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(mMediaType, postData.toString());
            Request request = new Request.Builder()
                    .url(ApiConnect.API_POST_THEM_SINH_VIEN_VAO_LOP_HOC_PHAN)
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDialogResult(json);
                            }
                        });
                    }
                }
            });
        } else {
            mEdtMaSinhVien.setError(getString(R.string.txt_nhap_ma_sv));
        }
    }

    private void xoaSinhVien(String maSv) {
        JSONObject postData = new JSONObject();
        try {
            postData.put(KeyUtils.MA_SINH_VIEN, maSv);
            postData.put(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
            postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(mMediaType, postData.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_POST_XOA_SINH_VIEN_KHOI_LOP_HOC_PHAN)
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LopHocPhanGiaoVienActivity.this, json, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public class SearchAsynTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            timKiemSinhVien(mTextQuery);
            return null;
        }

        private void timKiemSinhVien(String maSv) {
            Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
            Type dataType = Types.newParameterizedType(List.class, DiemEntity.class);
            JsonAdapter<List<DiemEntity>> adapter = moshi.adapter(dataType);
            JSONObject postData = new JSONObject();
            try {
                postData.put(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
                postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
                postData.put(KeyUtils.MA_SINH_VIEN, maSv);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(mMediaType, postData.toString());
            Request request = new Request.Builder()
                    .url(ApiConnect.API_POST_TIM_KIEM_SINH_VIEN_TRONG_LHP)
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
                    if (json != null && !json.trim().equals("[]")) {
                        mListDiemEntities = adapter.fromJson(json);
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListDiemEntities = new ArrayList<>();
                                mMaps = new HashMap<>();
                                for (int i = 0; i < mListDiemEntities.size(); i++) {
                                    DiemEntity entity = mListDiemEntities.get(i);
                                    SoTietSinhVienDiemDanhAsynTask asynTask = new SoTietSinhVienDiemDanhAsynTask(entity, mMaps, getApplicationContext(), mListDiemEntities.size());
                                    asynTask.setmListener(LopHocPhanGiaoVienActivity.this);
                                    asynTask.execute();
                                }
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    if (mListDiemEntities.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mMaps = new HashMap<>();
                                for (int i = 0; i < mListDiemEntities.size(); i++) {
                                    DiemEntity entity = mListDiemEntities.get(i);
                                    SoTietSinhVienDiemDanhAsynTask asynTask = new SoTietSinhVienDiemDanhAsynTask(entity, mMaps, getApplicationContext(), mListDiemEntities.size());
                                    asynTask.setmListener(LopHocPhanGiaoVienActivity.this);
                                    asynTask.execute();
                                }
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }
            });
        }
    }

    private class GetListPointAsynTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            layDanhSachBangDiemCuaLopHocPhan();
            return null;
        }

        private void layDanhSachBangDiemCuaLopHocPhan() {
            Moshi moshi = new Moshi.Builder().add(java.util.Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
            Type dataType = Types.newParameterizedType(List.class, DiemEntity.class);
            JsonAdapter<List<DiemEntity>> adapter = moshi.adapter(dataType);
            JSONObject postData = new JSONObject();
            try {
                postData.put(KeyUtils.MA_HOC_PHAN, mMaHocPhan);
                postData.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLopHocPhan);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(mMediaType, postData.toString());
            Request request = new Request.Builder()
                    .url(ApiConnect.API_POST_DANH_SACH_DIEM_CUA_LHP)
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
                        mListDiemEntities = adapter.fromJson(json);
                    }
                    if (mListDiemEntities.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mMaps = new HashMap<>();
                                for (int i = 0; i < mListDiemEntities.size(); i++) {
                                    DiemEntity entity = mListDiemEntities.get(i);
                                    SoTietSinhVienDiemDanhAsynTask asynTask = new SoTietSinhVienDiemDanhAsynTask(entity, mMaps, getApplicationContext(), mListDiemEntities.size());
                                    asynTask.setmListener(LopHocPhanGiaoVienActivity.this);
                                    asynTask.execute();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private void bindViewRecyclerView() {
        mDanhSachSinhVienAdapter = new DanhSachSinhVienVaDiemAdapter(getApplicationContext(),
                mListDiemEntities, mMaps, mSoTinChi, mSoTietHocDaDiemDanh);
        mDanhSachSinhVienAdapter.setListener(LopHocPhanGiaoVienActivity.this);
        mRecyclerView.setAdapter(mDanhSachSinhVienAdapter);
    }

    // MTA HuyNQn lay ra toan bo so tiet diem danh
    private class GetAllCheckIn extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            layRaSoTietDiemDanhCuaLopHocPhan();
            return null;
        }

        private void layRaSoTietDiemDanhCuaLopHocPhan() {
            Moshi moshi = new Moshi.Builder().build();
            Type type = Types.newParameterizedType(LhpVaSoTietDiemDanhEntity.class);
            JsonAdapter<LhpVaSoTietDiemDanhEntity> adapter = moshi.adapter(type);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("maLhp", mMaLopHocPhan)
                    .build();
            Request request = new Request.Builder()
                    .url(ApiConnect.API_POST_LHP_VA_SO_TIET_DIEM_DANH)
                    .post(requestBody)
                    .build();
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    if (json != null) {
                        mLhpVaStdd = adapter.fromJson(json);
                        mSoTietHocDaDiemDanh = mLhpVaStdd.getTongSoTietDiemDanh();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvSoTietDiemDanh.setText(getString(R.string.txt_so_tiet_diem_danh, mSoTietHocDaDiemDanh));
                            }
                        });
                    }
                }
            });
        }
    }

    private class ExportCSVAsynTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            mListExport = new ArrayList<>();
            String[] header = {"Id Student", "Name Student",
                    "Point I", "Point II", "Point III"};
            mListExport.add(header);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (DiemEntity entity : mListDiemEntities) {
                String maSv = entity.getMaSinhVien();
                String tenSv = entity.getSinhVien().getTenSinhVien();
                String diemCC = entity.diemCCtoString();
                String diemTx = entity.diemTXtoString();
                String diemThi = entity.diemThiToString();

                String[] str = {maSv, tenSv, diemCC, diemTx, diemThi};
                mListExport.add(str);
            }
            try {
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String formattedDate = df.format(date);
                File file = Environment.getExternalStorageDirectory();
                File newFile = new File(file.getAbsolutePath() + "/DiemDanh");
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                File fileCSV = new File(newFile.getAbsolutePath() + "/" + mMaLopHocPhan + "_" + formattedDate + ".csv");
                CSVWriter writer = new CSVWriter(new FileWriter(fileCSV));
                writer.writeAll(mListExport);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(LopHocPhanGiaoVienActivity.this, getString(R.string.txt_xuat_file_thanh_cong), Toast.LENGTH_SHORT).show();
            mDialogExportCSV.cancel();
        }

        private static final String CSV_SEPARATOR = ",";

        private void writeToCSV(List<ExportExcelEntity> listEntities) {
            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));
                for (ExportExcelEntity export : listEntities) {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(export.getMaSinhVien().trim().length() == 0 ? "" : export.getMaSinhVien());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(export.getTenSinhVien().trim().length() == 0 ? "" : export.getTenSinhVien());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(export.getDiemChuyenCan().trim().length() == 0 ? "" : export.getDiemChuyenCan());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(export.getDiemThuongXuyen().trim().length() == 0 ? "" : export.getDiemThuongXuyen());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(export.getDiemThi().trim().length() == 0 ? "" : export.getDiemThi());
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private AlertDialog mDialogExportCSV;

    private void showDilogExportCSV() {
        mDialogExportCSV = new AlertDialog.Builder(LopHocPhanGiaoVienActivity.this).create();
        mDialogExportCSV.setTitle(getString(R.string.txt_xuat_file_csv));
        mDialogExportCSV.setMessage(getString(R.string.txt_dang_xuat_file));
        mDialogExportCSV.setCancelable(false);
        mDialogExportCSV.show();
    }

    private AlertDialog mDialogCheckInManual;

    private void showDialogCheckInManual() {
        View view = getLayoutInflater().inflate(R.layout.view_setup_manual_check_in, null);
        Spinner spnKieuDiemDanh = view.findViewById(R.id.sp_kieu_diem_danh);
        ArrayList<String> listKieuDiemDanh = new ArrayList<>();
        listKieuDiemDanh.add(getString(R.string.txt_diem_danh_mot_tiet));
        listKieuDiemDanh.add(getString(R.string.txt_diem_danh_ca_buoi));
        listKieuDiemDanh.add(getString(R.string.txt_diem_danh_dau_buoi));
        listKieuDiemDanh.add(getString(R.string.txt_diem_danh_cuoi_buoi));
        listKieuDiemDanh.add(getString(R.string.txt_khong_diem_danh));
        ArrayAdapter<String> arrayAdapterKieuDiemDanh = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, listKieuDiemDanh);
        arrayAdapterKieuDiemDanh.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnKieuDiemDanh.setAdapter(arrayAdapterKieuDiemDanh);
        spnKieuDiemDanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mKieuDiemDanh = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spnKieuTietHoc = view.findViewById(R.id.sp_so_tiet_hoc);
        ArrayList<String> listKieuTietHoc = new ArrayList<>();
        listKieuTietHoc.add(getString(R.string.txt_1_tiet));
        listKieuTietHoc.add(getString(R.string.txt_1_5_tiet));
        listKieuTietHoc.add(getString(R.string.txt_2_tiet));
        listKieuTietHoc.add(getString(R.string.txt_2_5_tiet));
        listKieuTietHoc.add(getString(R.string.txt_3_tiet));
        listKieuTietHoc.add(getString(R.string.txt_4_tiet));
        listKieuTietHoc.add(getString(R.string.txt_5_tiet));
        listKieuTietHoc.add(getString(R.string.txt_6_tiet));

        ArrayAdapter<String> arrayAdapterSoTiet = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, listKieuTietHoc);
        arrayAdapterSoTiet.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnKieuTietHoc.setAdapter(arrayAdapterSoTiet);
        spnKieuTietHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mKieuTietHoc = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mDialogCheckInManual = new AlertDialog.Builder(LopHocPhanGiaoVienActivity.this)
                .setPositiveButton(getString(R.string.txt_diem_danh), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDialogCheckIn();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CheckInAsynTask asynTask = new CheckInAsynTask();
                                asynTask.execute();
                            }
                        }, 1000);
                    }
                })
                .setNegativeButton(getString(R.string.txt_huy_bo), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        mDialogCheckInManual.setView(view);
        mDialogCheckInManual.setTitle(getString(R.string.txt_setup_check_in_manual));
        mDialogCheckInManual.setMessage(getString(R.string.mes_setup_check_in_manual));
        mDialogCheckInManual.setCancelable(false);
        mDialogCheckInManual.show();
    }

    private AlertDialog mDialogTinhDiemCC;

    private void showDialogTinhDiemCC() {
        View view = getLayoutInflater().inflate(R.layout.view_input_tong_so_tiet_hoc, null);
        TextInputEditText edtTongSoTietHoc = view.findViewById(R.id.edt_tong_so_tiet_hoc);
        edtTongSoTietHoc.setText(mLhpVaStdd.getTongSTDDtoString());
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogTinhDiemCC.cancel();
            }
        });
        Button btnTinhDiem = view.findViewById(R.id.btn_tinh_diem_cc);
        btnTinhDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tongSoTiet = edtTongSoTietHoc.getText().toString();
                if (TextUtils.isEmpty(tongSoTiet)) {
                    edtTongSoTietHoc.setError(getString(R.string.txt_error_chua_nhap_tong_so_tiet));
                } else {
                    for (DiemEntity entity : mListDiemEntities) {

                        float diemCC = 10 / Float.valueOf(tongSoTiet) * Float.valueOf(mMaps.get(entity.getMaSinhVien()));
                        TinhDiemChuyenCanAsynTask asynTask = new TinhDiemChuyenCanAsynTask(diemCC, entity);
                        asynTask.setListener(LopHocPhanGiaoVienActivity.this);
                        asynTask.execute();
                        mDialogTinhDiemCC.cancel();
                    }
                }
            }
        });
        mDialogTinhDiemCC = new AlertDialog.Builder(LopHocPhanGiaoVienActivity.this).create();
        mDialogTinhDiemCC.setTitle(getString(R.string.title_tinh_diem_chuyen_can));
        mDialogTinhDiemCC.setMessage(getString(R.string.message_tinh_diem_chuyen_can));
        mDialogTinhDiemCC.setCancelable(false);
        mDialogTinhDiemCC.setView(view);
        mDialogTinhDiemCC.show();
    }
}
package com.mta.diemdanhandroid.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.mta.diemdanhandroid.Utils.KeyUtils;
import com.mta.diemdanhandroid.entity.DiemEntity;
import com.mta.diemdanhandroid.server.ApiConnect;
import com.squareup.moshi.Moshi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SoTietSinhVienDiemDanhAsynTask extends AsyncTask<Void, Void, Void> {
    private Moshi mMoshi = new Moshi.Builder().build();
    private MediaType mMediaType = MediaType.parse("application/json");
    private JSONObject mJsonObject = new JSONObject();
    private OkHttpClient mClient = new OkHttpClient();
    private String mMaSv;
    private String mMaLhp;
    private DiemEntity mDiemEntity;
    private Map<String,String> mMaps;
    private Context mContext;
    private int mSize; // MTA HuyNQn dung de lawng nghe callback sau khi lay xong du lieu

    public SoTietSinhVienDiemDanhAsynTask(DiemEntity entity, Map<String,String> map, Context context, int size) {
        this.mMaSv = entity.getSinhVien().getMaSinhVien();
        this.mMaLhp = entity.getMaLopHocPhan();
        this.mDiemEntity = entity;
        this.mMaps = map;
        this.mContext = context;
        this.mSize = size;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        layRaSoTietHocSinhVienDiemDanh();
        return null;
    }

    private void layRaSoTietHocSinhVienDiemDanh(){
        try {
            mJsonObject.put(KeyUtils.MA_SINH_VIEN, mMaSv);
            mJsonObject.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLhp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(mMediaType, mJsonObject.toString());
        Request request = new Request.Builder()
                .url(ApiConnect.API_POST_TONG_TIET_SV_DIEM_DANH)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if(json != null){
                    mMaps.put(mMaSv, json);
                    if(mListener != null && mMaps.size() == mSize){
                        mListener.callBackListener();
                    }
                }
            }
        });
    }

    private Listener mListener;

    public void setmListener(Listener mListener) {
        this.mListener = mListener;
    }

    public interface Listener{
        void callBackListener();
    }
}

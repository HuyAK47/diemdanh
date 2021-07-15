package com.mta.diemdanhandroid.asyntask;

import android.os.AsyncTask;
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

public class TinhDiemChuyenCanAsynTask extends AsyncTask<Void, Void, Void> {
    private Moshi mMoshi = new Moshi.Builder().build();
    private MediaType mMediaType = MediaType.parse("application/json");
    private JSONObject mJsonObject = new JSONObject();
    private OkHttpClient mClient = new OkHttpClient();
    private String mMaSv;
    private String mMaLhp;
    private float mDiemCC;
    private DiemEntity mDiemEntity;

    public TinhDiemChuyenCanAsynTask(float mDiemCC, DiemEntity mDiemEntity) {
        this.mDiemCC = mDiemCC;
        this.mDiemEntity = mDiemEntity;
        mMaSv = mDiemEntity.getMaSinhVien();
        mMaLhp = mDiemEntity.getMaLopHocPhan();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        tinhDiemChuyenCan();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(mListener != null){
            mListener.callBackUpdateUi();
        }
    }

    private void tinhDiemChuyenCan(){
        try {
            mJsonObject.put(KeyUtils.MA_SINH_VIEN, mMaSv);
            mJsonObject.put(KeyUtils.MA_LOP_HOC_PHAN, mMaLhp);
            mJsonObject.put(KeyUtils.DIEM_CHUYEN_CAN, mDiemCC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(mMediaType, mJsonObject.toString());
        Request request = new Request.Builder()
                .post(requestBody)
                .url(ApiConnect.API_POST_TINH_DIEM_CC)
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

    private UpdateUiListener mListener;

    public void setListener(UpdateUiListener mListener) {
        this.mListener = mListener;
    }

    public interface UpdateUiListener{
        void callBackUpdateUi();
    }
}

package com.mta.diemdanhandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.entity.DiemEntity;
import com.mta.diemdanhandroid.entity.SinhVienEntity;

import java.util.List;
import java.util.Map;

public class DanhSachSinhVienVaDiemAdapter extends RecyclerView.Adapter<DanhSachSinhVienVaDiemAdapter.SinhVienViewHolder> {
    private Context mContext;
    private List<DiemEntity> mList;
    private Map<String, String> mMaps;
    private float mSoTietDuocNghiPhep;
    private float mSoTietLopHocDaDiemDanh;

    public DanhSachSinhVienVaDiemAdapter(Context mContext, List<DiemEntity> mList,
                                         Map<String, String> map, int soTC, Float soBuoiDaDiemDanhCuaLHP) {
        this.mContext = mContext;
        this.mList = mList;
        this.mMaps = map;
        this.mSoTietDuocNghiPhep = /*(float) ((soTC*15)*0.15)*/ 1;
        this.mSoTietLopHocDaDiemDanh = soBuoiDaDiemDanhCuaLHP;
    }

    @NonNull
    @Override
    public DanhSachSinhVienVaDiemAdapter.SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_view_sinh_vien_va_diem, parent,false);
        return new DanhSachSinhVienVaDiemAdapter.SinhVienViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull DanhSachSinhVienVaDiemAdapter.SinhVienViewHolder holder, int position) {
        DiemEntity entity = mList.get(position);
        holder.mTvStt.setText(String.valueOf(position + 1));
        holder.mTvMaSv.setText(entity.getMaSinhVien());
        holder.mTvTenSv.setText(entity.getSinhVien().getTenSinhVien());
        holder.mTvDiemCC.setText(entity.diemCCtoString());
        holder.mTvSoTietDiemDanh.setText(mMaps.get(entity.getMaSinhVien()));
        if(!TextUtils.isEmpty(mMaps.get(entity.getMaSinhVien()))) {
            if (mSoTietLopHocDaDiemDanh - Float.valueOf(mMaps.get(entity.getMaSinhVien())) == mSoTietDuocNghiPhep) {
                holder.mLayoutItem.setBackgroundColor(mContext.getColor(R.color.mau_vang_nhat));
            } else if (mSoTietLopHocDaDiemDanh - Float.valueOf(mMaps.get(entity.getMaSinhVien())) > mSoTietDuocNghiPhep) {
                holder.mLayoutItem.setBackgroundColor(mContext.getColor(R.color.mau_do_nhat));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onClickItemSinhVien(v, entity.getSinhVien());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvStt;
        private TextView mTvMaSv;
        private TextView mTvTenSv;
        private TextView mTvDiemCC;
        private TextView mTvSoTietDiemDanh;
        private LinearLayout mLayoutItem;
        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvStt = itemView.findViewById(R.id.tv_stt);
            mTvMaSv = itemView.findViewById(R.id.tv_ma_sv);
            mTvTenSv = itemView.findViewById(R.id.tv_ten_sv);
            mTvDiemCC = itemView.findViewById(R.id.tv_diem_cc);
            mTvSoTietDiemDanh = itemView.findViewById(R.id.tv_so_tiet_diem_danh);
            mLayoutItem = itemView.findViewById(R.id.layout_item);
        }
    }
    private SinhVienItemListener mListener;

    public void setListener(SinhVienItemListener mListener) {
        this.mListener = mListener;
    }

    public interface SinhVienItemListener{
        void onClickItemSinhVien(View view, SinhVienEntity entity);
    }
}

package com.mta.diemdanhandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.entity.DiemDanhEntity;

import java.util.List;

public class DanhSachDiemDanhAdapter extends RecyclerView.Adapter<DanhSachDiemDanhAdapter.DiemDanhViewHolder> {
    private Context mContext;
    private List<DiemDanhEntity> mList;

    public DanhSachDiemDanhAdapter(Context mContext, List<DiemDanhEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public DiemDanhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_diem_danh_item_view, parent, false);
        return new DiemDanhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiemDanhViewHolder holder, int position) {
        DiemDanhEntity entity = mList.get(position);
        holder.mTvNgayDiemDanh.setText(entity.getNgayDiemDanhToString());
        holder.mTvMaMac.setText(entity.getMacDevice());
        holder.mTvInfoKieuDiemDanh.setText(entity.getKieuDiemDanh()
                + ": " + entity.getKieuTietHoc() + " tiết");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClickItemDiemDanh(v, entity, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class DiemDanhViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNgayDiemDanh;
        private TextView mTvMaMac;
        private TextView mTvInfoKieuDiemDanh;

        public DiemDanhViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvMaMac = itemView.findViewById(R.id.tv_ma_mac);
            mTvNgayDiemDanh = itemView.findViewById(R.id.tv_ngay_diem_danh);
            mTvInfoKieuDiemDanh = itemView.findViewById(R.id.tv_info_kieu_va_tiet_diem_danh);
        }
    }

    private ItemDiemDanhListener mListener;

    public void setListener(ItemDiemDanhListener mListener) {
        this.mListener = mListener;
    }

    public interface ItemDiemDanhListener {
        void onClickItemDiemDanh(View view, DiemDanhEntity entity, int pos);
    }
}

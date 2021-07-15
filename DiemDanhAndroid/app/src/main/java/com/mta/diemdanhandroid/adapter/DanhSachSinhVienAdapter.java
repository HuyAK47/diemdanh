package com.mta.diemdanhandroid.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.entity.BluetoothEntity;
import com.mta.diemdanhandroid.entity.SinhVienEntity;

import java.util.List;

public class DanhSachSinhVienAdapter extends RecyclerView.Adapter<DanhSachSinhVienAdapter.SinhVienViewHolder> {
    private Context mContext;
    private List<SinhVienEntity> mList;
    private List<BluetoothEntity> mListBluetoothEntityScan;

    public DanhSachSinhVienAdapter(Context mContext, List<SinhVienEntity> mList, List<BluetoothEntity> mListBluetoothEntityScan) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListBluetoothEntityScan = mListBluetoothEntityScan;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_list_item_view, parent,false);
        return new SinhVienViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        SinhVienEntity svEntity = mList.get(position);
        BluetoothEntity bluetoothEntity = mListBluetoothEntityScan.get(position);
        if(svEntity.getMaSinhVien().equals(bluetoothEntity.getNameDevice())){
            for (BluetoothEntity bl : mListBluetoothEntityScan) {
                if(!bluetoothEntity.getNameDevice().equals(bl.getNameDevice())
                        && bluetoothEntity.getMacDevice().equals(bl.getMacDevice())){
                    holder.mLayoutitem.setBackgroundColor(mContext.getColor(R.color.mau_do_nhat));
                }
            }
        }
        holder.mTvStt.setText(String.valueOf(position + 1));
        holder.mTvMaSv.setText(svEntity.getMaSinhVien());
        holder.mTvTenSv.setText(svEntity.getTenSinhVien());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onClickItemSinhVien(v, svEntity);
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
        private LinearLayout mLayoutitem;
        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvStt = itemView.findViewById(R.id.tv_stt);
            mTvMaSv = itemView.findViewById(R.id.tv_ma_sv);
            mTvTenSv = itemView.findViewById(R.id.tv_ten_sv);
            mLayoutitem = itemView.findViewById(R.id.layout_item);
        }
    }
    private SinhVienItemListener mListener;

    public void setmListener(SinhVienItemListener mListener) {
        this.mListener = mListener;
    }

    public interface SinhVienItemListener{
        void onClickItemSinhVien(View view, SinhVienEntity entity);
    }
}

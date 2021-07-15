package com.mta.diemdanhandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mta.diemdanhandroid.R;
import com.mta.diemdanhandroid.entity.HocPhanEntity;
import com.mta.diemdanhandroid.entity.LopHocPhanEntity;

import java.util.List;

public class DanhSachHocPhanAdapter extends RecyclerView.Adapter<DanhSachHocPhanAdapter.HocPhanViewHolder> {
    private Context mContext;
    private List<LopHocPhanEntity> mList;

    public DanhSachHocPhanAdapter(Context mContext, List<LopHocPhanEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HocPhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_hoc_phan_item_view, parent, false);
        return new HocPhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HocPhanViewHolder holder, int position) {
        LopHocPhanEntity entity = mList.get(position);
        holder.mTvTenHP.setText(entity.getHocPhan().getTenHocPhan());
        holder.mTvMaLHP.setText(entity.getMaLopHocPhan());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HocPhanViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvTenHP;
        private TextView mTvMaLHP;
        public HocPhanViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTenHP = itemView.findViewById(R.id.tv_hoc_phan);
            mTvMaLHP = itemView.findViewById(R.id.tv_ma_lop_hoc_phan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.itemOnClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface HocPhanClickListener{
        void itemOnClick(int position);
    }

    private HocPhanClickListener mListener;

    public void setListener(HocPhanClickListener listener) {
        this.mListener = listener;
    }
}

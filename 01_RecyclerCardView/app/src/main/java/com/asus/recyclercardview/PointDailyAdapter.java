package com.asus.recyclercardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Allen on 2018/1/2.
 */

public class PointDailyAdapter extends RecyclerView.Adapter<PointDailyAdapter.ViewHolder> {
    private List<PointSummary> mPoints;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView mPointDate;
        private final TextView mSaleRecordPoint;
        private final TextView mUniversityPoint;

        public ViewHolder(View itemView) {
            super(itemView);
            mPointDate = itemView.findViewById(R.id.tv_point_date);
            mSaleRecordPoint = itemView.findViewById(R.id.tv_sale_record_point);
            mUniversityPoint = itemView.findViewById(R.id.tv_university_point);
        }
    }

    public PointDailyAdapter(List<PointSummary> points) {
        mPoints = points;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PointDailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPointDate.setText(mPoints.get(position).getPointDate());
        holder.mSaleRecordPoint.setText(String.valueOf(mPoints.get(position).getSaleRecordPoint()));
        holder.mUniversityPoint.setText(String.valueOf(mPoints.get(position).getUniversityPoint()));
    }

    @Override
    public int getItemCount() {
        return mPoints.size();
    }
}
package com.asus.recyclercardview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Allen on 2018/1/4.
 */

public class PeriodPointAdapter extends RecyclerView.Adapter<PeriodPointAdapter.ViewHolder>{
    private List<PointSummary> mPoints;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mSaleRecordPoint;
        private final TextView mPointDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mSaleRecordPoint = itemView.findViewById(R.id.tv_sale_record_point);
            mPointDate = itemView.findViewById(R.id.tv_point_date);
        }
    }

    public PeriodPointAdapter(Context context, List<PointSummary> points) {
        mContext = context;
        mPoints = points;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PeriodPointAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.period_recycler_item, parent, false);

        PeriodPointAdapter.ViewHolder vh = new PeriodPointAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PeriodPointAdapter.ViewHolder holder, int position) {
        holder.mPointDate.setText(mPoints.get(position).getPointDate());
        holder.mSaleRecordPoint.setText(String.valueOf(mPoints.get(position).getSaleRecordPoint()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DailyPointActivity.class);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPoints.size();
    }
}

package com.asus.recyclercardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DailyPointActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PointSummary> mPoints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPoints = getPointData();

        mAdapter = new PointDailyAdapter(mPoints);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<PointSummary> getPointData() {

        List<PointSummary> points = new ArrayList<>();

        PointSummary point1 = new PointSummary("2017/01/02", 100, 300);
        PointSummary point2 = new PointSummary("2017/01/03", 200, 300);
        PointSummary point3 = new PointSummary("2017/01/04", 200, 300);
        PointSummary point4 = new PointSummary("2017/01/04", 200, 300);
        PointSummary point5 = new PointSummary("2017/01/04", 200, 300);

        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);

        return points;
    }
}

package com.asus.recyclercardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Point> mPoints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPoints = getPointData();

        mAdapter = new PointAdapter(mPoints);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Point> getPointData() {

        List<Point> points = new ArrayList<>();

        Point point1 = new Point("2017/01/02", 100, 300);
        Point point2 = new Point("2017/01/03", 200, 300);
        Point point3 = new Point("2017/01/04", 200, 300);
        Point point4 = new Point("2017/01/04", 200, 300);
        Point point5 = new Point("2017/01/04", 200, 300);

        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);

        return points;
    }
}

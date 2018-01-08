package com.asus.a02_swiperefreshlayout;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private PointDailyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PointSummary> mPoints = new ArrayList<>();
    private boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getPointData();

        mAdapter = new PointDailyAdapter(mPoints);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.layout_swipe_refresh);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                mPoints.clear();
                getPointData();
                mAdapter.notifyDataSetChanged();

                mRefreshLayout.setRefreshing(false);

                setOnScrollListener();
            }
        });

        setOnScrollListener();
    }

    //每次上拉加载的时候，给RecyclerView的后面添加了10条数据数据
    private void loadMoreData(){
        if(mPoints.size() < 20) {
            // 模擬獲取網路數據，延遲一秒
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            PointSummary point1 = new PointSummary("2017/01/06", 200, 300);
                            PointSummary point2 = new PointSummary("2017/01/07", 200, 300);

                            mPoints.add(point1);
                            mPoints.add(point2);

                            mAdapter.notifyDataSetChanged();
                            mAdapter.setLoadState(mAdapter.LOADING_COMPLETE);
                        }
                    });
                }
            }, 1000);
        }else{
            mAdapter.setLoadState(mAdapter.LOADING_END);
        }
    }

    private void setOnScrollListener() {
        mRecyclerView.addOnScrollListener(new LoadMoreScrollListener((LinearLayoutManager) mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mAdapter.setLoadState(mAdapter.LOADING);
                loadMoreData();
            }
        });
    }
    private void getPointData() {

        PointSummary point1 = new PointSummary("2017/12/01", 100, 300);
        PointSummary point2 = new PointSummary("2017/12/02", 200, 300);
        PointSummary point3 = new PointSummary("2017/12/03", 200, 300);
        PointSummary point4 = new PointSummary("2017/12/04", 200, 300);
        PointSummary point5 = new PointSummary("2017/12/05", 200, 300);
        PointSummary point6 = new PointSummary("2017/12/06", 100, 300);
        PointSummary point7 = new PointSummary("2017/12/07", 200, 300);
        PointSummary point8 = new PointSummary("2017/12/08", 200, 300);
        PointSummary point9 = new PointSummary("2017/12/09", 200, 300);
        PointSummary point10 = new PointSummary("2017/12/10", 200, 300);

        mPoints.add(point1);
        mPoints.add(point2);
        mPoints.add(point3);
        mPoints.add(point4);
        mPoints.add(point5);
        mPoints.add(point6);
        mPoints.add(point7);
        mPoints.add(point8);
        mPoints.add(point9);
        mPoints.add(point10);
    }
}

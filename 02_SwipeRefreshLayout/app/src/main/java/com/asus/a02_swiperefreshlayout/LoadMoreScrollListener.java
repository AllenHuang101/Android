package com.asus.a02_swiperefreshlayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Allen on 2018/1/6.
 */

public abstract class LoadMoreScrollListener extends  RecyclerView.OnScrollListener{

    private LinearLayoutManager mLinearLayoutManager;

    private int currentPage = 0;

    //目前 item 數量
    private int totalItemCount;

    //前一個載入前的 數量
    private int previousTotal = 0;

    //螢幕上可見的item数量
    private int visibleItemCount;

    //在螢幕上可见的Item中的第一個
    private int firstVisibleItem;

    //是否正在上拉載入數據
    private boolean loading = true;

    public LoadMoreScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        //螢幕上可見的item数量
        visibleItemCount = recyclerView.getChildCount();
        //目前 item 數量
        totalItemCount = mLinearLayoutManager.getItemCount();
        //在螢幕上可見的Item中的第一個
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if(loading){
            Log.d("LoadMore","firstVisibleItem: " +firstVisibleItem);
            Log.d("LoadMore","totalPageCount:" +totalItemCount);
            Log.d("LoadMore", "visibleItemCount:" + visibleItemCount);

            if(totalItemCount > previousTotal){
                //说明数据已经加载结束
                loading = false;
                previousTotal = totalItemCount;
            }
        }


        if (!loading && firstVisibleItem + visibleItemCount >= totalItemCount){
            currentPage ++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */
    public abstract void onLoadMore(int currentPage);
}

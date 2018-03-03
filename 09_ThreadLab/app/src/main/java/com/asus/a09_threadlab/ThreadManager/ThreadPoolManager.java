package com.asus.a09_threadlab.ThreadManager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Allen on 2018/3/3.
 */

public class ThreadPoolManager {
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolManager mInstance;
    private ThreadPoolExecutor mThreadPoolExector ;

    private ThreadPoolManager() {
        mThreadPoolExector = new ThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ThreadPoolManager getInstance() {
        if (mInstance == null) {
            mInstance = new ThreadPoolManager();
        }
        return mInstance;
    }

    public ThreadPoolExecutor getThreadPoolExector() {
        return mThreadPoolExector;
    }
}

package com.asus.a09_threadlab.ThreadManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by Allen on 2018/3/3.
 */

public class PriorityThreadPoolManager {

    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static PriorityThreadPoolManager mPriorityThreadPoolManager;
    private PriorityThreadPoolExecutor mPriorityThreadPoolExecutor;

    private PriorityThreadPoolManager() {
        mPriorityThreadPoolExecutor = new PriorityThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                60,
                TimeUnit.SECONDS);
    }

    public static PriorityThreadPoolManager getInstance() {
        if (mPriorityThreadPoolManager == null) {
            mPriorityThreadPoolManager = new PriorityThreadPoolManager();
        }
        return mPriorityThreadPoolManager;
    }

    public PriorityThreadPoolExecutor getThreadPoolExector() {
        return mPriorityThreadPoolExecutor;
    }
}

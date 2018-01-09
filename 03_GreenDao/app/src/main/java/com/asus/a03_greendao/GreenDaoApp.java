package com.asus.a03_greendao;

import android.app.Application;

import com.asus.a03_greendao.gen.DaoMaster;
import com.asus.a03_greendao.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Allen on 2018/1/9.
 */

public class GreenDaoApp extends Application {
    private DaoSession mDaoSession;
    private Database mDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataBase = new DbOpenHelper(this, "Green.db").getWritableDb();
        mDaoSession = new DaoMaster(mDataBase).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}

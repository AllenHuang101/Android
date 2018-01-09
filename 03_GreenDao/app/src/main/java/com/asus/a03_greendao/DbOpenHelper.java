package com.asus.a03_greendao;

import android.content.Context;
import android.util.Log;

import com.asus.a03_greendao.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Allen on 2018/1/9.
 */

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);


    }
}

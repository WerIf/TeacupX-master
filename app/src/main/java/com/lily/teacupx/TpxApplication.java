package com.lily.teacupx;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.material.appbar.AppBarLayout;
import com.lily.teacupx.db.DaoMaster;
import com.lily.teacupx.db.DaoSession;

/**
 * @author Lily
 * @date 2019/7/8 0008.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class TpxApplication extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static TpxApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static TpxApplication getInstance() {
        return instance;
    }

    private void obtainDataBase(){
        mHelper=new DaoMaster.DevOpenHelper(this,"starDb",null);
        db=mHelper.getWritableDatabase();
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }
}

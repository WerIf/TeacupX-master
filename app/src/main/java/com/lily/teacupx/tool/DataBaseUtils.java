package com.lily.teacupx.tool;

import com.lily.teacupx.TpxApplication;
import com.lily.teacupx.db.DaoSession;
import com.lily.teacupx.db.StarBeanInfo;
import com.lily.teacupx.db.StarBeanInfoDao;

import java.util.List;

/**
 * @author Lily
 * @date 2019/7/8 0008.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class DataBaseUtils {
    private static DataBaseUtils baseUtils;
    private StarBeanInfoDao beanInfoDao;
    private DaoSession daoSession;

    private DataBaseUtils(TpxApplication application){
        beanInfoDao= application.getDaoSession().getStarBeanInfoDao();
        daoSession=application.getDaoSession();
    }

    public static DataBaseUtils getInstance(TpxApplication application){
        if(baseUtils==null){
            synchronized (DataBaseUtils.class){
                if(baseUtils==null){
                    baseUtils=new DataBaseUtils(application);
                }
            }
        }
        return baseUtils;
    }

    public void insert(StarBeanInfo info){
        beanInfoDao.insert(info);
    }

    public void delete(StarBeanInfo info){
        beanInfoDao.delete(info);
    }

    public void update(StarBeanInfo info){
        beanInfoDao.update(info);
    }

    public List<StarBeanInfo> check(){
        List<StarBeanInfo> list = beanInfoDao.loadAll();
        return list;
    }

    public List<StarBeanInfo> check(String name){
        List<StarBeanInfo> list = daoSession.queryBuilder(StarBeanInfo.class).where(StarBeanInfoDao.Properties.Name.eq(name)).build().list();
        return list;
    }
}

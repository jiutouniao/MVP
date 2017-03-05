package com.soft.login.model.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.soft.login.model.db.dto.PersonDto;
import java.sql.SQLException;

/**
 * description: 数据库帮助类
 * Date: 2016/9/8 17:46
 * User: shaobing
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqlite-test.db";
    private static DatabaseHelper instance;

    public static String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Person表 ，每张表对于一个
     */
    private Dao<PersonDto, Integer> userDao;


    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PersonDto.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * SQL ALTER TABLE 语法
     * 如需在表中添加列，请使用下列语法:
     * ALTER TABLE table_name
     * ADD column_name datatype
     * 要删除表中的列，请使用下列语法：
     * ALTER TABLE table_name
     * DROP COLUMN column_name
     * 注释：某些数据库系统不允许这种在数据库表中删除列的方式 (DROP COLUMN column_name)。
     * 要改变表中列的数据类型，请使用下列语法：
     * ALTER TABLE table_name
     * ALTER COLUMN column_name datatype
     *
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        try {
////            TableUtils.dropTable(connectionSource, PersonDto.class, true);
////            onCreate(database, connectionSource);
//
//            if (oldVersion < 3) {
////                getPersonDao().executeRaw("ALTER TABLE `tb_user` ADD COLUMN test TEXT DEFAULT 'ddddd';");
//            }
//
//            if (oldVersion < 4) {
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<PersonDto, Integer> getPersonDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(PersonDto.class);
        }
        return userDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
    }

}
package tydic.cn.com.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import tydic.cn.com.helloworld.User;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }


    public void add(List<User> Users) {
        db.beginTransaction();  //开始事务
        try {
            for (User user : Users) {
                db.execSQL("INSERT INTO student" +
                        " VALUES(null, ?, ?, ?)", new Object[]{user.getName(), user.getAge(), user.getInfo()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    public void updateAge(User user) {
        ContentValues cv = new ContentValues();
        cv.put("age",user.getAge() );
        db.update("student", cv, "name = ?", new String[]{user.getName()});
    }

    public void deleteOldUser(User user) {
        db.delete("student", "age = ?", new String[]{user.getAge()});
    }


    public List<User> query() {
        List<User> users = new ArrayList<User>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            User user = new User();
            user.setName(c.getString(c.getColumnIndex("name")));
            user.setAge(c.getString(c.getColumnIndex("age")));
            user.setInfo(c.getString(c.getColumnIndex("info")));
            users.add(user);
        }
        c.close();
        return users;
    }


    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM student", null);
        return c;
    }


    public void closeDB() {
        db.close();
    }
}
package com.maggots.YellowPages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: maity
 * Date: 5/8/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class dataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="yellowpages.db";
    private static final int SCHEMA_VERSION=1;


    public dataBaseHelper(Context context){
        super(context,DATABASE_NAME, null, SCHEMA_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE yellowpage (_id INTEGER PRIMARY KEY AUTOINCREMENT, section TEXT, category TEXT, title TEXT,  address TEXT, phone TEXT, tag TEXT);");
        Log.i("DataBase", "created Table yellowpage");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void insert(String section, String category, String title, String address, String phone, String tag){
        ContentValues cv = new ContentValues();
        cv.put("section", section);
        cv.put("category",category);
        cv.put("title", title);
        cv.put("address", address);
        cv.put("phone", phone);
        cv.put("tag",tag);
        getWritableDatabase().insert("yellowpage","title",cv);

    }

    public Cursor getSectionNames(){
        Cursor c = null;
        Log.i("YellowPage", "Try to get section names from DB");

        c = getReadableDatabase()
                .rawQuery("SELECT section, _id  FROM yellowpage GROUP BY section", null);
        if(c ==null){
            Log.e("YellowPages", "Error on getting sections from Database");
        }



        return c;

    }

    public Cursor getCategoryBySectioinName(String secName){
        Cursor c = null;
        Log.i("YellowPage", "Try to get cat names from DB");

            c = getReadableDatabase()
                    .rawQuery("SELECT category, _id  FROM yellowpage WHERE section='"+secName+"' GROUP BY category", null);
            if(c ==null){
               Log.e("YellowPages", "Error on getting categories from Database");
            }



        return c;

    }

    public Cursor getItemByCategoryName(String catName){
        Cursor c = null;
        Log.i("YellowPage", "Try to get cat names from DB");

        c = getReadableDatabase()
                .rawQuery("SELECT title, address, phone, tag,  _id  FROM yellowpage WHERE category='"+catName+"'", null);
        if(c ==null){
            Log.e("YellowPages", "Error on getting items from Database");
        }



        return c;

    }



    public Cursor getCategoryItems(String catName){
        return(getReadableDatabase()
                .rawQuery("SELECT *  FROM yellowpage where category ='"+catName+"'", null));

    }

}

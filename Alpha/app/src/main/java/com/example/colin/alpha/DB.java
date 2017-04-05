package com.example.colin.alpha;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Colin on 02/04/2017.
 */

public class DB extends SQLiteOpenHelper {

    final static int DB_VERSION = 1;
    final static String DB_NAME = "mydb";
    Context context;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // Store the context for later use
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_RT = "CREATE TABLE " + rt.TABLE  + "("
                + rt.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + rt.KEY_name + " TEXT, "
                + rt.KEY_volume + " INTEGER, "
                + rt.KEY_rt + " INTEGER )";

        db.execSQL(CREATE_TABLE_RT);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + rt.TABLE);

        // Create tables again
        onCreate(db);

    }
}
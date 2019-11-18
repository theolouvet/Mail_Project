package com.project.mail.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.mail.Constants;

public class DatabaseHandler extends SQLiteOpenHelper {



    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("test","db creation");
        db.execSQL(Constants.MAIL_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("test","db upgrade ");
        db.execSQL(Constants.MAIL_TABLE_DROP);
        onCreate(db);
    }
}

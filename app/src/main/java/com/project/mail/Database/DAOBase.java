package com.project.mail.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.mail.Constants;

public abstract class DAOBase {

    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, Constants.VERSION);
        removeDuplicate();

    }

    public SQLiteDatabase open() {
        if(mDb == null)
            mDb = mHandler.getWritableDatabase();
        else if(!mDb.isOpen())
            mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        if(mDb.isOpen())
            mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }

    public void removeDuplicate(){
        open();
        mDb.execSQL(Constants.REMOVE_REQUEST);
        mDb.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME="+"'"+Constants.MAIL_TABLE_NAME+"';");
        close();
    }
}



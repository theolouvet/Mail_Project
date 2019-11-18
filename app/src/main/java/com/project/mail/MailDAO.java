package com.project.mail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.project.mail.Database.DAOBase;

import java.util.ArrayList;
import java.util.Map;

public class MailDAO extends DAOBase {


    public static final String MAIL_TABLE_DROP = "DROP TABLE IF EXISTS " + Constants.MAIL_TABLE_NAME +";";

    public MailDAO(Context context){
        super(context);
    }

    public Boolean add(Mailstruct mailstruct){
        open();
        Boolean result = true;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.MAIL_PASSWORD, mailstruct.getPwd());
        contentValues.put(Constants.MAIL_ADDRESS, mailstruct.getAddress());
        Cursor search = mDb.rawQuery("SELECT "+Constants.MAIL_ADDRESS+" FROM "+
                Constants.MAIL_TABLE_NAME+" WHERE "+ Constants.MAIL_ADDRESS+"=?", new String[]{mailstruct.getAddress()});

        if(mDb == null)
            Log.i("test","null database");

        if(!search.moveToFirst())
            mDb.insert(Constants.MAIL_TABLE_NAME, null, contentValues);
        else
            result = false;
        search.close();
        mDb.close();
        return result;
    }

    public void remove(long id){
        open();
        mDb.delete(Constants.MAIL_TABLE_NAME, Constants.MAIL_KEY + " = ?",new String[]{String.valueOf(id)});
        mDb.close();
    }

    public void remove(String add){
        open();
        mDb.delete(Constants.MAIL_TABLE_NAME, Constants.MAIL_ADDRESS + " = ?",new String[]{add});
        close();
    }

    public void removeAll(){
        open();
        mDb.execSQL("DELETE FROM "+Constants.MAIL_TABLE_NAME);
    }

    public void modify(Mailstruct mailstruct){
        open();
        ContentValues values = new ContentValues();
        values.put(Constants.MAIL_PASSWORD, mailstruct.getPwd());
        mDb.update(Constants.MAIL_TABLE_NAME, values, Constants.MAIL_KEY + " = ?",
                new String[]{String.valueOf(mailstruct.getId())});
        mDb.close();
    }

    public void select(long id){}

    public ArrayList<String> getAddressList(){
        open();
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = mDb.rawQuery("SELECT "+Constants.MAIL_ADDRESS+ " FROM " + Constants.MAIL_TABLE_NAME , null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            System.out.println("test " + c.getString(0));
            list.add(getAddress(c));
        }
        c.close();
        mDb.close();
        return list;
    }

    private String getAddress(Cursor c){
        return c.getString(0);
    }

    public ArrayList<Mailstruct> mailList(){
        open();
        ArrayList<Mailstruct> mailstructs = new ArrayList<Mailstruct>();
        Cursor c = mDb.rawQuery("SELECT "+Constants.MAIL_KEY+","+Constants.MAIL_ADDRESS+","+Constants.MAIL_PASSWORD+
                " FROM "+Constants.MAIL_TABLE_NAME, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
            mailstructs.add(new Mailstruct(c.getInt(0), c.getString(1), c.getString(2)));
        c.close();
        mDb.close();
        return mailstructs;
    }
}

package com.project.mail;

import android.provider.BaseColumns;

public class Constants implements BaseColumns {

    public final static int VERSION = 2;

    public static final String MAIL_KEY = "id";

    public static final String MAIL_ADDRESS = "address";

    public static final String MAIL_PASSWORD = "password";

    public static final String MAIL_TABLE_NAME = "DataMail";

    public static final String MAIL_TABLE_CREATE = "CREATE TABLE " + MAIL_TABLE_NAME +
            "(" + MAIL_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            MAIL_ADDRESS + "," +
            MAIL_PASSWORD + ");";

    public static final String MAIL_TABLE_DROP = "DROP TABLE IF EXISTS " + MAIL_TABLE_NAME +";";

    public static final  String REMOVE_REQUEST = "delete from "+ MAIL_TABLE_NAME+"\n" +
            "where    rowid not in\n" +
            "         (\n" +
            "         select  min(rowid)\n" +
            "         from "+MAIL_TABLE_NAME+"\n" +
            "         group by\n"
            + MAIL_ADDRESS +"\n" +
            "         )" ;

}

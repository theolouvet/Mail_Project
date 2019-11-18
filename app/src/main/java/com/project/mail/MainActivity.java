package com.project.mail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.project.mail.Database.DatabaseHandler;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 112;
    private boolean CheckPermission = false;
    private MailDAO mailDAO;
    static final int ADD_MAIL_REQUEST = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        mailDAO = new MailDAO(this);
        //test();
    }

    public void test(){
        MailDAO mailDAO = new MailDAO(this);

        Map<String, String> arrayMap = new ArrayMap<>();
        //arrayMap.put("theolouvetsuys@gmail.com","edd23a3d");
        arrayMap.put("azert@orange.fr","1234");
        mailDAO.remove("azert@orange.fr");
        mailDAO.remove("ewresx");
        for(Map.Entry<String, String> m : arrayMap.entrySet()){
            if(!mailDAO.add(new Mailstruct(0,m.getKey(), m.getValue())))
                Toast.makeText(this, "Address already known", Toast.LENGTH_LONG).show();
        }



        //getDatabaseStructure(mailDAO.getDb());
       // mailDAO.getAddressList();

        ArrayList<Mailstruct> list = mailDAO.mailList();
        for(Mailstruct mail : list){
            System.out.println(mail.toString());
        }



    }


    public void getDatabaseStructure(SQLiteDatabase db) {

        Cursor c = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        ArrayList<String[]> result = new ArrayList<String[]>();
        int i = 0;
        result.add(c.getColumnNames());
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String[] temp = new String[c.getColumnCount()];
            for (i = 0; i < temp.length; i++) {
                temp[i] = c.getString(i);
                System.out.println("TABLE - " + temp[i]);


                Cursor c1 = db.rawQuery(
                        "SELECT * FROM " + temp[i], null);
                c1.moveToFirst();
                String[] COLUMNS = c1.getColumnNames();
                for (int j = 0; j < COLUMNS.length; j++) {
                    c1.move(j);
                    System.out.println("    COLUMN - " + COLUMNS[j]);
                }
            }
            result.add(temp);
        }
        Cursor c2 = db.rawQuery("SELECT * FROM "+ Constants.MAIL_TABLE_NAME, null);
        System.out.println("test " + c2.getColumnName(0));

    }

    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            requestPermission();
        else
            CheckPermission = true;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    CheckPermission = true;
                else
                    finish();
            }
        }

    }

    public void addAccount(){
        //startActivity(new Intent(this, MailFormulary.class));
        startActivityForResult(new Intent(this, MailFormulary.class), ADD_MAIL_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addAccount:
                addAccount();
                return true;
            case R.id.MyMail:
                startActivity(new Intent(this,MailListView.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_MAIL_REQUEST && resultCode == RESULT_OK){

            String add = data.getExtras().getString("MAIL_ADDRESS");
            String pwd = data.getStringExtra("MAIL_PASSWORD");
            if(data.getExtras() == null)
                Log.i("test","null extra");
            else if(add == null)
                Log.i("test","null mail");
            else if(pwd == null)
                Log.i("test","null pwd");
            if(!mailDAO.add(new Mailstruct(0, add, pwd)))
                Toast.makeText(this,"Adresse connue", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Adresse ajoutee",Toast.LENGTH_LONG).show();
            ArrayList<Mailstruct> list = mailDAO.mailList();
            for(Mailstruct mail : list){
                System.out.println(mail.toString());
            }
        }
    }
}

package com.project.mail;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MailListView extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_mail_view);
        affichage();

    }

    private void affichage(){
        MailDAO mailDAO = new MailDAO(this);
        ArrayList<Mailstruct> list = mailDAO.mailList();
        ListView listView = findViewById(R.id.list_item);
        MailAdapter mailAdapter = new MailAdapter(this, list);
        listView.setAdapter(mailAdapter);
        mailDAO.close();
    }

    public void enterButtonClear(View view){
        MailDAO mailDAO = new MailDAO(this);
        mailDAO.removeAll();
        mailDAO.close();
        affichage();
    }
}

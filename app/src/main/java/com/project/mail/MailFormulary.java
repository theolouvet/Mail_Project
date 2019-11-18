package com.project.mail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MailFormulary extends Activity {

    EditText mailAddresse;
    EditText mailPasseword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulary);

        mailAddresse = findViewById(R.id.editMailAdd);
        mailPasseword = findViewById(R.id.editMailPass);

    }

    private boolean isValidateAddress(){
        return !TextUtils.isEmpty(mailPasseword.getText()) && !TextUtils.isEmpty(mailAddresse.getText()) && android.util.Patterns.EMAIL_ADDRESS.matcher(mailAddresse.getText()).matches();
    }

    public void enterButton(View view){
        boolean check;
        if(isValidateAddress()){
            Intent resutlIntent = new Intent();
            resutlIntent.putExtra("MAIL_ADDRESS",mailAddresse.getText().toString());
            resutlIntent.putExtra("MAIL_PASSWORD",mailPasseword.getText().toString());
            setResult(Activity.RESULT_OK, resutlIntent);
            finish();
        }
        else
            Toast.makeText(this, "Mail invalide", Toast.LENGTH_LONG).show();
    }

}

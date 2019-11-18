package com.project.mail;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MailAdapter extends ArrayAdapter<Mailstruct> {

    public MailAdapter(Context context, List<Mailstruct> mailstructs){
        super(context, 0, mailstructs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        super.getView(position, convertView, parent);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_mails_view,parent, false);
        }

        MailViewHolder viewHolder = (MailViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MailViewHolder();
            viewHolder.address = (TextView) convertView.findViewById(R.id.addlistview);
            convertView.setTag(viewHolder);
        }


        Mailstruct mailstruct = getItem(position);


        viewHolder.address.setText(mailstruct.getAddress());


        return convertView;
    }

    private class MailViewHolder{
        public TextView address;
    }
}

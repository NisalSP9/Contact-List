package com.android.example.cis.contaclist.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.example.cis.contaclist.R;

import database.DBHandler;
import model.Contact;

public class UpdateContact extends AppCompatActivity {

    EditText names,phones,emails;
    TextView ids;
    Button updateBtn;
    String name,phone,email,id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        names = (EditText) findViewById(R.id.nameText);
        phones = (EditText)findViewById(R.id.phoneText);
        emails = (EditText)findViewById(R.id.editText3);
        ids = (TextView)findViewById(R.id.id);
        updateBtn = (Button)findViewById(R.id.update);

        savedInstanceState = getIntent().getExtras();

        name = savedInstanceState.getString("NAME");
        phone = savedInstanceState.getString("PHONE");
        email = savedInstanceState.getString("EMAIL");
        id = savedInstanceState.getString("ID");

        names.setText(name);
        phones.setText(phone);
        emails.setText(email);


        final DBHandler dbHandler = new DBHandler(this);



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact contact = new Contact(names.getText().toString(),phones.getText().toString(),emails.getText().toString());


                int rst = dbHandler.updateContact(contact,id);

                if(rst>0){

                    Toast.makeText(UpdateContact.this,"Updated..!",Toast.LENGTH_LONG).show();
                    reload();

                }else{

                    Toast.makeText(UpdateContact.this,"Something went wrong..!",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void reload(){
        Intent intent = new Intent(this, ViewContactList.class);
        startActivity(intent);


    }

}

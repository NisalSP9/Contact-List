package com.android.example.cis.contaclist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.example.cis.contaclist.R;

import database.DBHandler;
import model.Contact;

public class AddNewContact extends AppCompatActivity {

    EditText nameText;
    EditText phoneText;
    EditText emailText;
    Button save;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        nameText = (EditText)findViewById(R.id.nameText);
        phoneText = (EditText)findViewById(R.id.phoneText);
        emailText = (EditText)findViewById(R.id.editText3);
        save = (Button)findViewById(R.id.save);
        reset = (Button)findViewById(R.id.reset);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetContent();
            }
        });
    }


    public void saveContact(){

        String name = nameText.getText().toString();
        String phoneNumber = phoneText.getText().toString();
        String email = emailText.getText().toString();

        if (name.isEmpty()){

            Toast.makeText(AddNewContact.this,"Enter Name",Toast.LENGTH_LONG).show();

        }else if(phoneNumber.isEmpty()) {

            Toast.makeText(AddNewContact.this,"Enter Phone Number",Toast.LENGTH_LONG).show();

        }else{
            Contact contact = new Contact(name,phoneNumber,email);

       DBHandler dbHandler = new DBHandler(this);
       long rst = dbHandler.addContact(contact);

       if(rst>0){

           Toast.makeText(this,"Saved..!",Toast.LENGTH_LONG).show();
           resetContent();

       }else{

           Toast.makeText(this,"Something went wrong..!",Toast.LENGTH_LONG).show();


       }
      }

    }

    public void resetContent(){

        nameText.setText("");
        phoneText.setText("");
        emailText.setText("");

    }
}

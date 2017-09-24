package com.android.example.cis.contaclist.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.android.example.cis.contaclist.R;

public class MainActivity extends AppCompatActivity {

    Button viewContactBut;
    Button addNewContact;
    //Menu menu_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewContactBut = (Button)findViewById(R.id.viewList);
        addNewContact = (Button)findViewById(R.id.addNew);


        viewContactBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactList();
            }
        });

        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewContact();
            }
        });

    }

    public void openContactList(){

        //Toast.makeText(this,"openContactList",Toast.LENGTH_LONG).show();
        //Log.d("msg","view");
        Intent intent = new Intent(this, ViewContactList.class);
        startActivity(intent);

    }

    public void openNewContact(){

        //Toast.makeText(this,"openNewContact",Toast.LENGTH_LONG).show();
        //Log.d("msg","new");
        Intent intent = new Intent(this, AddNewContact.class);
        startActivity(intent);

    }
}

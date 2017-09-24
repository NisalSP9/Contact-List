package com.android.example.cis.contaclist.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.example.cis.contaclist.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import database.DBHandler;
import model.Contact;

public class ViewContactList extends AppCompatActivity {

    ListView contactList;
    String name,phone,mail;
    Map<String,Contact> contacts;
    Set IDSet;
    List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_contact_list);

        contactList = (ListView) findViewById(R.id.listView);

        contactList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);



        contacts = new DBHandler(this).getContactList();

        IDSet = contacts.keySet();

        nameList = new ArrayList<>();



        final List<Contact> objectList = new ArrayList<>();
        final List<String> IDList = new ArrayList<>();

        for (Object id : IDSet){

            nameList.add(contacts.get(id).getName());
            IDList.add(id.toString());
            objectList.add(contacts.get(id));

        }



        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,nameList);

        contactList.setAdapter(stringArrayAdapter);

            contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //Toast.makeText(ViewContactList.this,contactList.getCheckedItemPosition()+"",Toast.LENGTH_LONG).show();
                        Contact contact = objectList.get(position);
                        name = contact.getName();
                        phone = contact.getPhone();
                        mail = contact.getEmail();

                        showInfor(name,phone,mail);


                }
            });

        contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


               final PopupMenu menu = new PopupMenu(ViewContactList.this,view);
               final String ids = IDList.get(position);

                menu.inflate(R.menu.menu);

                menu.show();

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.one:

                                Contact contact = objectList.get(position);

                                name = contact.getName();
                                phone = contact.getPhone();
                                mail = contact.getEmail();

                                updateInfor(ids,name,phone,mail);
                                break;

                            case R.id.two:

                                DBHandler dbHandler = new DBHandler(ViewContactList.this);
                                dbHandler.deleteContact(ids);
                                Toast.makeText(ViewContactList.this,"Deleted",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());
                                break;

                        }


                        return false;
                    }
                });

                return true;//if return false it will continu the onclick proccess
            }
        });





    }


    public void showInfor(String name,String phone,String mail){

        Intent intent = new Intent(this, ContactDetail.class);
        intent.putExtra("NAME",name);
        intent.putExtra("PHONE",phone);
        intent.putExtra("EMAIL",mail);
        startActivity(intent);

    }

    public void updateInfor(String id,String name,String phone,String mail){

        Intent intent = new Intent(this, UpdateContact.class);

        intent.putExtra("ID",id);
        intent.putExtra("NAME",name);
        intent.putExtra("PHONE",phone);
        intent.putExtra("EMAIL",mail);

        startActivity(intent);



    }


}

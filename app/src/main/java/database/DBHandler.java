package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Contact;

/**
 * Created by aDMIN on 5/11/2016.
 */
public class DBHandler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "contactInfo";

    // Contacts table name
    private static final String TABLE_NAME = "contact";

    // contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";


    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("", "onCreate: ");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " VARCHAR(100)," + KEY_PHONE + " VARCHAR(100),"
                + KEY_EMAIL + " VARCHAR(100)" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("", "onCreate: ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Creating tables again
        onCreate(db);
    }

    // Adding new contact
    public long addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName()); //Name
        values.put(KEY_PHONE, contact.getPhone()); //Phone Number
        values.put(KEY_EMAIL,contact.getEmail());//Email

        // Inserting Row
        long rst = db.insert(TABLE_NAME, null, values);

        db.close(); // Closing database connection

        return rst;

    }

    //get all data in database
    public Map<String,Contact> getContactList(){

        //make a list to store results
        Map<String,Contact> contacts = new HashMap<>();

        //sql
        String sql = "select * from "+TABLE_NAME;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql,null);

        while(cursor.moveToNext()){

            Contact contact = new Contact();

            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contact.setEmail(cursor.getString(3));

            contacts.put(cursor.getString(0),contact);

        }

        return contacts;
    }


    public int updateContact(Contact contact,String id){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName()); //Name
        values.put(KEY_PHONE, contact.getPhone()); //Phone Number
        values.put(KEY_EMAIL,contact.getEmail());//Email

        int rst = db.update(TABLE_NAME,values,KEY_ID + "= ?" ,new String[]{id});

        return rst;
    }

    public int deleteContact(String id){

        SQLiteDatabase db = getWritableDatabase();

        int rst = db.delete(TABLE_NAME,KEY_ID + "= ?",new String[]{id});

        return rst;



    }

}

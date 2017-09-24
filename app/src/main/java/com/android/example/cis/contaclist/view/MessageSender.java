package com.android.example.cis.contaclist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.example.cis.contaclist.R;

public class MessageSender extends AppCompatActivity {

    Button send, reset;
    TextView name, phone;
    EditText message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_sender);

        send = (Button) findViewById(R.id.send);
        reset = (Button) findViewById(R.id.reset);

        name = (TextView) findViewById(R.id.nameLableSMS);
        phone = (TextView) findViewById(R.id.phoneLableSMS);

        message = (EditText) findViewById(R.id.messageText);

        savedInstanceState = getIntent().getExtras();
        name.setText("To: " + savedInstanceState.getString("NAME"));
        phone.setText(savedInstanceState.getString("PHONE"));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setText("");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
            }
        });


    }


    protected void sendSMSMessage() {

        String phoneNo = phone.getText().toString();
        String sms = message.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);


            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

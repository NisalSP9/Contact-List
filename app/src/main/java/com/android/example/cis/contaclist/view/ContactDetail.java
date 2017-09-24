package com.android.example.cis.contaclist.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.example.cis.contaclist.R;

import model.Contact;

public class ContactDetail extends AppCompatActivity {

    TextView nameText,phoneText,emailText;
    ImageButton call,sms, emailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        nameText = (TextView) findViewById(R.id.nameLable);
        phoneText = (TextView)findViewById(R.id.phoneLable);
        emailText = (TextView)findViewById(R.id.emailLable);
        call = (ImageButton)findViewById(R.id.call);
        sms = (ImageButton)findViewById(R.id.smsButton);
        emailBtn = (ImageButton)findViewById(R.id.emailButton);

        savedInstanceState = getIntent().getExtras();

        nameText.setText(savedInstanceState.getString("NAME"));
        phoneText.setText(savedInstanceState.getString("PHONE"));
        emailText.setText(savedInstanceState.getString("EMAIL"));

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               call(phoneText.getText().toString());
                //Toast.makeText(ContactDetail.this,"Calling "+phoneText.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms(nameText.getText().toString(),phoneText.getText().toString());
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailAddress =emailText.getText().toString();

                if (mailAddress.isEmpty()){

                    Toast.makeText(ContactDetail.this,"Email Address Not Found",Toast.LENGTH_LONG).show();

                }else {

                    sendMail(mailAddress);

                }


            }
        });

    }




    private void call(String number) {
        Intent makeCall =new Intent(Intent.ACTION_CALL);
        makeCall.setData(Uri.parse("tel:"+number));

        try{
            startActivity(makeCall);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void sms(String name,String number){

        Intent intent = new Intent(this,MessageSender.class);
        intent.putExtra("NAME",name);
        intent.putExtra("PHONE",number);
        startActivity(intent);



    }

    private void sendMail(String To) {

        Intent intent = new Intent(this,MailSender.class);
        intent.putExtra("TO",To);
        startActivity(intent);

    }
}

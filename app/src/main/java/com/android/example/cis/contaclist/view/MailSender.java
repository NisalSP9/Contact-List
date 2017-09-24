package com.android.example.cis.contaclist.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.example.cis.contaclist.R;

public class MailSender extends AppCompatActivity {

    TextView to;
    EditText cc,subject,message;
    Button send,reset,resetALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_sender);

        to = (TextView)findViewById(R.id.mailTo);

        savedInstanceState = getIntent().getExtras();

        final String recipient = savedInstanceState.getString("TO");
        to.setText("To : "+recipient);

        cc = (EditText)findViewById(R.id.ccText);
        subject = (EditText)findViewById(R.id.mailSubject);
        message = (EditText)findViewById(R.id.mailMessage);

        send = (Button)findViewById(R.id.mailSend);
        reset = (Button)findViewById(R.id.mailClear);
        resetALL = (Button)findViewById(R.id.resetAll);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setText("");
            }
        });

        resetALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cc.setText("");
                subject.setText("");
                message.setText("");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String CCofmail = cc.getText().toString();
                final String subjectString = subject.getText().toString();
                final String messageString = message.getText().toString();

                sendMail(recipient,CCofmail,subjectString,messageString);
            }
        });

    }


    private  void sendMail(String recipient,String cc,String mailSubject,String mailMssage){
        String[] TO = {recipient};
        String[] CC = {cc};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC,CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,mailMssage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MailSender.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }
}

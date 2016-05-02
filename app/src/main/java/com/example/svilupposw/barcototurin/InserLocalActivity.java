package com.example.svilupposw.barcototurin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class InserLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inser_local);

        Button registerLocal = (Button) findViewById(R.id.registerLocal);

        if(registerLocal != null)
            registerLocal.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    EditText regLocalName = (EditText) findViewById(R.id.regLocalName);
                    EditText regLocaladdress = (EditText) findViewById(R.id.regLocaladdress);
                    EditText regLocalType = (EditText) findViewById(R.id.regLocalType);
                    EditText regLocalManey = (EditText) findViewById(R.id.regLocalManey);
                    EditText regLocalContact = (EditText) findViewById(R.id.regLocalContact);
                    EditText regLocalHours = (EditText) findViewById(R.id.regLocalHours);

                    String name = regLocalName.getText().toString();
                    String address = regLocaladdress.getText().toString();
                    String type = regLocalType.getText().toString();
                    String maney = regLocalManey.getText().toString();
                    String contact = regLocalContact.getText().toString();
                    String hours = regLocalHours.getText().toString();

                    Firebase localRef = MyApplication.getMyFirebaseRef().child("Local");
                    Firebase newLocalRef = localRef.push();

                    Local item = new Local(name, address, type, maney, contact, hours);

                    item.setId(newLocalRef.getKey());

                    newLocalRef.setValue(item);

                    regLocalName.setText("");
                    regLocaladdress.setText("");
                    regLocalType.setText("");
                    regLocalManey.setText("");
                    regLocalContact.setText("");
                    regLocalHours.setText("");

                    Intent intent = new Intent(getApplicationContext(), ListLocalActivity.class);

                    startActivity(intent);
                }

            });
    }
}

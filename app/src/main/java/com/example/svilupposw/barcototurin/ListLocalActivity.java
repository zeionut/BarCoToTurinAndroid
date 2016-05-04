package com.example.svilupposw.barcototurin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class ListLocalActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_local);

        myAdapter = new MyAdapter(getApplicationContext());

        ListView listView = (ListView) findViewById(R.id.listLocal);

        if (listView != null) {

            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();

                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(getApplicationContext(), "Se riesco lo rimuovo", Toast.LENGTH_SHORT).show();

                    return true;
                }
            });

        }

        MyApplication.getMyFirebaseRef().child("Local").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildAdded", dataSnapshot.getKey());
                //viene chiamata per ogni elemento esistente della lista oppure quando un nuovo elemento viene aggiunto
                Local newPost = dataSnapshot.getValue(Local.class);
                myAdapter.addItem(newPost);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildChanged", dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("onChildRemoved", dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildMoved", "dataSnapshot.getKey()");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i("onCancelled", firebaseError.getMessage());
            }
        });

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        if(buttonAdd != null)
            buttonAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), InserLocalActivity.class);

                    startActivity(intent);

                }

            });

    }
}

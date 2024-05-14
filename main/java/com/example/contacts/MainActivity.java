package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //view
    private FloatingActionButton fab;
    private RecyclerView contactRv;
    //db
    private DbHelper dbHelper;
    //adapter
    private AdapterContact adapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init db
        dbHelper = new DbHelper(this);
        //initialization
        fab = findViewById(R.id.fab);
        contactRv = findViewById(R.id.contactRv);
        contactRv.setHasFixedSize(true);
        //add listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to new activity to add contact
                Intent intent = new Intent(MainActivity.this,AddEditContact.class);
                startActivity(intent);

            }
        });
        loadData();
    }

    private void loadData() {
        adapterContact = new AdapterContact(this,dbHelper.getAllData());
        contactRv.setAdapter(adapterContact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
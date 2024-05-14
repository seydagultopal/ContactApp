package com.example.contacts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditContact extends AppCompatActivity {
    private EditText nameEt, phoneEt, emailEt, noteEt;
    private FloatingActionButton fab;
    //String variable
    private String name, email, phone, note;
    //Action bar
    private ActionBar actionBar;
    private  DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);
        //init db
        dbHelper = new DbHelper(this);
        //init actionBar
        actionBar = getSupportActionBar();
        //set title in action bar
        actionBar.setTitle("Add Contact");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //init view
        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        emailEt = findViewById(R.id.emailEt);
        noteEt = findViewById(R.id.noteEt);
        fab = findViewById(R.id.fab);
        //add even handler
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });
    }
    private void saveData() {
        //take user giver data in variable
        name = nameEt.getText().toString();
        phone = phoneEt.getText().toString();
        email = emailEt.getText().toString();
        note = noteEt.getText().toString();
        //get current time to save as added time
        String timeStamp = ""+System.currentTimeMillis();
        //check filed data
        if (!name.isEmpty() || !phone.isEmpty() || !email.isEmpty() || !note.isEmpty()) {
            //save data, if user have only one data
            //function for save data on SQLLite database
            long id = dbHelper.insertContact(
                    ""+name,
                    ""+phone,
                    ""+email,
                    ""+note,
                    ""+timeStamp,
                    ""+timeStamp
            );
            //to check insert data successfully, show a toast message
            Toast.makeText(getApplicationContext(),"Inserted"+id,Toast.LENGTH_SHORT).show();
        } else {
            //show toast message
            Toast.makeText(getApplicationContext(), "Nothing to save", Toast.LENGTH_SHORT).show();
        }
    }
    //ctrl + o
    @Override
    //back button click
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher();
        return super.onSupportNavigateUp();
    }
}
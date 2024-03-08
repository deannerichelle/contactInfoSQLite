package com.zybooks.contactinfosqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contactNumber, age, faveColor;
    Button add, update, delete, view;
    tableHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.tv_name);
        contactNumber = findViewById(R.id.tv_contactNumber);
        age = findViewById(R.id.tv_age);
        faveColor = findViewById(R.id.tv_faveColor);

        add = findViewById(R.id.btnAdd);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new tableHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String contactTxt = contactNumber.getText().toString();
                String ageTxt = age.getText().toString();
                String faveColorTxt = faveColor.getText().toString();

                Boolean checkAddedData = DB.insertData(nameTxt, contactTxt, ageTxt, faveColorTxt);
                if(checkAddedData ==  true)
                    Toast.makeText(MainActivity.this, "New Entry Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry was not added", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String contactTxt = contactNumber.getText().toString();
                String ageTxt = age.getText().toString();
                String faveColorTxt = faveColor.getText().toString();

                Boolean checkUpdatedData = DB.updateData(nameTxt, contactTxt, ageTxt, faveColorTxt);
                if(checkUpdatedData ==  true)
                    Toast.makeText(MainActivity.this, "Entry updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry was not updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                Boolean checkDeletedData = DB.deleteData(nameTxt);
                if(checkDeletedData ==  true)
                    Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry was not deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "That entry doesn't exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name: " + res.getString(0)+ "\n");
                    buffer.append("Contact Number: " + res.getString(1)+ "\n");
                    buffer.append("Age: " + res.getString(2)+ "\n");
                    buffer.append("Favorite Color: " + res.getString(3)+ "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show(); //this will show the alert values
            }
        });
    }
}
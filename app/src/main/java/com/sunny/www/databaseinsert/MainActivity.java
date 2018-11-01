package com.sunny.www.databaseinsert;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyDBOpenHelper dbHelper;
    SQLiteDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDBOpenHelper(this,"awe.db",null, 1);
        mdb = dbHelper.getWritableDatabase();

        Button button = findViewById(R.id.buttonInsert);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText countryEditText = (EditText)findViewById(R.id.country);
        EditText cityEditText=(EditText)findViewById(R.id.city);

        mdb.execSQL("INSERT INTO awe_country VALUES(null,'"+countryEditText+"','"+cityEditText+"');");
    }
}

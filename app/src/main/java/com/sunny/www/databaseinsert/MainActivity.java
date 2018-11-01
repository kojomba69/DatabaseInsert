package com.sunny.www.databaseinsert;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Button button1= findViewById(R.id.buttonRead);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id;
        String country, capital;

        int idid=v.getId();
        switch (idid){
            case R.id.buttonInsert:

                break;
            case R.id.buttonRead:

                break;
            case R.id.buttonUpdate:

                break;
            case R.id.buttonDelet:

                break;
        }


        EditText countryEditText = (EditText)findViewById(R.id.country);
        country = countryEditText.getText().toString();
        EditText cityEditText=(EditText)findViewById(R.id.city);
        capital = cityEditText.getText().toString();

        TextView tvResult = (TextView)findViewById(R.id.edit);
        String query = "SELECT * FROM awe_country";
        Cursor cursor = mdb.rawQuery(query,null);
        String str="";
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
            country = cursor.getString(cursor.getColumnIndex("country"));
            capital=cursor.getString(2);
            str+=(id+":"+ country +"-"+capital+"\n");
        }
        if (str.length()>0){
            tvResult.setText(str);
        }
        else {
            Toast.makeText(getApplicationContext(),"Warning Empty DB", Toast.LENGTH_SHORT).show();
            tvResult.setText("");
        }

        mdb.execSQL("INSERT INTO awe_country VALUES(null,'"+countryEditText+"','"+cityEditText+"');");
    }
}

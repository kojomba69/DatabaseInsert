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

        dbHelper = new MyDBOpenHelper(this,"awe.db",null, 2);
        mdb = dbHelper.getWritableDatabase();

        Button button = findViewById(R.id.buttonInsert);
        button.setOnClickListener(this);
        Button button1= findViewById(R.id.buttonRead);
        button1.setOnClickListener(this);
        Button button2= findViewById(R.id.buttonUpdate);
        button2.setOnClickListener(this);
        Button button3= findViewById(R.id.buttonDelet);
        button3.setOnClickListener(this);
        Button button4=findViewById(R.id.buttonSearch);
        button4.setOnClickListener(this);
        Button button5= findViewById(R.id.buttonAddVisit);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText countryEditText = (EditText)findViewById(R.id.country);
        String country = countryEditText.getText().toString();
        EditText cityEditText=(EditText)findViewById(R.id.city);
        String capital = cityEditText.getText().toString();

        switch (v.getId()){
            case R.id.buttonInsert:
                mdb.execSQL("INSERT INTO awe_country VALUES(null,'"+country+"','"+capital+"');");
                break;
            case R.id.buttonRead:
                TextView tvResult = (TextView)findViewById(R.id.edit);
                String query = "SELECT * FROM awe_country order by _id desc";
                Cursor cursor = mdb.rawQuery(query,null);
                String str="";
                while (cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    country = cursor.getString(cursor.getColumnIndex("country"));
                    capital=cursor.getString(2);
                    str+=(id+":"+ country +"-"+capital+"\n");
                }
                tvResult.setText(str);
                break;
            case R.id.buttonUpdate:
                mdb.execSQL("UPDATE awe_country SET capital='"+capital+"' WHERE country='"+country+"'");
                break;

            case R.id.buttonDelet:
                mdb.execSQL("DELETE FROM awe_country WHERE country='"+country+"';");
                break;

            case R.id.buttonAddVisit:
                TextView textViewPkid = (TextView)findViewById(R.id.textViewPkid);
                String strPkID=textViewPkid.getText().toString();
                query="INSERT INTO awe_country_visitedcount VALUES('"+strPkID+"')";
                mdb.execSQL(query);
                break;

            case R.id.buttonSearch:
                country=countryEditText.getText().toString();
                query="SELECT pkid, country, capital, count(fkid) visitedTotal"+"FROM awe_country INNER JOIN awe_country_visitedcount"+
                        "ON pkid=fkid AND pkid='"+country+"'";
                cursor = mdb.rawQuery(query,null);
                if(cursor.getCount()>0){
                    cursor.moveToFirst();

                   // visitedTotal = cursor.getInt(cursor.getColumnIndex("visitedTotal"));
                    //textViewVisToCo.setText(String.valueOf(visitedTotal));

                }
                break;
        }


//        if (str.length()>0){
//            tvResult.setText(str);
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"Warning Empty DB", Toast.LENGTH_SHORT).show();
//            tvResult.setText("");
//        }
//

    }
}

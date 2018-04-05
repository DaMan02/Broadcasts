package com.dayal.broadcasts.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dayal.broadcasts.model.IncomingNumber;
import com.dayal.broadcasts.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Constants.DATABASE_NAME,null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_INCOMING_NUMBER + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
          db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
          onCreate(db);
    }

    public void saveToDb(String number){

        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_INCOMING_NUMBER,number);
        db.insert(Constants.TABLE_NAME,null,contentValues);
        Log.w("log","Saved to database,no : " + number);

    }

    public List<IncomingNumber> getNumber(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<IncomingNumber> numbers = new ArrayList<>();
        Cursor cursor = db.query(Constants.TABLE_NAME,new String[]{Constants.KEY_ID, Constants.KEY_INCOMING_NUMBER},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                IncomingNumber incomingNumber= new IncomingNumber();
                incomingNumber.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                incomingNumber.setNumber(cursor.getString(cursor.getColumnIndex(Constants.KEY_INCOMING_NUMBER)));
                numbers.add(incomingNumber);


            }while(cursor.moveToNext());
        }
         cursor.close();

        return numbers;
    }
}

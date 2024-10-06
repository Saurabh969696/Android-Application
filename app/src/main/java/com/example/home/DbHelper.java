package com.example.home;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "people.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "person";

    // Columns
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_AGE = "age";
    private static final String COL_CITY = "city";
    private static final String COL_PHONE = "phone";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY, "  // Removed AUTOINCREMENT
                + COL_NAME + " TEXT, "
                + COL_AGE + " INTEGER, "
                + COL_CITY + " TEXT, "
                + COL_PHONE + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data with manually entered ID
    public boolean insertData(String id, String name, int age, String city, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);  // Manually inputted ID
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_AGE, age);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_PHONE, phone);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // if result is -1, insertion failed
    }

    // Update data
    public boolean updateData(String id, String name, int age, String city, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_AGE, age);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_PHONE, phone);

        int result = db.update(TABLE_NAME, contentValues, COL_ID + " = ?", new String[]{id});
        return result > 0; // if result > 0, update was successful
    }

    // Delete data
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{id});
    }

    // Select all data
    public List<String> getAllData() {
        List<String> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String record = "ID: " + cursor.getString(0) + ", Name: " + cursor.getString(1)
                        + ", Age: " + cursor.getString(2) + ", City: " + cursor.getString(3)
                        + ", Phone: " + cursor.getString(4);
                dataList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }

    // Select data by ID
    public String[] getDataById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, age, city, phone FROM " + TABLE_NAME + " WHERE id = ?", new String[]{id});

        if (cursor != null && cursor.moveToFirst()) {
            String[] result = new String[4];
            result[0] = cursor.getString(0);  // name
            result[1] = cursor.getString(1);  // age
            result[2] = cursor.getString(2);  // city
            result[3] = cursor.getString(3);  // phone
            cursor.close();
            return result;
        }
        return null;  // No data found
    }
}

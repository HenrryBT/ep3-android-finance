package com.henrrybeltran.ep3.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteRepository extends SQLiteOpenHelper {
    public SQLiteRepository(@Nullable Context context) {
        super(context, "myBank", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tbl_movement(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "description TEXT," +
                "amount FLOAT, " +
                "movement INT)");
    }

    public int movementInsert(SQLiteRepository repository, String description, float amount, int movement){
        SQLiteDatabase sqLiteDatabase = repository.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("description", description);
        contentValues.put("amount", amount);
        contentValues.put("movement", movement);

        return (int) sqLiteDatabase.insert("tbl_movement", null, contentValues);
    }

    public Cursor movementSelect(SQLiteRepository repository){
        SQLiteDatabase sqLiteDatabase = repository.getReadableDatabase();
        String sql = "SELECT * FROM tbl_movement";

        return sqLiteDatabase.rawQuery(sql, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

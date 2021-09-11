package com.example.todoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todoapp.Models.Todos;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TodoList.db";
    public static final String TABLE_NAME = "TodoTable";
    public static final String COL_1 = "Title";
    public static final String COL_2 = "Time";
    public static final String COL_3 = "Date";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Time TEXT, Date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String title, String time, String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, title);
        contentValues.put(COL_2, time);
        contentValues.put(COL_3, date);

        long result = database.insert(TABLE_NAME, null, contentValues);
        database.close();

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<Todos> getAllData(){
        ArrayList<Todos> todoList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Todos todos = new Todos();
                todos.setTitle(cursor.getString(1));
                todos.setTime(cursor.getString(2));
                todos.setDate(cursor.getString(3));
                todoList.add(todos);
            }
        }
        return todoList;
    }

    public void deleteAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM "+TABLE_NAME);
        database.close();
    }
}

package com.example.weathergps.data.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String QUERY_TABLE = "QUERY_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LATITUDE = "LATITUDE";
    public static final String COLUMN_LONGITUDE = "LONGITUDE";
    public static final String COLUMN_AVG_TEMP = "AVG_TEMP";

    public DataBaseHelper(@Nullable Context context,
                          @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableStatement = "CREATE TABLE " + QUERY_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LATITUDE + " TEXT," +
                COLUMN_LONGITUDE + " TEXT," +
                COLUMN_AVG_TEMP + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean saveQuery(QueryModel queryModel){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_LATITUDE, queryModel.getLatitude());
        contentValues.put(COLUMN_LONGITUDE, queryModel.getLongitude());
        contentValues.put(COLUMN_AVG_TEMP, queryModel.getAvg_temp());

        return database.insert(QUERY_TABLE, null, contentValues) != -1;

    }

}

package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NguoiChoiDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DBNguoiChoi.db";

    public NguoiChoiDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NguoiChoiEnTry.TAO_BANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NguoiChoiEnTry.XOA_BANG);
        onCreate(sqLiteDatabase);
    }
    public long dangKy(NguoiChoiEnTry nguoiChoiEnTry){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NguoiChoiEnTry.COL_TENDANGNHAP, nguoiChoiEnTry.getTendangnhap());
        values.put(NguoiChoiEnTry.COL_EMAIL, nguoiChoiEnTry.getEmail());
        values.put(NguoiChoiEnTry.COL_MATKHAU, nguoiChoiEnTry.getMatkhau());
        values.put(NguoiChoiEnTry.COL_NHAPLAIMATKHAU, nguoiChoiEnTry.getNhaplaimatkhau());

        long insertID = sqLiteDatabase.insert(NguoiChoiEnTry.TABLE_NAME, null, values);
        return insertID;
    }
}

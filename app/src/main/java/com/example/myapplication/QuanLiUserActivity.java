package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class QuanLiUserActivity extends AppCompatActivity {

    private EditText mTen,mEmail,mMatKhau,mXNMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_user);
        mTen = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mMatKhau = findViewById(R.id.pass);
        mXNMatKhau = findViewById(R.id.repass);
    }

    public void CapNhat(View view) {

    }

    public void HuyForm(View view) {
        mTen.setText("");
        mEmail.setText("");
        mMatKhau.setText("");
        mXNMatKhau.setText("");

        mTen.requestFocus();
        mEmail.requestFocus();
        mMatKhau.requestFocus();
        mXNMatKhau.requestFocus();
    }
}

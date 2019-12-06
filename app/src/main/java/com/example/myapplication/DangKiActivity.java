package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class DangKiActivity extends AppCompatActivity {

    private EditText mTenDangNhapEdit, mEmailEdit, mMatKhauEdit, mResMatKhau;
    private NguoiChoiDBHelper ncDBHelper;
    private NguoiChoiEnTry ncEnTry;

    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        mTenDangNhapEdit = findViewById(R.id.username);
        mEmailEdit = findViewById(R.id.email);
        mMatKhauEdit = findViewById(R.id.pass);
        mResMatKhau = findViewById(R.id.repass);

        ncDBHelper = new NguoiChoiDBHelper(this);

        for(int i=0;i<20;i++){
            mWordList.addLast("NguoiChoi"+i);
        }
    }
    public void XoaForm(View view){
        mTenDangNhapEdit.setText("");
        mEmailEdit.setText("");
        mMatKhauEdit.setText("");
        mResMatKhau.setText("");

        mTenDangNhapEdit.requestFocus();
    }
    public void DangKy(View view) {
        String tendangnhap = mTenDangNhapEdit.getText().toString();
        String email = mEmailEdit.getText().toString();
        String matmhau = mMatKhauEdit.getText().toString();
        String nhaplaimatkhau = mResMatKhau.getText().toString();
        ncEnTry = new NguoiChoiEnTry(this,tendangnhap, email, matmhau, nhaplaimatkhau);
        long id = ncDBHelper.dangKy(ncEnTry);
        if(id>0){
            Toast.makeText(this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
            XoaForm(view);
        }else {
            Toast.makeText(this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
        }
    }
}

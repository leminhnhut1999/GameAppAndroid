package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button btnDangNhap,btnDangKy,btnThoat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        Anhxa();



    }
    private void Anhxa() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnThoat = findViewById(R.id.btnThoat);
    }

    public void DangNhap(View view) {

        if(username.getText().length() !=0 && password.getText().length() !=0)
        {
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("123456"))
            {
                Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this,ManHinhChinhActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MainActivity.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }
    }

    public void DangKy(View view) {
        Intent intent= new Intent(MainActivity.this ,DangKiActivity.class);
        startActivity(intent);
    }

    public void QuenMAtKhau(View view) {
        Intent intent= new Intent(this ,QuenMatKhauActivity.class);
        startActivity(intent);
    }
}

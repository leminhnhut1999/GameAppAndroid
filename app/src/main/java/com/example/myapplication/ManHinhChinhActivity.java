package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManHinhChinhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
    }

    public void QuanLy(View view) {
        Intent intent= new Intent(this ,QuanLiUserActivity.class);
        startActivity(intent);
    }

    public void ChonLinhVuc(View view) {
        Intent intent= new Intent(this ,ChonLinhVucActivity.class);
        startActivity(intent);
    }

    public void MuaCredit(View view) {
        Intent intent= new Intent(this ,ManHinhMuaCredit.class);
        startActivity(intent);
    }
    public void LichSuChoi(View view){
        Intent intent= new Intent(this ,ManHinhLichSuChoi.class);
        startActivity(intent);
    }
    public void XepHang(View view){
        Intent intent= new Intent(this ,ManHinhBanXepHang.class);
        startActivity(intent);
    }
}

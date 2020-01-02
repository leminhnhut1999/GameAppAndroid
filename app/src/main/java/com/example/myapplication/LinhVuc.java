package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LinhVuc extends AppCompatActivity {
    private int id;
    private String ten_linh_vuc;

    public LinhVuc(int id, String ten_linh_vuc) {
        this.id = id;
        this.ten_linh_vuc = ten_linh_vuc;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_linh_vuc() {
        return ten_linh_vuc;
    }

    public void setTen_linh_vuc(String ten_linh_vuc) {
        this.ten_linh_vuc = ten_linh_vuc;
    }
}

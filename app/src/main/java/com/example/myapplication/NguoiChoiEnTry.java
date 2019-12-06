package com.example.myapplication;

import android.content.Context;

public class NguoiChoiEnTry {
    public static final String TABLE_NAME = "nguoi_choi";
    public static final String COL_ID = "id";
    public static final String COL_TENDANGNHAP = "tendangnhap";
    public static final String COL_EMAIL = "email";
    public static final String COL_MATKHAU = "matkhau";
    public static final String COL_NHAPLAIMATKHAU = "nhaplaimatkhau";

    public static final String TAO_BANG = "CREATE TABLE "+TABLE_NAME + "("+COL_ID+" INTEGER PRIMARY KEY,"+ COL_TENDANGNHAP +" TEXT,"+COL_EMAIL+" TEXT,"+COL_MATKHAU + " TEXT,"+COL_NHAPLAIMATKHAU +" TEXT)";

    public static final String XOA_BANG ="DROP TABLE IF EXITS"+TABLE_NAME;

    private long id;
    private String tendangnhap;
    private String email;
    private String matkhau;
    private String nhaplaimatkhau;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNhaplaimatkhau() {
        return nhaplaimatkhau;
    }

    public void setNhaplaimatkhau(String nhaplaimatkhau) {
        this.nhaplaimatkhau = nhaplaimatkhau;
    }

    public NguoiChoiEnTry(DangKiActivity dangKiActivity, String tendangnhap, String email, String matmhau, String nhaplaimatkhau){
        this.id=0;
        this.tendangnhap="";
        this.email="";
        this.matkhau="";
        this.nhaplaimatkhau="";
    }
    public NguoiChoiEnTry(String tendangnhap,String email, String matkhau, String nhaplaimatkhau){
        this.id=0;
        this.tendangnhap=tendangnhap;
        this.email=email;
        this.matkhau=matkhau;
        this.nhaplaimatkhau=nhaplaimatkhau;
    }
    public NguoiChoiEnTry(long id, String tendangnhap, String email, String matkhau, String nhaplaimatkhau){
        this.id=id;
        this.tendangnhap=tendangnhap;
        this.email=email;
        this.matkhau=matkhau;
        this.nhaplaimatkhau=nhaplaimatkhau;
    }
}

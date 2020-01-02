package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPref;
    private String sharePrefFile = "com.example.myapplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getSharedPreferences(sharePrefFile, MODE_PRIVATE);

        //Kiểm tra Token trong Shared Preferences
        //Nếu có Token thì chuyển qua màn hình Chính

        String token = mPref.getString("TOKEN",null);
        int id = mPref.getInt("id",0);
        if(token != null)
        {
            //Mở activity màn hình chính
            Intent intent = new Intent(this, ManHinhChinh.class);
            startActivity(intent);
        }
    }

    public void dangNhap(View view)
    {
        EditText mTenDangNhap = findViewById(R.id.ten_dang_nhap_text);
        EditText mMatKhau = findViewById(R.id.mat_khau_text);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null)
        {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected())
        {
            new FetchDangNhap(){
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        // Lấy giá trị của key "success"
                        boolean success = jsonObject.getBoolean("status");
                        String message = jsonObject.getString("message");
                        if(success) {
                            // Lưu token vao Shared Preferences
                            String token = jsonObject.getString("token");

                            SharedPreferences.Editor editor = mPref.edit();
                            editor.putString("TOKEN", token);
                            editor.apply();

                            // Mở activity Màn Hình Chính
                            Intent intent = new Intent(MainActivity.this, ManHinhChinh.class);
                            startActivity(intent);
                        }
                        else
                            {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }.execute("dang-nhap", "POST", mTenDangNhap.getText().toString(), mMatKhau.getText().toString());

        }
        else
            {
            Toast.makeText(this, "Khong the ket noi den server", Toast.LENGTH_SHORT).show();
            }
        }

    public void Dangky(View view) {
        Intent intent = new Intent(this,DangKy.class);
        startActivity(intent);
    }
}
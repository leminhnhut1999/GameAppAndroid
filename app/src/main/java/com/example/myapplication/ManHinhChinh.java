package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ManHinhChinh extends AppCompatActivity {
    Button TroChoiMoi, HuongDan;
    private String sharedPrefFile = "com.example.myapplication";
    private SharedPreferences mPreferences;
    private TextView mThongBao, mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        mThongBao = findViewById(R.id.thong_bao_text);
        mToken = findViewById(R.id.token_text);

        //Lấy token trong sharedPreferences
        String token = mPreferences.getString("TOKEN", "");
//        mToken.setText(token);

        TroChoiMoi = (Button) findViewById(R.id.troChoiMoi_button);


       TroChoiMoi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ManHinhChinh.this,ChonLinhVuc.class);
               startActivity(intent);
           }
       });
    }

    public void HuongDan(View view)
    {
       Intent intent = new Intent(this,LuatChoi.class);
       startActivity(intent);
    }


    public void dangXuat(View view) {
        // Xóa token trong SharedPreferences
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goiAPI(View view) {

        String token = mPreferences.getString("TOKEN", null);

        new FetchAPIToken() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    mThongBao.setText(jsonObject.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute("lay-thong-tin", "GET", token);
    }
}
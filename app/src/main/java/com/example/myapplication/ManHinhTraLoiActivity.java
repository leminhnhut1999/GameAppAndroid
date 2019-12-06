package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class ManHinhTraLoiActivity extends AppCompatActivity {

    TextView txt_thoigian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tra_loi);

        txt_thoigian = (TextView)findViewById(R.id.txtThoigian);

        CountDownTimer countDownTimer;
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                txt_thoigian.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                txt_thoigian.setText("Hết Giờ");

            }
        };
        countDownTimer.start();
    }

}

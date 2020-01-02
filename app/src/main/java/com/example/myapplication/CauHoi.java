package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {
    private Button btn_Caua, btn_Caub, btn_Cauc, btn_Caud;
    private TextView txt_Noidung;
    TextView txt_thoigian;

    private final ArrayList<cls_CauHoi> cauHoiArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);

        btn_Caua = findViewById(R.id.btn_CauA);
        btn_Caub = findViewById(R.id.btn_CauB);
        btn_Cauc = findViewById(R.id.btn_CacC);
        btn_Caud = findViewById(R.id.btn_CauD);
        txt_Noidung = findViewById(R.id.txt_CauHoi);

        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);

        txt_thoigian = (TextView)findViewById(R.id.textView4);
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
    @NonNull
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args)
    {
        Intent intent = getIntent();
        int lv_id = intent.getIntExtra("Id_linh_vuc",0);
        return new CauHoiLoader(this,lv_id);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
       try{
           JSONObject jsonObject =new JSONObject(data);
           JSONArray itemsArray = jsonObject.getJSONArray("data");
           for (int i = 0; i < itemsArray.length(); i++) {
               int id = itemsArray.getJSONObject(i).getInt("id");
               int linhvucid = itemsArray.getJSONObject(i).getInt("linh_vuc_id");
               String noiDung = itemsArray.getJSONObject(i).getString("noi_dung");
               String phuong_an_a = itemsArray.getJSONObject(i).getString("phuong_an_a");
               String phuong_an_b = itemsArray.getJSONObject(i).getString("phuong_an_b");
               String phuong_an_c = itemsArray.getJSONObject(i).getString("phuong_an_c");
               String phuong_an_d = itemsArray.getJSONObject(i).getString("phuong_an_d");
               String dap_an = itemsArray.getJSONObject(i).getString("dap_an");
               this.cauHoiArrayList.add(new cls_CauHoi(id, linhvucid, noiDung, phuong_an_a, phuong_an_b, phuong_an_c, phuong_an_d, dap_an));
           }
       }
        catch (JSONException e) {
           e.printStackTrace();
       }
        //Hiển thị câu hỏi lên các button
        txt_Noidung.setText(cauHoiArrayList.get(0).getNoi_dung());
        btn_Caua.setText(cauHoiArrayList.get(0).getPhuong_an_a());
        btn_Caub.setText(cauHoiArrayList.get(0).getPhuong_an_b());
        btn_Cauc.setText(cauHoiArrayList.get(0).getPhuong_an_c());
        btn_Caud.setText(cauHoiArrayList.get(0).getPhuong_an_d());
    }

    public void tro_giup_khan_gia(View view)
    {
        final Dialog dialog = new Dialog(this);

    }

    public void tro_giup_5050(View view) {
        Button button = findViewById(R.id.btn_CauA);
        button.setVisibility(View.INVISIBLE);
        Button button2 = findViewById(R.id.btn_CauB);
        button2.setVisibility(View.INVISIBLE);
        ImageView button3 = findViewById(R.id.img_5050);
        button3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}

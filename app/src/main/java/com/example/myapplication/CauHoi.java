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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {
//    private Button btn_Caua, btn_Caub, btn_Cauc, btn_Caud;
    private Button [] buttons=new Button[4];
    private TextView txt_Noidung;
    TextView txt_thoigian;

    private final ArrayList<cls_CauHoi> cauHoiArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);

        buttons[0] = findViewById(R.id.btn_CauA);
        buttons[1] = findViewById(R.id.btn_CauB);
        buttons[2] = findViewById(R.id.btn_CauC);
        buttons[3] = findViewById(R.id.btn_CauD);
        txt_Noidung = findViewById(R.id.txt_CauHoi);
        txt_thoigian = (TextView)findViewById(R.id.textView4);

        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);
        txt_thoigian.setText("hi");
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
        buttons[0].setText(cauHoiArrayList.get(0).getPhuong_an_a());
        buttons[1].setText(cauHoiArrayList.get(0).getPhuong_an_b());
        buttons[2].setText(cauHoiArrayList.get(0).getPhuong_an_c());
        buttons[3].setText(cauHoiArrayList.get(0).getPhuong_an_d());

    }

    public void tro_giup_khan_gia(View view)
    {
        final Dialog dialog = new Dialog(this);

    }

    public void tro_giup_5050(View view) {
        String dapAn = cauHoiArrayList.get(0).getDapan();
        String cauTraLoi;
        for(int i=0;i<4;i++){
            cauTraLoi=buttons[i].getText().toString();
            if(dapAn.equals(cauTraLoi)){
                if(i==0){
                    setInvisibility(1,3);
                }
                if(i==1){
                    setInvisibility(0,2);
                }
                if(i==2){
                    setInvisibility(0,3);
                }
                if(i==3){
                    setInvisibility(0,1);
                }
                break;
            }
        }

    }
    public void setInvisibility(int a, int b){
        buttons[a].setVisibility(View.INVISIBLE);
        buttons[b].setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void rollback(View view) {

        Intent intentid = getIntent();
        ImageView imageView = findViewById(R.id.img_rollback);
        imageView.setVisibility(View.INVISIBLE);
        int lv_id = intentid.getIntExtra("Id_linh_vuc",0);
        Intent intent = new Intent(this,CauHoi.class);
        intent.putExtra("Id_linh_vuc",lv_id);
        startActivity(intent);

    }


    public void selectA(View view) {
        String dapAn = cauHoiArrayList.get(0).getDapan();
        String cauTraLoi = buttons[0].getText().toString();
        Intent intent = new Intent(this,ChonLinhVuc.class);
        if(dapAn.equals(cauTraLoi)){
            buttons[0].setBackgroundColor(getColor(R.color.Blue));
            Toast.makeText(this,"Bạn đã trả lời đúng",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
           buttons[0].setBackgroundColor(getColor(R.color.Red));
            Toast.makeText(this,"Bạn đã trả lời sai",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    public void selectD(View view) {
        String dapAn = cauHoiArrayList.get(0).getDapan();
        String cauTraLoi = buttons[3].getText().toString();
        Intent intent = new Intent(this,ChonLinhVuc.class);
        if(dapAn.equals(cauTraLoi)){
            buttons[3].setBackgroundColor(getColor(R.color.Blue));
            Toast.makeText(this,"Bạn đã trả lời đúng",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            buttons[3].setBackgroundColor(getColor(R.color.Red));
            Toast.makeText(this,"Bạn đã trả lời sai",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    public void selectC(View view) {
        String dapAn = cauHoiArrayList.get(0).getDapan();
        String cauTraLoi = buttons[2].getText().toString();
        Intent intent = new Intent(this,ChonLinhVuc.class);
        if(dapAn.equals(cauTraLoi)){
            buttons[2].setBackgroundColor(getColor(R.color.Blue));
            Toast.makeText(this,"Bạn đã trả lời đúng",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            buttons[2].setBackgroundColor(getColor(R.color.Red));
            Toast.makeText(this,"Bạn đã trả lời sai",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    public void selectB(View view) {
        String dapAn = cauHoiArrayList.get(0).getDapan();
        String cauTraLoi = buttons[1].getText().toString();
        Intent intent = new Intent(this,ChonLinhVuc.class);
        if(dapAn.equals(cauTraLoi)){
            buttons[1].setBackgroundColor(getColor(R.color.Blue));
            Toast.makeText(this,"Bạn đã trả lời đúng",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            buttons[1].setBackgroundColor(getColor(R.color.Red));
            Toast.makeText(this,"Bạn đã trả lời sai",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }
}

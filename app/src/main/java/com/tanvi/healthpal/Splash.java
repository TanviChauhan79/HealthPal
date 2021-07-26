package com.tanvi.healthpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private ImageView img_logo;
    private TextView txt_moto,txt_lable;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        intail();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Splash.this,login.class);
                startActivity(intent);
                finish();
            }
        });


    }
    private void intail(){
        img_logo=findViewById(R.id.img_splash_logo);
        txt_lable=findViewById(R.id.txt_splash_lable);
        txt_moto=findViewById(R.id.txt_splash_moto);
        start=findViewById(R.id.btn_splash_start);
    }

}
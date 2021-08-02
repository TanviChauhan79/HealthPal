package com.tanvi.healthpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.tanvi.healthpal.user.login;

public class MainActivity extends AppCompatActivity {
    // widget
    Button btnLogOut;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalize();

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, login.class));
                finish();
            }
        });

    }

    private void initalize(){
        btnLogOut=findViewById(R.id.btn_logout);
        auth=FirebaseAuth.getInstance();
    }
}
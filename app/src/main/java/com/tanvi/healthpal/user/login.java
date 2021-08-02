package com.tanvi.healthpal.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.tanvi.healthpal.MainActivity;
import com.tanvi.healthpal.R;

public class login extends AppCompatActivity {

    private EditText edt_email,edt_password;
    private TextView txt_forgetpassword,txt_sinup;
    private Button bun_login;
    private FirebaseAuth firebaseAuth;
    //private FirebaseUser currentUser;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        txt_sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
        bun_login.setOnClickListener(view -> {
            String em,pass;
            em=edt_email.getText().toString();
            pass=edt_password.getText().toString();

            if(TextUtils.isEmpty(edt_email.getText().toString())){
                edt_email.setError("Email cannot be empty!!");
                edt_email.requestFocus();
            }
            else if(TextUtils.isEmpty(edt_password.getText().toString())){
                edt_password.setError("Password cannot be empty!!");
                edt_password.requestFocus();
            }else{
                loginUser(em, pass);
            }
        });

    }
    private void initialize(){
        edt_email=findViewById(R.id.edt_login_email);
        edt_password=findViewById(R.id.edt_login_password);
        txt_forgetpassword=findViewById(R.id.txt_login_forgotpassword);
        txt_sinup=findViewById(R.id.txt_login_signup);
        bun_login=findViewById(R.id.btn_login_loginbtn);
        firebaseAuth= FirebaseAuth.getInstance();
    }
    private void loginUser(String em,String pass){
        firebaseAuth.signInWithEmailAndPassword(em,pass)
                .addOnCompleteListener(this,task -> {
                    if(task.isSuccessful()){
                        Log.i(TAG,"login success");
                        Toast.makeText(login.this,"Login Success ful",Toast.LENGTH_SHORT).show();
                        //currentUser=firebaseAuth.getCurrentUser();
                        startActivity(new Intent(login.this, MainActivity.class));
                        finish();
                    }else {
                        bun_login.setError("Password cannot be empty!!");
                        bun_login.requestFocus();
                        Log.e(TAG,"login not success because of no user and pass");
                        Toast.makeText(login.this,"Login not success",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
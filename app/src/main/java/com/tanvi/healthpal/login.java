package com.tanvi.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class login extends AppCompatActivity {
    private EditText edt_email,edt_password;
    private TextView txt_forgetpassword,txt_sinup;
    private Button bun_login;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intalise();
        txt_sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
                finish();
            }
        });
        bun_login.setOnClickListener(view -> {
            String em,pass;
            em=edt_email.getText().toString();
            pass=edt_password.getText().toString();
            loginUser(em,pass);
        });
    }
    private void intalise(){
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
                        Toast.makeText(login.this,"Login Success ful",Toast.LENGTH_SHORT).show();
                        currentUser=firebaseAuth.getCurrentUser();
                        startActivity(new Intent(login.this,MainActivity.class));
                    }else {
                        Toast.makeText(login.this,"Login not success",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
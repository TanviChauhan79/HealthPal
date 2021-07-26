package com.tanvi.healthpal;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText edt_email,edt_password;
    private TextView txt_forgetpassword,txt_sinup;
    private Button bun_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initalizaition
        edt_email=findViewById(R.id.edt_login_email);
        edt_password=findViewById(R.id.edt_login_password);
        txt_forgetpassword=findViewById(R.id.txt_login_forgotpassword);
        txt_sinup=findViewById(R.id.txt_login_signup);
        bun_login=findViewById(R.id.btn_login_loginbtn);
        txt_sinup.setOnClickListener(new View.OnClickListener() {//moving to register page
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
        });
    }
}
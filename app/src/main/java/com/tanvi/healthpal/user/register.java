package com.tanvi.healthpal.user;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tanvi.healthpal.R;

import java.util.HashMap;
import java.util.Map;
public class register extends AppCompatActivity {
    private EditText  edt_email,edt_password,edt_repassword,edt_firstName,edt_lastName,
            edt_addres,edt_phone;
    private Button btn_signup;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initalise();
        btn_signup.setOnClickListener(view -> {
            if(!checkEmptyFiels()){
                if(edt_password.getText().length() <6){
                    edt_password.setError("Invalid Password, Password should be greater than 6 characters");
                    edt_password.requestFocus();
                }else{
                    if(! edt_password.getText().toString().equals(edt_repassword.getText().toString())){
                        edt_repassword.setError("Invalid Password, Password did not match");
                        edt_repassword.requestFocus();
                    }
                    else {
                        String email=edt_email.getText().toString();
                        String password=edt_password.getText().toString();
                        String firstName=edt_firstName.getText().toString();
                        String lastName=edt_lastName.getText().toString();
                        String address=edt_addres.getText().toString();
                        String phoneNumber=edt_phone.getText().toString();
                        user user =new user(email,password,firstName,lastName,address,phoneNumber);
                        createUser(user);
                    }
                }
            }
        });
    }
    private void initalise(){
        edt_email=findViewById(R.id.edt_register_email);
        edt_password=findViewById(R.id.edt_register_password);
        edt_repassword=findViewById(R.id.edt_register_repassword);
        edt_firstName=findViewById(R.id.edt_register_firstName);
        edt_lastName=findViewById(R.id.edt_register_lastName);
        edt_addres=findViewById(R.id.edt_register_addres);
        edt_phone=findViewById(R.id.edt_register_phoneNumber);
        btn_signup=findViewById(R.id.btn_register_signup);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
    }
    private boolean checkEmptyFiels(){
        if(TextUtils.isEmpty(edt_email.getText().toString())){
            edt_email.setError("Email cannot be empty!!");
            edt_email.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_password.getText().toString())){
            edt_password.setError("Password cannot be empty!!");
            edt_password.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_repassword.getText().toString())){
            edt_repassword.setError("Email cannot be empty!!");
            edt_repassword.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_firstName.getText().toString())){
            edt_firstName.setError("Email cannot be empty!!");
            edt_firstName.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_lastName.getText().toString())){
            edt_lastName.setError("Email cannot be empty!!");
            edt_lastName.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_addres.getText().toString())){
            edt_addres.setError("Email cannot be empty!!");
            edt_addres.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_phone.getText().toString())){
            edt_phone.setError("Email cannot be empty!!");
            edt_phone.requestFocus();
            return true;
        }
        return false;
    }
    public void createUser(user user){
        auth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(this,task -> {
                    if(task.isSuccessful()){
                        FirebaseUser firebaseUser=auth.getCurrentUser();
                        writeFireStore(user,firebaseUser);
                    }
                    else {
                        Toast.makeText(register.this,"Registration Errror!!!!!!!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void writeFireStore(user user, FirebaseUser firebaseUser){
        Map<String,Object> userMap=new HashMap<>();
        userMap.put("firstName",user.getFirstName());
        userMap.put("lastName",user.getLastName());
        userMap.put("address",user.getAddress());
        userMap.put("phoneNumber",user.getPhoneNumber());
        firestore.collection("User").document(firebaseUser.getUid())
                .set(userMap).addOnCompleteListener(this,task -> {
            if(task.isSuccessful()){
                Toast.makeText(this,"Registration succesful",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(register.this,login.class));
                finish();
            }
            else {
                Toast.makeText(this,"Firestore Errror!!!!!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
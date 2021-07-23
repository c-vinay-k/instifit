package com.example.instifit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

  private   EditText loginEmail,loginPassword;
  private   Button signupButton,login,forgotPassword;
  private   TextView loginAppTitle,loginFooterText;
  private   CheckBox showPassword;
  private   FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);
        signupButton=findViewById(R.id.signupButton);
        login=findViewById(R.id.login);
        forgotPassword=findViewById(R.id.forgotPassword);
        loginAppTitle=findViewById(R.id.loginAppTitle);
        loginFooterText=findViewById(R.id.loginFooterText);
        showPassword=findViewById(R.id.showPassword);
        mAuth=FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
                finish();
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    loginPassword.setTransformationMethod(null);
                }else{
                    loginPassword.setTransformationMethod(new PasswordTransformationMethod());
                }

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgotPassword.class));
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String emailstr = loginEmail.getText().toString();
               String passwordstr = loginPassword.getText().toString();

               if(loginEmail.length()==0){
                   loginEmail.setError("Fill Email");
               }
               if(loginPassword.length()==0){
                   loginPassword.setError("Fill Password");
               }

               if(!TextUtils.isEmpty(emailstr)&&!TextUtils.isEmpty(passwordstr)){
                   mAuth.signInWithEmailAndPassword(emailstr,passwordstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(mAuth.getCurrentUser().isEmailVerified()){


                               if(task.isSuccessful()){

                                   if (mAuth.getCurrentUser().isEmailVerified()) {
                                       startActivity(new Intent(Login.this,MainActivity.class));finish();
                                   }else{
                                       Toast.makeText(Login.this,"Please Verify your email",Toast.LENGTH_SHORT).show();
                                   }
                               }else {
                                   Toast.makeText(Login.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                               }





                           }else{
                               Toast.makeText(Login.this,"Verify your account by mail sent to your email address",Toast.LENGTH_SHORT).show();
                           }




                       }
                   });
               }else {
                   Toast.makeText(Login.this,"Fill all Fields",Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(Login.this,Login.class));
               }

           }
       }) ;



    }
}
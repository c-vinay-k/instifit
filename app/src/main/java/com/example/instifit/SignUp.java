package com.example.instifit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

   private EditText signupEmail,signupPassword,signupRePassword;
   private Button loginButton,signup;
   private TextView signupAppTitle,signupFooterText;
   private CheckBox signupShowPassword;

   private FirebaseAuth mAuth;
   private boolean poss;
   private String verify1,verify2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupEmail=findViewById(R.id.signupEmail);
        signupPassword=findViewById(R.id.signupPassword);
        signupRePassword=findViewById(R.id.signupRePassword);
        loginButton=findViewById(R.id.loginButton);
        signup=findViewById(R.id.signup);
        signupAppTitle=findViewById(R.id.signupAppTitle);
        signupFooterText=findViewById(R.id.signupFooterText);
        signupShowPassword=findViewById(R.id.signupShowPassword);

        mAuth=FirebaseAuth.getInstance();
        verify1="@iitb.ac.in";
        verify2="@gmail.com";


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
                finish();
            }
        });

        signupShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    signupPassword.setTransformationMethod(null);
                    signupRePassword.setTransformationMethod(null);

                }else{
                    signupPassword.setTransformationMethod(new PasswordTransformationMethod());
                    signupRePassword.setTransformationMethod(new PasswordTransformationMethod());
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr = signupEmail.getText().toString();
                String passwordstr = signupPassword.getText().toString();
                String repasswordstr = signupRePassword.getText().toString();
                poss = emailstr.contains(verify1);

                if(signupEmail.length()==0){
                    signupEmail.setError("Fill Email Address");
                }
                if(signupPassword.length()==0){
                    signupPassword.setError("Fill Password");
                }
                if(signupRePassword.length()==0){
                    signupRePassword.setError("Re enter your password");
                }



                if(poss){

                    if(!TextUtils.isEmpty(emailstr) && !TextUtils.isEmpty(passwordstr) && !TextUtils.isEmpty(repasswordstr)){
                        if(passwordstr.equals(repasswordstr)){
                            mAuth.createUserWithEmailAndPassword(emailstr,passwordstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){


                                        mAuth.getCurrentUser().sendEmailVerification();


                                        Toast.makeText(SignUp.this,"Verification Email has been sent.Please check your email and then Login",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUp.this, Login.class));
                                        finish();


                                    }else{
                                        Toast.makeText(SignUp.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }else {
                            Toast.makeText(SignUp.this,"Re check entered Passwords",Toast.LENGTH_SHORT).show();
                        }




                    }else {
                        Toast.makeText(SignUp.this,"Enter all credentials",Toast.LENGTH_SHORT).show();
                    }




                }else{
                    Toast.makeText(SignUp.this,"Enter valid Email address",Toast.LENGTH_SHORT).show();
                }





            }
        });

    }
}
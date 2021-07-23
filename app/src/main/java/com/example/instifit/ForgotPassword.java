package com.example.instifit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private TextView textView1,textView2,forgotPasswordFooterText;
    private Button  forgotPasswordLoginButton,send;
    private EditText forgotPasswordEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        forgotPasswordLoginButton=findViewById(R.id.forgotPasswordLoginButton);
        send=findViewById(R.id.send);
        forgotPasswordEmail=findViewById(R.id.forgotPasswordEmail);
        forgotPasswordFooterText=findViewById(R.id.forgotPasswordFooterText);

        mAuth=FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailstr;
                mailstr=forgotPasswordEmail.getText().toString();

                if(forgotPasswordEmail.length()==0){
                    forgotPasswordEmail.setError("Enter Email address");
                }

                if(!TextUtils.isEmpty(mailstr)){

                    mAuth.sendPasswordResetEmail(mailstr).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPassword.this,"Password reset link has been emailed to you",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPassword.this,FirstPage.class));
                                finish();
                            }else {
                                Toast.makeText(ForgotPassword.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }else {
                    Toast.makeText(ForgotPassword.this,"Please Fill your Email",Toast.LENGTH_SHORT).show();
                }




            }
        });


        forgotPasswordLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,Login.class));
                finish();
            }
        });



    }
}
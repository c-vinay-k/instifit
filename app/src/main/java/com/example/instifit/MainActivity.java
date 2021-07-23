package com.example.instifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String current_user_id;
     ImageView home,signout;
     Button profile;



    @Override
    protected void onStart(){
        super.onStart();
        if(current_user_id==null || !(mAuth.getCurrentUser().isEmailVerified())){
            startActivity(new Intent(MainActivity.this,FirstPage.class));
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        current_user_id=mAuth.getUid();

        signout=findViewById(R.id.signout);
        home=findViewById(R.id.home);
        profile=findViewById(R.id.profile);


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Logging you out",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,FirstPage.class));
                finish();
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Opening Profile Page",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);

            }
        });







    }
}
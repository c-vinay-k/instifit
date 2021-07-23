package com.example.instifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instifit.Exercises.Burpees;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private Button burpees,pushups,situps,plank,bicyclecrunches,donkeykick,jumpsquats,birddog,stationarylunge,sidehip;
    private ImageView homeBack,profileView,signout;
    private FirebaseAuth mAuth;
    private String current_user_id;

    @Override
    protected void onStart(){
        super.onStart();
        if(current_user_id==null || !(mAuth.getCurrentUser().isEmailVerified())){
            startActivity(new Intent(Home.this,FirstPage.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth=FirebaseAuth.getInstance();
        current_user_id=mAuth.getUid();

        homeBack=findViewById(R.id.homeBack);
        burpees=findViewById(R.id.burpees);
        pushups=findViewById(R.id.pushups);
        situps=findViewById(R.id.situps);
        plank=findViewById(R.id.plank);
        bicyclecrunches=findViewById(R.id.bicyclecrunches);
        donkeykick=findViewById(R.id.donkeykick);
        jumpsquats=findViewById(R.id.jumpsquats);
        birddog=findViewById(R.id.birddog);
        stationarylunge=findViewById(R.id.stationarylunge);
        sidehip=findViewById(R.id.sidehip);
        profileView=findViewById(R.id.profileView);
        signout=findViewById(R.id.signout);



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this,"Logging you out",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(Home.this,FirstPage.class));
                finish();
            }
        });


        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,MainActivity.class));
                finish();
            }
        });

        burpees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.Burpees.class));
                finish();
            }
        });

        pushups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.PushUps.class));
                finish();
            }
        });

        situps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.SitUps.class));
                finish();
            }
        });

        plank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.Plank.class));
                finish();
            }
        });

        bicyclecrunches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.BicycleCrunches.class));
                finish();
            }
        });

        donkeykick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.DonkeyKick.class));
                finish();
            }
        });

        jumpsquats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.JumpSquats.class));
                finish();
            }
        });

        birddog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.BirdDog.class));
                finish();
            }
        });

        stationarylunge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.StationaryLunge.class));
                finish();
            }
        });

        sidehip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, com.example.instifit.Exercises.SideHip.class));
                finish();
            }
        });

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,ViewProfile.class));
                finish();
            }
        });



    }
}
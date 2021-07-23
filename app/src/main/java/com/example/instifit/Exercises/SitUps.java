package com.example.instifit.Exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instifit.Home;
import com.example.instifit.R;

public class SitUps extends AppCompatActivity {

    private ImageView back;
    private TextView reps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_ups);


        back=findViewById(R.id.situpsback);
        reps=findViewById(R.id.reps);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.instifit.Exercises.SitUps.this, Home.class));
            }
        });

        SharedPreferences sp = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        float val = sp.getFloat("bmi", 0.0f);

        /* Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        double val;
        val = Integer.parseInt(str); */

        if(val < 20){
            reps.setText("No.of reps per set (as per your BMI) = 4");
        }else if(val < 24){
            reps.setText("No.of reps per set (as per your BMI) = 6");
        }else{
            reps.setText("No.of reps per set (as per your BMI) =8");
        }




    }
}
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

public class DonkeyKick extends AppCompatActivity {

    private ImageView back;
    private TextView reps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donkey_kick);

        back=findViewById(R.id.donkeykickback);
        reps=findViewById(R.id.reps);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.instifit.Exercises.DonkeyKick.this, Home.class));
            }
        });

        SharedPreferences sp = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        float val = sp.getFloat("bmi", 0.0f);

        /* Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        double val;
        val = Integer.parseInt(str); */

        if(val < 20){
            reps.setText("No.of reps per set on each side (as per your BMI) = 7");
        }else if(val < 24){
            reps.setText("No.of reps per set on each side (as per your BMI) = 10");
        }else{
            reps.setText("No.of reps per set on each side (as per your BMI) = 12");
        }




    }
}
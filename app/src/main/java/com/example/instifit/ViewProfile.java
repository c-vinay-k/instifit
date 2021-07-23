package com.example.instifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {

    TextView nameText,ageText,weightText,heightText,bmiText;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        weightText = findViewById(R.id.weightText);
        heightText = findViewById(R.id.heightText);
        bmiText =  findViewById(R.id.bmiText);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewProfile.this,Home.class));
                finish();
            }
        });


        SharedPreferences sp = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);

        String nameStr,ageStr,weightStr,heightStr,bmiStr;

        nameStr = sp.getString("name","");
        ageStr = sp.getString("age","");
        weightStr = sp.getString("weight","");
        heightStr = sp.getString("height","");
        bmiStr = sp.getString("BMI","");

        nameText.setText(nameStr);
        ageText.setText(ageStr);
        weightText.setText(weightStr);
        heightText.setText(heightStr);
        bmiText.setText(bmiStr);


    }
}
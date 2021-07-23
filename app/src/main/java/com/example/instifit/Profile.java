package com.example.instifit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instifit.Exercises.BicycleCrunches;
import com.example.instifit.Exercises.BirdDog;
import com.example.instifit.Exercises.Burpees;
import com.example.instifit.Exercises.DonkeyKick;
import com.example.instifit.Exercises.JumpSquats;
import com.example.instifit.Exercises.Plank;
import com.example.instifit.Exercises.PushUps;
import com.example.instifit.Exercises.SideHip;
import com.example.instifit.Exercises.SitUps;
import com.example.instifit.Exercises.StationaryLunge;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Profile extends AppCompatActivity {

    private TextView title,text,bmi;
    private EditText name,age,height,weight;
    private Button save;
    private ImageView imageView,back;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    FirebaseStorage storage;
    Uri selectedImage;
    ProgressDialog dialog;

    SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        title=findViewById(R.id.title);
        text=findViewById(R.id.text);
        bmi=findViewById(R.id.bmi);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        save=findViewById(R.id.save);
        imageView=findViewById(R.id.imageView);
        back=findViewById(R.id.back);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Updating Profile.....");
        dialog.setCancelable(false);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();

        sp = getSharedPreferences("User", Context.MODE_PRIVATE);





        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,MainActivity.class));
                finish();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namestr = name.getText().toString();
                String agestr = age.getText().toString();
                String weightstr = weight.getText().toString();
                String heightstr = height.getText().toString();


                if(namestr.isEmpty()){
                    name.setError("Please enter your name");
                    return;
                }
                if(agestr.isEmpty()){
                    age.setError("Please enter your age");
                    return;
                }
                if(weightstr.isEmpty()){
                    weight.setError("Please enter your weight");
                    return;
                }
                if(heightstr.isEmpty()){
                    height.setError("Please enter your height");
                    return;
                }


                dialog.show();

                float w,h;
                float bmivalue;
                w = Integer.parseInt(weightstr);
                h = Integer.parseInt(heightstr);
                bmivalue = (w*100*100)/(h*h);
                String bmistr = "Your BMI is "+String.valueOf(bmivalue);


                bmi.setVisibility(View.VISIBLE);
                if(bmivalue<18.5){
                    String bmistr1 = bmistr+" .It seems that you are Underweight";
                    bmi.setText(bmistr1);
                    Toast.makeText(Profile.this,bmistr1,Toast.LENGTH_SHORT).show();
                }else if(bmivalue<25){
                    String bmistr2 = bmistr+" .Amazing,You are now in a healthy range";
                    bmi.setText(bmistr2);
                    Toast.makeText(Profile.this,bmistr2,Toast.LENGTH_SHORT).show();
                }else {
                    String bmistr3 = bmistr+" .Oops,It seems that you are overweight";
                    bmi.setText(bmistr3);
                    Toast.makeText(Profile.this,bmistr3,Toast.LENGTH_SHORT).show();
                }


                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",namestr);
                editor.putString("age",agestr);
                editor.putString("weight",weightstr);
                editor.putString("height",heightstr);
                editor.putString("BMI",String.valueOf(bmivalue));
                editor.putFloat("bmi",bmivalue);
                editor.commit();



                /* String str1 = String.valueOf(bmiint);
                Intent intent1 = new Intent(Profile.this,com.example.instifit.Exercises.Burpees.class);
                intent1.putExtra("burpees",str1);
                //startActivity(intent1);

                String str2=String.valueOf(bmivalue);
                Intent intent2 = new Intent(Profile.this, com.example.instifit.Exercises.PushUps.class);
                intent2.putExtra("pushups",str2);
                //startActivity(intent2);

                String str3=String.valueOf(bmivalue);
                Intent intent3 = new Intent(Profile.this, com.example.instifit.Exercises.SitUps.class);
                intent3.putExtra("situps",str3);
                //startActivity(intent3);

                String str4=String.valueOf(bmivalue);
                Intent intent4= new Intent(Profile.this, com.example.instifit.Exercises.Plank.class);
                intent4.putExtra("plank",str4);
                //startActivity(intent4);

                String str5=String.valueOf(bmivalue);
                Intent intent5= new Intent(Profile.this, com.example.instifit.Exercises.BicycleCrunches.class);
                intent5.putExtra("bicyclecrunches",str5);
                //startActivity(intent5);

                String str6=String.valueOf(bmivalue);
                Intent intent6 = new Intent(Profile.this, com.example.instifit.Exercises.DonkeyKick.class);
                intent6.putExtra("donkeykick",str6);
                //startActivity(intent6);

                String str7=String.valueOf(bmivalue);
                Intent intent7 = new Intent(Profile.this, com.example.instifit.Exercises.JumpSquats.class);
                intent7.putExtra("jumpsquats",str7);
                //startActivity(intent7);

                String str8=String.valueOf(bmivalue);
                Intent intent8 = new Intent(Profile.this, com.example.instifit.Exercises.BirdDog.class);
                intent8.putExtra("birddog",str8);
                //startActivity(intent8);

                String str9=String.valueOf(bmivalue);
                Intent intent9 = new Intent(Profile.this, com.example.instifit.Exercises.StationaryLunge.class);
                intent9.putExtra("stationarylunge",str9);
                //startActivity(intent9);

                String str=String.valueOf(bmivalue);
                Intent intent = new Intent(Profile.this, com.example.instifit.Exercises.SideHip.class);
                intent.putExtra("sidehip",str);
                //startActivity(intent); */





                if(selectedImage != null){
                    StorageReference reference = storage.getReference().child("Profiles").child(mAuth.getUid());
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull  Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageUrl = uri.toString();

                                        String uid = mAuth.getUid();


                                        User user = new User(namestr, agestr, weightstr, heightstr, uid, imageUrl);

                                        database.getReference()
                                                .child("users")
                                                .child(uid)
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        dialog.dismiss();
                                                        Intent intent = new Intent(Profile.this, Home.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                    }


                                });

                            }
                        }
                    });


                } else {

                    String uid = mAuth.getUid();




                    User user = new User(namestr,agestr,weightstr,heightstr,uid,"No Image");

                    database.getReference()
                            .child("users")
                            .child(uid)
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Profile.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                }




            }
        });



    }
}
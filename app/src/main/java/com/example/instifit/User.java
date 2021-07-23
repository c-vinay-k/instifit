package com.example.instifit;

public class User {

    public User(){

    }

    private  String name,age,weight,height,uid,profileImage;


    public User(String name, String age, String weight, String height, String uid,String profileImage) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.uid = uid;
        this.profileImage=profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}




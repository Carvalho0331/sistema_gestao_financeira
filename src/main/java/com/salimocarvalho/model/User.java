package com.salimocarvalho.model;

import com.salimocarvalho.enums.Profile;

public class User {
    private int id;
    private  String Name;
    private  String email;
    private  String password ;
    private Profile profile;

    public User(int id, String name, String email, String password, Profile profile) {
        this.id = id;
        Name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    @Override
    public String toString() {
        return "model.User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", email='" + email + '\'' +
                ", profile=" + profile +
                '}';
    }
}

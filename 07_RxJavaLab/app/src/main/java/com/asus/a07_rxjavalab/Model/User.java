package com.asus.a07_rxjavalab.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Allen on 2018/2/11.
 */

public class User {
    private String id;
    private String name;
    @SerializedName("username")
    private String userName;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

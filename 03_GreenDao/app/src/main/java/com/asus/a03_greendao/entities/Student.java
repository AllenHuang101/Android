package com.asus.a03_greendao.entities;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Allen on 2018/1/9.
 */

@Entity
public class Student {

    @Id(autoincrement = true)
    private Long id;

    @NonNull
    private String name;

    @Generated(hash = 1124112952)
    public Student(Long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
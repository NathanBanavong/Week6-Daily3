package com.example.consultants.week6_daily3.model;

import android.arch.persistence.room.Entity;

@Entity
public class Place {
    //parameter for the 'places' seen by user
    String name;
    String time;
    String enter;
    String exit;

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }
}

package com.example.consultants.week6_daily3.model.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.consultants.week6_daily3.model.Place;
import com.example.consultants.week6_daily3.model.PlaceDAO;
import com.example.consultants.week6_daily3.model.data.PlaceDatabase;

public class LocalDataSource {

    Context context;
    PlaceDatabase placeDatabase;
    PlaceDAO placeDAO;

    public LocalDataSource(Context context) {
        this.context = context;
        placeDatabase = Room.databaseBuilder(context, PlaceDatabase.class, "Place-Database").build();
        placeDAO = placeDatabase.placeDAO();
    }
}

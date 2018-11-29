package com.example.consultants.week6_daily3.model.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.consultants.week6_daily3.model.Place;
import com.example.consultants.week6_daily3.model.PlaceDAO;

@Database(entities = Place.class, version = 1)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDAO placeDAO();
}

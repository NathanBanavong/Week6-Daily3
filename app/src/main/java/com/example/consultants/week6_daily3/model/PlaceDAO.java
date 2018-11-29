package com.example.consultants.week6_daily3.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PlaceDAO {
    @Insert
    void savePlace(Place places);

    @Insert
    void saveAllPlaces(List<Place> placesList);

    @Query("SELECT * FROM Place")
    Flowable<List<Place>> getAllPlaces();
}

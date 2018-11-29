package com.example.consultants.week6_daily3;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.consultants.week6_daily3.model.Place;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName()+"_TAG";

    Constants constants;
    private Place places;
    private GeofencingClient geofencingClient;
    private List<Geofence> geofenceList = new ArrayList<>();
    private PendingIntent geoFencePendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geofencingClient = LocationServices.getGeofencingClient(this);
        AddGeoFencingClient(geofencingClient);
        createGeoFence();

//        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
//                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        // Geofences added
//                        // ...
//                    }
//                })
//                .addOnFailureListener(this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // Failed to add geofences
//                        // ...
//                    }
//                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        RemoveGeoFencingClient(geofencingClient);
    }

    //created a list of Geofence that hold the places
    public List<Geofence> createGeoFence(){

        String[] placeName = {"Treasure Planet","Assim Uranus","Planet of Apes", "The Pink Pony","Manroop's World"};
        Double[] placeLatitude = {38.02539, 50.32146, 53.95476, 40.44568, 39.00256};
        Double[] placeLongitude = {44.03526, 42.32151, 38.26485, 39.25476, 41.78514};

        for (int x = 0; x < placeName.length; x++) {
            geofenceList.add(new Geofence.Builder()
                .setRequestId(placeName[x])
                .setCircularRegion(placeLatitude[x],placeLongitude[x],constants.GEOFENCE_RADIUS_IN_METERS)
                .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());
        }
        return geofenceList;
    }

    @SuppressLint("MissingPermission")
    public void AddGeoFencingClient(GeofencingClient geofencingClient) {
        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Geofences added
                        // ...
                        Log.d(TAG, "onSuccess: ");
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add geofences
                        // ...
                    }
                });
    }

    @SuppressLint("MissingPermission")
    public void RemoveGeoFencingClient(GeofencingClient geofencingClient) {
        geofencingClient.removeGeofences(getGeofencePendingIntent())
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Geofences added
                        // ...
                        Log.d(TAG, "onSuccess: ");
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add geofences
                        // ...
                    }
                });
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(createGeoFence());
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (geoFencePendingIntent != null) {
            return geoFencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        geoFencePendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return geoFencePendingIntent;
    }
}


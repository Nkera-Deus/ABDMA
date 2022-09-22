package com.example.abdma;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.abdma.databinding.ActivityRouteCheckerBinding;

import java.util.ArrayList;

public class RouteChecker extends FragmentActivity implements OnMapReadyCallback {

    private  GoogleMap mMap;
    private ActivityRouteCheckerBinding binding;
    //places to mark

    LatLng sydney = new LatLng(-34,151);
    LatLng TamWorth = new LatLng(-31.083332,150.916672);
    LatLng NewCastle = new LatLng(-32.916668,151.750000);
    LatLng Brisbane = new LatLng(-27.470125,153.021072);
     //creating arraylist for adding all our locations

    private ArrayList<LatLng> locationArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRouteCheckerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //initiate our arrayList
        locationArrayList = new ArrayList<>();

        //then we add our locations to the arraylist

        locationArrayList.add(sydney);
        locationArrayList.add(TamWorth);
        locationArrayList.add(NewCastle);
        locationArrayList.add(Brisbane);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < locationArrayList.size(); i++){
            //add a marker to each location in the arraylist
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
            // below function zooms our camera on the map
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

            //move the camera to a specific location
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));

        }


    }
}
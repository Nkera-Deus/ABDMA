package com.example.abdma;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.abdma.databinding.ActivityLocateServiceBinding;

import java.util.List;

public class LocateService extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Address> listGeoCoder;
    private ActivityLocateServiceBinding binding;
    private static final int LOCATION_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocateServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(isLocationPermissionGranted()){
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            try{
                listGeoCoder = new Geocoder(this).getFromLocationName("Mbarara-Bushenyi, 4 Miles Trading Center",1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            double longitude = listGeoCoder.get(0).getLongitude();
            double latitude = listGeoCoder.get(0).getLatitude();

            Log.i("GOOGLE_MAP_TAG","Address has longitude " + ":::" + String.valueOf(longitude) + "latitude" + String.valueOf(latitude));

        }
        else{
            requestLocationPermission();
        }


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

        // Add a marker in Sydney and move the camera
        LatLng mbarara = new LatLng(-0.6072, 30.6545);
        mMap.addMarker(new MarkerOptions().position(mbarara).title("Marker in Mbarara"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mbarara));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }


    }
    private boolean isLocationPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            return false;
        }
    }
    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
    }


}
package com.example.abdma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.location.Address;
import android.location.Geocoder;

import com.example.abdma.databinding.ActivityLocateServiceBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;


public class GetServiceLocation extends FragmentActivity implements OnMapReadyCallback {


    SupportMapFragment mapFragment;
    SearchView searchView;
    GoogleMap mMap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_service_location);


        searchView =findViewById(R.id.search_view);
        mapFragment =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.google_map);





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //get the location name from the searchview
                String location = searchView.getQuery().toString();

                //create a list of addresses where we will store
                //the list of all addresses
                List<Address> addressList = null;
                //here we check if the entered location is null or not

                if(location != null || !location.equals("")){
                    //here we create and initialise a geocoder
                    Geocoder geocoder = new Geocoder(GetServiceLocation.this);
                    try{
                        //here we get the location from the location name and
                        //add that location to location address
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    //here we get the location from our list from first position
                    Address address = addressList.get(0);

                    //create a variable for our location where we will add our latitude and longitude

                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());

                    //this code below adds marker to the position
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                    //here we animate the camera to the desired position
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady( GoogleMap googleMap) {
        mMap = googleMap;
        
    }
}
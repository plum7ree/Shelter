package cs2340.gatech.edu.lab4.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import cs2340.gatech.edu.lab4.R;

import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.ARG_AGE;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.ARG_GENDER;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.currentAgeSearchOption;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.currentGenderSearchOption;

/**
 * Created by Owner on 3/25/2018.
 */

public class ShelterMapPopup extends SupportMapFragment implements OnMapReadyCallback{

    GoogleMap nGoogleMap;
    MapView mapView;
    View mView;

    public ShelterMapPopup() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_map, container, false);
        return mView;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        nGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }





}

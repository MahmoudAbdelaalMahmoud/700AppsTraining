package com.mahmoud.a700appstraining.location;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mahmoud.a700appstraining.R;


/*TWO WAYS
 * 1- Google Location Services APIs
 * 2- framework location APIs
 * */

/*battery drain
 * Accuracy
 * Frequency
 * Latency
 * */


public class LocationFragmentIntentService extends Fragment implements LocationHelper.ILocationCallback, OnMapReadyCallback {
    private LocationBroadCast locationReceiver;
    private GoogleMap map;
    private Marker myLocationMarker;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationReceiver = new LocationBroadCast();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(locationReceiver, new IntentFilter(LocationBroadCast.ACTION_BROADCAST));
    }

    @Override
    public void onPause() {
        getContext().unregisterReceiver(locationReceiver);
        super.onPause();
    }


    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LocationHelper.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getContext().startService(new Intent(getContext(), LocationIntentService.class));
                        break;

                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("LocationFragmentTest", String.format("%s , %s", location.getLatitude(), location.getLongitude()));
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
        if (myLocationMarker == null) {
            MarkerOptions options = new MarkerOptions().position(currentPosition)
                    .title("myLocation");
            myLocationMarker = map.addMarker(options);
        }else{
            myLocationMarker.setPosition(currentPosition);
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition,17));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        getContext().startService(new Intent(getContext(), LocationIntentService.class));

    }




    public class LocationBroadCast extends BroadcastReceiver {
        private static final String PACKAGE_NAME =
                "com.mahmoud.a700appstraining.location";
        static final String ACTION_BROADCAST = PACKAGE_NAME+".broadcast";
        static final String EXTRA_LOCATION   = PACKAGE_NAME+".location";
        private static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra(EXTRA_LOCATION);
            if (location != null) {
                onLocationChanged(location);
            }
        }
    }


}

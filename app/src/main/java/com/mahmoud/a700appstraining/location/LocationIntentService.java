package com.mahmoud.a700appstraining.location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;


public class LocationIntentService extends IntentService implements LifecycleOwner, LocationHelper.ILocationCallback {

    private LifecycleRegistry lifecycleRegistry;
    private LocationHelper locationHelper;

    public LocationIntentService() {
        super("Location Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    public void onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        super.onDestroy();
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        this.locationHelper = new LocationHelper(this, this);
    }

    public LocationHelper.ILocationCallback getLocationListenrr() {
        return this;
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onLocationChanged(Location location) {
        Intent intent = new Intent(LocationFragmenIntentService.LocationBroadCast.ACTION_BROADCAST);
        intent.putExtra(LocationFragmenIntentService.LocationBroadCast.EXTRA_LOCATION, location);
        sendBroadcast(intent);
//        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }


}
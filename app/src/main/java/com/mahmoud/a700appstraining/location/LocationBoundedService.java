package com.mahmoud.a700appstraining.location;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;



public class LocationBoundedService extends Service implements LifecycleOwner, LocationHelper.ILocationCallback {

    private LifecycleRegistry lifecycleRegistry;

    private final IBinder mBinder = new LocalBinder();

    private LocationHelper locationHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
        this.locationHelper = new LocationHelper(this, this);
    }

    @Override
    public void onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
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
        Intent intent = new Intent(LocationFragmentIntentService.LocationBroadCast.ACTION_BROADCAST);
        intent.putExtra(LocationFragmentIntentService.LocationBroadCast.EXTRA_LOCATION, location);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }


    public class LocalBinder extends Binder {
        LocationBoundedService getService() {
            return LocationBoundedService.this;
        }
    }


}

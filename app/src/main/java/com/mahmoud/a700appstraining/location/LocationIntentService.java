package com.mahmoud.a700appstraining.location;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.mahmoud.a700appstraining.MainActivity;
import com.mahmoud.a700appstraining.R;

import java.util.Locale;


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
        Intent intent = new Intent(LocationFragmenBoundService.LocationBroadCast.ACTION_BROADCAST);
        intent.putExtra(LocationFragmenBoundService.LocationBroadCast.EXTRA_LOCATION, location);
        sendBroadcast(intent);
        sendNotification(location);
//        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    public void sendNotification(Location location){
        Intent fullScreenIntent = new Intent(this, MainActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My Location")
                .setContentText(String.format(Locale.ENGLISH,"%.3f , %.3f",location.getLatitude(),location.getLongitude()))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setFullScreenIntent(fullScreenPendingIntent, true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }
    public static final int NOTIFICATION_ID = 888;
    public static final String CHANNEL_ID = "apps.location.700";
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "location";
            String description = "get user location";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }


}
package com.work.nalandya.pawoon_test.presenter.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class MapsUtil {

    public static LatLng getCurLocation(Activity act) {
        LocationManager manager = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Location loc = getLastKnownLocation(act);
            if (loc != null) {
                return new LatLng(loc.getLatitude(), loc.getLongitude());
            }
        } else {
            buildAlertMessageNoGps(act);
        }
        return new LatLng(0,0);
    }

    public static Location getLastKnownLocation(Activity act) {
        LocationManager mLocationManager = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private static void buildAlertMessageNoGps(final Activity act) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        act.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static void buildAlertMessageNoPermission(final Activity act) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setMessage("You must enabled premission location manually?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean askForPermission(Activity activity, String permission, Integer requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    //This is called if user has denied the permission before
                    //In this case I am just asking the permission again
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                    return false;
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                    return false;
                }
            } else {
                //Toast.makeText(activity, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return true;
    }

}

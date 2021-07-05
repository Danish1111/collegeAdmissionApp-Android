package com.example.abc.itmcollegealigarh.activity.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.itmcollegealigarh.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private GoogleMap googleMap;
    // latitude and longitude
    double latitude = 27.9089;
    double longitude = 77.9566;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
    };
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.primary_dark, R.color.primary, R.color.primary_light, R.color.accent, R.color.primary_dark_material_light};
    private TextView tvGeoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        checkGPS();
        tvGeoMap = findViewById(R.id.tv_open_map);
        tvGeoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLastLocation != null) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=" + mLastLocation.getLatitude() + "+," + mLastLocation.getLongitude() + "&daddr=" + latitude + "," + longitude));
                    startActivity(intent);
                }else {
                    Toast.makeText(MapActivity.this, "Trying to find your location", Toast.LENGTH_SHORT).show();
                }

            }
        });
        polylines = new ArrayList<>();
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        } else {
            initialization();
        }

    }

    void checkGPS(){
        LocationManager lm = (LocationManager)MapActivity.this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(MapActivity.this);
            dialog.setMessage("Please on your location");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    MapActivity.this.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    onBackPressed();

                }
            });
            dialog.show();
        }
    }

    private void initialization() {
        try {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            // Loading map
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @SuppressLint("MissingPermission")
                @Override
                public void onMapReady(GoogleMap g) {
                    googleMap = g;

                    mLocationRequest = new LocationRequest();
                    mLocationRequest.setInterval(30000); // two minute interval
                    mLocationRequest.setFastestInterval(30000);
                    mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setRotateGesturesEnabled(true);
                    googleMap.getUiSettings().setZoomControlsEnabled(true); // true to enable
                    googleMap.getUiSettings().setCompassEnabled(true);
                    Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("I.T.M Aligarh"));
                    marker.showInfoWindow();
//                    MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("I.T.M Aligarh ");
//                    googleMap.addMarker(marker);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(
                            new LatLng(latitude, longitude)).zoom(7).build();

                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                }
            });
//                initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initialization();
            } else {
                Log.i("LOG", "REFUSED");
                Toast.makeText(this, "REFUSED,", Toast.LENGTH_LONG).show();
            }
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);
                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title("Your Position"));
                marker.showInfoWindow();
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title("Your Position");
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//                mCurrLocationMarker = googleMap.addMarker(markerOptions);
//                Toast.makeText(MapActivity.this, "" + latLng.latitude, Toast.LENGTH_SHORT).show();

                LatLng end = new LatLng(latitude, longitude);

//                Routing routing = new Routing.Builder()
//                        .key("AIzaSyC8cPpyCEDVOsovdk9btpmPU-xSCdxRwGE")
//                        .travelMode(Routing.TravelMode.DRIVING)
//                        .withListener(new RoutingListener() {
//                            @Override
//                            public void onRoutingFailure(RouteException e) {
//                                Toast.makeText(MapActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
//                            }
//
//                            @Override
//                            public void onRoutingStart() {
//
//                            }
//
//                            @Override
//                            public void onRoutingSuccess(ArrayList<Route> route, int i) {
//                                CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
//                                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//
//                                googleMap.moveCamera(center);
//
//
//                                if(polylines.size()>0) {
//                                    for (Polyline poly : polylines) {
//                                        poly.remove();
//                                    }
//                                }
//
//                                polylines = new ArrayList<>();
//                                //add route(s) to the map.
//                                for (int r = 0; r <route.size(); r++) {
//
//                                    //In case of more than 5 alternative routes
//                                    int colorIndex = r % COLORS.length;
//
//                                    PolylineOptions polyOptions = new PolylineOptions();
//                                    polyOptions.color(getResources().getColor(COLORS[colorIndex]));
//                                    polyOptions.width(10 + r * 3);
//                                    polyOptions.addAll(route.get(r).getPoints());
//                                    Polyline polyline = googleMap.addPolyline(polyOptions);
//                                    polylines.add(polyline);
//                                }
//                            }
//
//                            @Override
//                            public void onRoutingCancelled() {
//                                Toast.makeText(MapActivity.this, "Fail", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .waypoints(latLng, end)
//                        .build();
//                routing.execute();

                //move map camera

//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
            }
        }
    };


    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

//    private void initilizeMap() {
//        if (googleMap == null) {
//            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
//                    R.id.map)).getMap();
//            googleMap.setMyLocationEnabled(true); // false to disable
//            googleMap.getUiSettings().setCompassEnabled(true);
//            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//            googleMap.getUiSettings().setRotateGesturesEnabled(true);
//            googleMap.getUiSettings().setZoomControlsEnabled(true); // true to enable
//            googleMap.getUiSettings().setCompassEnabled(true);
//            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("I.T.M Aligarh ");
//            googleMap.addMarker(marker);
//            CameraPosition cameraPosition = new CameraPosition.Builder().target(
//                    new LatLng(latitude, longitude)).zoom(10).build();
//
//            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//
//            // check if map is created successfully or not
//            if (googleMap == null) {
//                Toast.makeText(getApplicationContext(),
//                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        initilizeMap();
    }

}

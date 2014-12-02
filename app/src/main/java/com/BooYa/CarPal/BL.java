package com.BooYa.CarPal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.location.Geocoder;
import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 30/11/2014.
 */
public class BL {

    public static Context CONTEXT;


    public static LatLng GetDefaultLocation()
    {
        return  new LatLng(32.085300,34.781768);
    }

    public static LatLng GetCurrentLocation(GoogleMap mMap)
    {
        try
        {

            mMap.setMyLocationEnabled(true);
            Location myloc =  mMap.getMyLocation();
            LatLng location = new LatLng(myloc.getLatitude(),myloc.getLongitude());
            return location;
        }
        catch(Exception ex)
        {}
        return  GetDefaultLocation();
    }

    public static LatLng GetLatLngFromAddress(Context context, String Address)
    {
        LatLng location = GetDefaultLocation();
        try
        {
            android.location.Address address;
            Geocoder gc = new Geocoder(context);
            address = gc.getFromLocationName(Address,1).get(0);
            location = new LatLng(address.getLatitude(), address.getLongitude());
        }
        catch (Exception ex){}
        return  (location);
    }

    public static LatLng GetMarkerForMeetingLocation(Context context, GoogleMap mMap, ArrayList<UserInfo> groupMembers)
    {
        LatLng location = GetCenter(context, groupMembers);
        return location;
    }

    public static LatLng GetMeetingLocation(Context context,GoogleMap mMap, ArrayList<UserInfo> groupMembers)
    {
        LatLng location = GetCenter(context, groupMembers);



        return location;
    }

    public static LatLng GetMeetingLocationByAdresses(Context context, ArrayList<String> addresses)
    {
        LatLng location = GetDefaultLocation();



        return location;
    }

    public static LatLng GetCenterRecursive(ArrayList<LatLng> locations, LatLng currentCenter, double radius)
    {
        if(locations.isEmpty())
        {
            return currentCenter;
        }
        LatLng location = locations.get(0);

        locations.remove(0);

        return  GetCenterRecursive(locations, currentCenter, radius);
    }

    public static LatLng GetCenter(Context context, ArrayList<UserInfo> locations)
    {
       LatLng location = new LatLng(0,0);
        try {
            if (!locations.isEmpty()) {
                double x = 0;
                double y = 0;
                for (UserInfo member : locations) {
                    LatLng memberLocation = GetLatLngFromAddress(context, member.get_addressHome().toString());
                    x += memberLocation.latitude;
                    y += memberLocation.longitude;
                }
                x /= locations.size();
                y /= locations.size();
                location = new LatLng(x, y);
            }
        }
        catch (Exception ex)
        {

        }
        return location;
    }

}

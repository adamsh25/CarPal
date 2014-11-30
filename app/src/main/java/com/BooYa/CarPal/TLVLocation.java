package com.BooYa.CarPal;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by adam on 30/11/2014.
 */
public class TLVLocation {

    private String _locationName;
    private String _description;
    private LatLng _location;
    private String _address;

    public String get_locationName() {
        return _locationName;
    }

    public TLVLocation set_locationName(String _locationName) {
        this._locationName = _locationName;
        return this;
    }

    public String get_description() {
        return _description;
    }

    public TLVLocation set_description(String _description) {
        this._description = _description;
        return this;
    }

    public LatLng get_location() {
        return _location;
    }

    public TLVLocation set_location(LatLng _location) {
        this._location = _location;
        return this;
    }

    public String get_address() {
        return _address;
    }

    public TLVLocation set_address(String _address) {
        this._address = _address;
        return this;
    }
}

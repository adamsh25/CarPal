package com.BooYa.CarPal;

/**
 * Created by adam on 23/11/2014.
 */
public class Address {

    private String _countryAddress;
    private String _streetNameAddress;
    private String _cityAddress;
    private String _streetNumberAddress;

    public String get_countryAddress() {
        return _countryAddress;
    }

    public Address set_countryAddress(String _countryAddress) {
        this._countryAddress = _countryAddress;return this;
    }

    public String get_streetNameAddress() {
        return _streetNameAddress;
    }

    public Address set_streetNameAddress(String _streetNameAddress) {
        this._streetNameAddress = _streetNameAddress;return this;
    }

    public String get_streetNumberAddress() {
        return _streetNumberAddress;
    }

    public Address set_streetNumberAddress(String _streetNumberAddress) {
        this._streetNumberAddress = _streetNumberAddress;
        return this;
    }

    public String get_cityAddress() {
        return _cityAddress;
    }

    public Address set_cityAddress(String _cityAddress) {
        this._cityAddress = _cityAddress;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s",
                get_countryAddress(),
                get_cityAddress(),
                get_streetNameAddress(),
                get_streetNumberAddress());
    }
}

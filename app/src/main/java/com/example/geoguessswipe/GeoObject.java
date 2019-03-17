package com.example.geoguessswipe;

import com.example.geoguessswipe.R;

/**
 * Created by marmm on 02/11/2017.
 */


public class GeoObject {

    public static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
            "denmark",
            "canada",
            "bangladesh",
            "kazachstan",
            "colombia",
            "poland",
            "malta",
            "thailand"
    };

    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };

    public static final Boolean[] IS_IN_EUROPA = {
            true,
            false,
            false,
            true,
            false,
            true,
            true,
            false
    };

    private String mGeoName;
    private int mGeoImageName;
    private Boolean mGeoBoolean;


    public GeoObject(String mGeoName, int mGeoImageName, Boolean mGeoBoolean) {

        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
        this.mGeoBoolean = mGeoBoolean;
    }

    public String getmGeoName() {
        return mGeoName;
    }

    public void setmGeoName(String mGeoName) {
        this.mGeoName = mGeoName;
    }

    public int getmGeoImageName() {
        return mGeoImageName;
    }

    public void setmGeoImageName(int mGeoImageName) {
        this.mGeoImageName = mGeoImageName;
    }

    public boolean getmGeoBoolean() { return mGeoBoolean; }

    public void setmGeoBoolean(Boolean mGeoBoolean) {
        this.mGeoBoolean = mGeoBoolean;
    }

    @Override
    public String toString() {
        return "Europa: " + this.getmGeoBoolean();
    }



}
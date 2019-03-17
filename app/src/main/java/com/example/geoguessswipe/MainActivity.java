package com.example.geoguessswipe;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    List<GeoObject> mGeoObjects;
    private GestureDetector mGestureDetector;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGeoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i],
                    GeoObject.IS_IN_EUROPA[i]
            ));
        }

        final RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( this);

        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        mGeoRecyclerView.setHasFixedSize(true);
        final GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);
        mGeoRecyclerView.addOnItemTouchListener(this);

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {

                View child = mGeoRecyclerView.findChildViewUnder(e1.getX(), e2.getY());
                int mAdapterPosition = mGeoRecyclerView.getChildAdapterPosition(child);

                try {
                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                        return false;
                    // right to left swipe
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                    //ligt in Europa?
                    { if (mGeoObjects.get(mAdapterPosition).getmGeoBoolean()){
                        //Laat antwoord zien
                        showToast("Correct! " + mGeoObjects.get(mAdapterPosition).getmGeoName().toUpperCase() + " ligt in Europa");
                    } else
                        showToast("Fout! " + mGeoObjects.get(mAdapterPosition).getmGeoName().toUpperCase() + " ligt NIET in Europa");

                    } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //left to right flip
                        { if (!mGeoObjects.get(mAdapterPosition).getmGeoBoolean()){
                            //Laat antwoord zien
                            showToast("Correct! " + mGeoObjects.get(mAdapterPosition).getmGeoName().toUpperCase() + " ligt NIET in Europa");
                        } else
                            showToast("Fout! " + mGeoObjects.get(mAdapterPosition).getmGeoName().toUpperCase() + " ligt WEL in Europa");
                        }
                    }} catch (Exception e) {
                }
                return false;
            }
        });
    }

    private void showToast(String answer) {
        Toast.makeText(this, answer, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        int mAdapterPosition = rv.getChildAdapterPosition(child);
        if (child != null && mGestureDetector.onTouchEvent(e)) {
            Toast.makeText(this, mGeoObjects.get(mAdapterPosition).getmGeoName(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
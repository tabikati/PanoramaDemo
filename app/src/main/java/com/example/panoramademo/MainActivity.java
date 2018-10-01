package com.example.panoramademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PanoramaPic> panoramaPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panoramaPics = new ArrayList<>();
        panoramaPics.add(new PanoramaPic((VrPanoramaView) findViewById(R.id.pano_0), "andes.jpg"));
        panoramaPics.add(new PanoramaPic((VrPanoramaView) findViewById(R.id.pano_1), "index.jpg"));
        panoramaPics.add(new PanoramaPic((VrPanoramaView) findViewById(R.id.pano_2), "test.jpg"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        for (PanoramaPic panoramaPic : panoramaPics) {
            panoramaPic.onResume();
        }
    }

    @Override
    protected void onPause() {
        for (PanoramaPic panoramaPic : panoramaPics) {
            panoramaPic.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        for (PanoramaPic panoramaPic : panoramaPics) {
            panoramaPic.onDestroy();
        }
        super.onDestroy();
    }

}

package com.example.panoramademo;

import android.graphics.Bitmap;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

class PanoramaPic {

    private VrPanoramaView panoramaView;
    private ImageLoaderTask imageLoader;

    PanoramaPic(final VrPanoramaView panoramaView, String fileName) {
        this.panoramaView = panoramaView;
        this.imageLoader = new ImageLoaderTask(panoramaView.getContext(), fileName, new ImageLoaderTask.ResultListener() {
            @Override
            public void onCompleted(Bitmap bitmap) {
                panoramaView.loadImageFromBitmap(bitmap, null);
            }
        });
        imageLoader.execute();
    }

    void onResume() {
        if (panoramaView != null) {
            panoramaView.resumeRendering();
        }
    }

    void onPause() {
        if (panoramaView != null) {
            panoramaView.pauseRendering();
        }
    }

    void onDestroy() {
        if (panoramaView != null) {
            panoramaView.shutdown();
        }
        if (imageLoader != null) {
            imageLoader.cancel(true);
        }
    }

}

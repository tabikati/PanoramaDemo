package com.example.panoramademo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class ImageLoaderTask extends AsyncTask<Void, Void, Bitmap> {

    private AssetManager assetManager;
    private String fileName;
    private ResultListener listener;


    ImageLoaderTask(Context context, String fileName, ResultListener listener) {
        assetManager = context.getAssets();
        this.fileName = fileName;
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        VrPanoramaView.Options panoOptions;
        InputStream istr;
        try {
            istr = assetManager.open(fileName);
            panoOptions = new VrPanoramaView.Options();
            panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        try {
            istr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (listener != null) {
            listener.onCompleted(bitmap);
        }
    }

    public interface ResultListener {
        void onCompleted(Bitmap bitmap);
    }
}

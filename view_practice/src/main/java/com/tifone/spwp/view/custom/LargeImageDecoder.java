package com.tifone.spwp.view.custom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.util.Size;

import java.io.IOException;
import java.io.InputStream;

/**
 * use to decode the large image.
 * decode image according to region.
 * use LURCache to save image cache
 */
public class LargeImageDecoder {
    private BitmapRegionDecoder mDecoder;
    public LargeImageDecoder(InputStream is) {
        try {
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap decodeRegion(Rect region, BitmapFactory.Options option) {
        return mDecoder.decodeRegion(region, option);
    }

    public Size getSize() {
        return new Size(mDecoder.getWidth(), mDecoder.getHeight());
    }

}

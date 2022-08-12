package com.codewithamrit.myapplication.CompressImage;

import android.graphics.Bitmap;

public class ImageCompression {
    //private static long MAX_SIZE = 360000;
    //private static long THUMB_SIZE = 6553;
    public static Bitmap resizeImage(Bitmap bitmap, int MAX_SIZE){
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight= bitmap.getHeight();
        bitmapWidth= bitmap.getWidth();
        ratioSquare= (bitmapHeight*bitmapWidth)/MAX_SIZE;
        if(ratioSquare<=1){
            return bitmap;
        }
        double ratio= Math.sqrt(ratioSquare);
        int requiredHeight=(int)Math.round(bitmapHeight/ratio);
        int requiredWidth=(int)Math.round(bitmapWidth/ratio);
        return Bitmap.createScaledBitmap(bitmap,requiredWidth,requiredHeight,true);

    }
}

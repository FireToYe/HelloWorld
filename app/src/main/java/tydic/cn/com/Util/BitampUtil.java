package tydic.cn.com.Util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by yechenglong on 2017/1/12.
 */

public class BitampUtil {

    private static int getInsamPleSize(BitmapFactory.Options options,int wideSize,int heightSize){
        int inSampleSize = 1;
        final int width = options.outWidth;
        final int height = options.outHeight;
        if (width>wideSize||height>heightSize){
           inSampleSize =  Math.min( Math.round((float) height / (float) heightSize),Math.round((float)width/(float)wideSize));
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize = getInsamPleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
        }
    }

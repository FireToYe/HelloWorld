package tydic.cn.com.imageUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by yechenglong on 2017/1/13.
 * 图片压缩公共类
 */

public class ImageResizer {
    private final String  TAG = "ImageResizer";
    /**
    *根据resId压缩本地图片
     */
    private volatile static ImageResizer instance;
    private ImageResizer(){};

    /**
     * 公共类单例模式
     * @return
     */
    public static ImageResizer instance(){
        if (instance ==null){
            synchronized (ImageResizer.class){
                if (instance==null){
                    instance = new ImageResizer();
                }
            }
        }
        return instance;
    }
    public Bitmap decodeSampleBitmapFromResouse(Resources res,int resId,int reqWidth,int reqHeight){
        BitmapFactory.Options options =new BitmapFactory.Options();
        options.inJustDecodeBounds =true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize = calCulateInsampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
    /**
     *根据fileDescriptor压缩图片
     */
    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fd,int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd,null,options);
        options.inSampleSize = calCulateInsampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds =false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }
    /**
     * 获取压缩比例
     */
    private int calCulateInsampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int inSampleSize = 1;
        final int width = options.outWidth;
        final int height = options.outHeight;
        if (reqHeight==0||reqWidth==0){
            return inSampleSize;
        }
        if (width>reqWidth||height>reqHeight){
            final int halfWidth = width/2;
            final int halfHeight =height/2;
            while((halfWidth/inSampleSize)>reqWidth||(halfHeight/inSampleSize)>reqHeight){
                inSampleSize *=2;
            }
        }
        return inSampleSize;
    }
}

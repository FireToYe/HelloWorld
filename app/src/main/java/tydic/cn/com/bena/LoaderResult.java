package tydic.cn.com.bena;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by yechenglong on 2017/1/16.
 */

public class LoaderResult {
    private ImageView imageView;
    private String uri;
    private Bitmap bitmap;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
        this.imageView = imageView;
        this.uri = uri;
        this.bitmap = bitmap;
    }
}

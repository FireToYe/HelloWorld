package tydic.cn.com.imageUtil;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import tydic.cn.com.Util.MyUtil;
import tydic.cn.com.bena.LoaderResult;
import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2017/1/13.
 */

public class ImageLoader {
    private final String TAG = "ImageLoader";
    private static final long DISK_CACHE_SIZE = 1024*1024*50;
    private static final int DISK_CACHE_INDEX = 0;
    private static final int IO_BUFFER_SIZE = 8*1024;
    private static final int TAG_KEY_URI = R.id.imageloader_uri;
    private  static final int MESSAGE_POST_RESULT = 0x123;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT+1;
    private static final int MAXIMUM_POOL_SIZE = 2*CORE_POOL_SIZE+1;
    private static final Long KEEP_ALIVE = 10L;
    private boolean mIsDiskLruCacheCreated =false;
    private LruCache<String,Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private  final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"ImageView#"+mCount.getAndIncrement());
        }
    };
    private static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE, TimeUnit.DAYS.SECONDS,new LinkedBlockingQueue<Runnable>(),sThreadFactory);
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.getImageView();
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.getUri())){
                imageView.setImageBitmap(result.getBitmap());
            }else {
                Log.w(TAG,"图片已经改变");
            }
        }
    };
    private ImageLoader(Context context){
        mContext = context.getApplicationContext();
        int maxMemery = (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxMemery/8;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
        File diskCacheDir = getDiskCacheDir(mContext,"bitmap");
        if (!diskCacheDir.exists()){
            diskCacheDir.mkdir();
        }
        if (getUsableSpace(diskCacheDir)>DISK_CACHE_SIZE){
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir,1,1,DISK_CACHE_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static ImageLoader build(Context context){
        return new ImageLoader(context);
    }
    /**
     * 内存缓存的添加
     * @param key
     * @param bitmap
     */
    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        //不存在才添加
        if (getBitmapFromMemCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }
    }

    /**
     * 内存缓存的获取
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemCache(String key){
        return mMemoryCache.get(key);
    }
    public Bitmap loadBitmap(String uri,int reqWidth,int reqHeight){
        Bitmap bitmap =loadBitmapFromMemcache(uri);
        if (bitmap!=null){
            return bitmap;
        }
        try {
            bitmap = loadBitmapFromDiskCache(uri,reqWidth,reqHeight);
            if (bitmap!=null){
                return bitmap;
            }
            bitmap =loadBitmapFromHttp(uri,reqWidth,reqHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap==null&&!mIsDiskLruCacheCreated){
            bitmap=downloadBitmapFromUrl(uri);
        }
        return bitmap;
    }
    public void bindBitMap(final String uri, final ImageView imageView, final int reqWidth,final int reqHeight){
        imageView.setTag(TAG_KEY_URI,uri);
        Bitmap bitmap = loadBitmapFromMemcache(uri);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }
        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap= loadBitmap(uri,reqWidth,reqHeight);
                if (bitmap!=null){
                    LoaderResult result = new LoaderResult(imageView,uri,bitmap);
                    mHandler.obtainMessage(MESSAGE_POST_RESULT,result).sendToTarget();
                }
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }
    public void bindBitmap(String uri,final ImageView imageView){
        bindBitMap(uri,imageView,0,0);
    }
    private Bitmap loadBitmapFromMemcache(String url){
        final String key = hashKeyFromUrl(url);
        Bitmap bitmap = mMemoryCache.get(key);
        return bitmap;
    }
    private File getDiskCacheDir(Context context,String uniqueName){
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable){
            cachePath = context.getExternalCacheDir().getPath();
        }else{
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath+File.separator+uniqueName);
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private long getUsableSpace(File path){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.GINGERBREAD){
            return path.getUsableSpace();
        }else{
            final StatFs statFs = new StatFs(path.getPath());
            return statFs.getBlockSizeLong()*statFs.getAvailableBlocksLong();
        }
    }

    /**
     * 根据url写入数据文件缓存
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapFromHttp(String url,int reqWidth,int reqHeight) throws IOException {
        if (Looper.myLooper()==Looper.getMainLooper()){
            throw new RuntimeException("不能在ui线程中工作");
        }
        if (mDiskLruCache==null){
            return null;
        }
        String key = hashKeyFromUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor!=null){
            OutputStream os = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downLoadUrlToStream(url,os)){
                editor.commit();
            }else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapFromDiskCache(url,reqWidth,reqHeight);
    }

    /**
     * 根据url进行加密获取文件缓存数据
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapFromDiskCache(String url,int reqWidth,int reqHeight) throws IOException {
        if (Looper.myLooper()==Looper.getMainLooper()){
            Log.w(TAG,"不能在主线程加载");
        }
        if (mDiskLruCache==null){
            return null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFromUrl(url);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        if (snapshot!=null){
            FileInputStream in = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fileDescriptor=in.getFD();
            bitmap = ImageResizer.instance().decodeSampleBitmapFromFileDescriptor(fileDescriptor,reqWidth,reqHeight);
            if (bitmap!=null){
                addBitmapToMemoryCache(key,bitmap);
            }
        }
        return bitmap;
    }
    private Bitmap downloadBitmapFromUrl(String urlString){
         BufferedInputStream inputStream = null;
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }
            MyUtil.close(inputStream);
        }
        return bitmap;
    }
    /**
     * 根据地址下载图片写入流中
     * @param urlString
     * @param os
     * @return
     */
    private boolean downLoadUrlToStream(String urlString,OutputStream os){
        HttpURLConnection connection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url =new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(url.openStream(),IO_BUFFER_SIZE);
            out =new BufferedOutputStream(os,IO_BUFFER_SIZE);
            int b ;
            while ((b=in.read())!=-1){
                out.write(b);
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }
            MyUtil.close(in);
            MyUtil.close(out);
        }
        return false;
    }
    private String hashKeyFromUrl(String url){
        String hashKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            hashKey =byteToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            hashKey = String.valueOf(url.hashCode());
        }
        return hashKey;
    }
    private String byteToHexString(byte[] bytes){
        StringBuilder sbd = new StringBuilder();
        for(int i = 0;i<bytes.length;i++){
            String hex = Integer.toHexString(bytes[0]);
            if (hex.length()==1){
                sbd.append('0');
            }
            sbd.append(hex);
        }
        return sbd.toString();
    }
}

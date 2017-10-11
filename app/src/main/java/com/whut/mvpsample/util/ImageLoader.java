package com.whut.mvpsample.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2017/10/10
 *     desc   : 图片加载类
 *     version: 1.0
 * </pre>
 */
public class ImageLoader {
    // 图片缓存
    ImageCache mImageCache = new ImageCache();
    // 线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool
            (Runtime.getRuntime().availableProcessors());

    /**
     * 将图片显示在Imageview上
     *
     * @param url
     * @param imageView
     */
    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        // 1，从缓存中取图片
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        // 2，开启线程下载图片
//        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
//                if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap(bitmap);
//                }
                mImageCache.put(url, bitmap);
            }
        });

    }

    /**
     * 下载图片
     *
     * @param imageUrl
     * @return
     */
    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            if (bitmap == null) {
                throw new Exception("bitmap download 为空");
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}

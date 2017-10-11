package com.whut.mvpsample.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2017/10/11
 *     desc   : 图片缓存
 *     version: 1.0
 * </pre>
 */
public class ImageCache {
    // 图片LRU缓存（最近最少使用）
    LruCache<String,Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    private void initImageCache(){
        // 计算可用最大内存
        int maxMemory=(int)(Runtime.getRuntime().maxMemory()/1024);
        // 取四分之一的可用内存作为缓存
        int cacheSize=maxMemory/4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }

    public void put(String url,Bitmap bitmap){
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }


}

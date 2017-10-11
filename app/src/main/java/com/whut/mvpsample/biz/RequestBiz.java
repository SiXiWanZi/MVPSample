package com.whut.mvpsample.biz;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2017/10/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface RequestBiz {
    // 请求数据业务
    void requestForData(OnRequestListener listener);
}

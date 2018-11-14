package com.betweencity.tm.betweencity.base;

import java.lang.ref.WeakReference;

/**
 * Created by kw on 2018/7/25.
 */

public abstract class BasePresentImpl<V>{
    /**
     * 持有UI接口的弱引用
     */
    protected WeakReference<V> mViewRef;
    /**
     * 获取数据方法
     */
    public abstract void fetch();
    /**
     * 绑定的方法
     * 在onCreate()中调用
     *
     * @param view
     */
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }
    /**
     * 解绑
     * 在onDestroy方法中调用，防止内存泄漏
     */
    public void detach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }

        onDestroy();
    }
    //释放资源处理
    public abstract void onDestroy();
}

package com.betweencity.tm.betweencity.myHttp.HttpManager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;


import com.betweencity.tm.betweencity.R;
import com.betweencity.tm.betweencity.commom.LoginOut;
import com.betweencity.tm.betweencity.commom.Var;
import com.betweencity.tm.betweencity.listner.BroadListener;
import com.betweencity.tm.betweencity.listner.LoadingError;
import com.betweencity.tm.betweencity.mvp.ui.pop_dialog.MyLoadingDialog;
import com.betweencity.tm.betweencity.wedget.MyToast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;


/**Diolog控制
 * Rt
 * Created by Administrator on 2017/12/19.
 */

public class ProgressSubscriber<T> extends DisposableObserver<T> implements ProgressCancelListener {
    private LoadingError loadingError;
    private SubscriberOnNextListenter mSubscriberOnNextListenter;
    private Activity context;
    /**
     * 两个diolog二选其一
     */
    //系统dialog
     private ProgressDialogHandler mProgressDialogHandler;
    //自定义dialog可以修改为帧动画
    private MyLoadingDialog dialog;
    private boolean is_dilog;

    /**
     *
     * @param context
     * @param is_dilog dialog控制器
     * @param mSubscriberOnNextListenter 处理数据后的返回监听
     */
    public ProgressSubscriber(Activity context, Boolean is_dilog, SubscriberOnNextListenter mSubscriberOnNextListenter) {
        this.mSubscriberOnNextListenter = mSubscriberOnNextListenter;
        this.context = context;
        this.is_dilog=is_dilog;
//        mProgressDialogHandler = new ProgressDialogHandler(context,this,is_dilog);
    }

    /**
     *
     * @param context
     * @param loadingError 进入一个页面获取数据失败，无数据，无网络的监听
     * @param is_dilog
     * @param mSubscriberOnNextListenter
     */
    public ProgressSubscriber(Activity context, LoadingError loadingError, Boolean is_dilog, SubscriberOnNextListenter mSubscriberOnNextListenter) {
        this.loadingError=loadingError;
        this.mSubscriberOnNextListenter = mSubscriberOnNextListenter;
        this.context = context;
        this.is_dilog=is_dilog;
//        mProgressDialogHandler = new ProgressDialogHandler(context,this,is_dilog);
    }

    /**
     * 在开始订阅的时候显示加载框
     */
    @Override
    public void onStart() {
//        if (mProgressDialogHandler != null) {
//            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
//        }
        /*************************这里是自定义的dilog*****************************************************/
        if(is_dilog){
            dialog=new MyLoadingDialog(context);
            dialog.showDialog("请稍后……");
            Var.getInstance().setBroadListener(new BroadListener() {
                @Override
                public void onSuccess(int i) {
                    if(i==4){
                        dismissProgressDialog();
                        onCancelProgress();
                    }
                }
            });
        }
    }


    /**
     * 在出错的时候也进行影藏
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            if(loadingError!=null){
                loadingError.NoNetwork(true);
            }
            Toast.makeText(context,"网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            if(loadingError!=null){
                loadingError.NoNetwork(true);
            }
            Toast.makeText(context,"网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if(e instanceof UnknownHostException){
            //HttpResultFunc<T>抛出的异常也会执行
            if(loadingError!=null){
                loadingError.NoNetwork(true);
            }
            Toast.makeText(context,"网络异常，主机地址未响应", Toast.LENGTH_SHORT).show();
        }else{
            if(e.getMessage().equals("20100")){
                MyToast.getInstance(context).showText("请重新登录", Gravity.CENTER, R.mipmap.ic_error);
                LoginOut.loginOutClean();
            }else{
                MyToast.getInstance(context).showText(e.getMessage(), Gravity.CENTER, R.mipmap.ic_error);
                RetrofitManager.i("---","--"+e.getMessage());
                mSubscriberOnNextListenter.getErr(1000000);
            }
        }
        dismissProgressDialog();
    }
    /**
     * 在完成的时候进行影藏
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListenter.next(t);
    }

    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();//取消订阅
        }
    }

    private void showProgressDialog() {
//        if (mProgressDialogHandler != null) {
//            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
//        }
    }

    private void dismissProgressDialog() {
//        if (mProgressDialogHandler != null) {
//            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
//            mProgressDialogHandler = null;
//        }
        if(dialog!=null){
            dialog.closeDialog();
        }
    }
}

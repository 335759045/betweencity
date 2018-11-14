package com.betweencity.tm.betweencity.base;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betweencity.tm.betweencity.commom.Constans;
import com.betweencity.tm.betweencity.utils.StatusBarUtil;
import com.betweencity.tm.betweencity.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, P extends BasePresentImpl<V>> extends Fragment implements View.OnClickListener{

    private View mContentView;
    private Context mContext;
    /**
     * P层引用
     */
    protected P mPresent;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取状态栏高度
        int statusBarHeight1 = -1;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        Constans.P_STATUSBAR_HEIGHT=statusBarHeight1;
        StatusBarUtil.setImmersiveStatusBar(getActivity(),true);
        //沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(getActivity().getResources().getColor(R.color.transparent));
//        }
        mPresent = createPresent();
        //做绑定关联View
        if(mPresent!=null){
            mPresent.attachView((V) this);
        }
        getData();
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);
        unbinder = ButterKnife.bind(this, mContentView);
        mContext = getActivity();
        setUpView();
        setUpData();
        return mContentView;
    }
    protected abstract int setLayoutResourceID();

    /*
     * @params
     * @name 子类实现具体的构建过程
     */
    protected abstract P createPresent();

    protected abstract void setUpView();
    /**
     * 设置数据
     *
     * @return
     */
    protected abstract void setUpData();

    public void toastShow(String str) {
        ToastUtil.createToastConfig().ToastShow(getActivity(),str);
    }
    public void startIntentAct(Context context, Class<?> tClass){
        Intent intent=new Intent(context,tClass);
        startActivity(intent);
    }

    /**
     * 获取数据
     *
     * @return
     */
    protected abstract void getData();

    public View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresent!=null){
            mPresent.detach();
        }
        unbinder.unbind();
    }
}

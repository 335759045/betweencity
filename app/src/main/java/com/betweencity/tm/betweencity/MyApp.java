package com.betweencity.tm.betweencity;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.betweencity.tm.betweencity.commom.Constans;
import com.betweencity.tm.betweencity.utils.SPUtil;
import com.betweencity.tm.betweencity.utils.SystemHelper;
import com.example.codeutils.utils.Utils;
import com.weavey.loading.lib.LoadingLayout;



public class MyApp extends MultiDexApplication {
	public static MyApp instance;
	public static SPUtil sp;
	public static Context context;
	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		context=this.getApplicationContext();
		SPUtil spUtil=new SPUtil(context,"userInfo");
		sp = spUtil;
		Constans.P_WIGHT=(int) (SystemHelper.getScreenInfo(context).widthPixels );
		Constans.P_HEIGHT=(int) (SystemHelper.getScreenInfo(context).heightPixels );

		LoadingLayout.getConfig()
				.setErrorText("出错啦~请稍后重试！")
				.setEmptyText("抱歉，暂无数据")
				.setNoNetworkText("无网络连接，请检查您的网络···")
				.setErrorImage(R.mipmap.ic_launcher)
				.setNoNetworkImage(R.mipmap.ic_launcher)
				.setAllTipTextColor(R.color.act_title_bg)
				.setAllTipTextSize(14)
				.setReloadButtonText("点击重试")
				.setReloadButtonTextSize(14)
				.setReloadButtonTextColor(R.color.act_title_bg)
				.setReloadButtonWidthAndHeight(150,40);
		//初始化Utils
		Utils.init(this);
	}

	public static synchronized MyApp getInstance() {
		return instance;
	}
}

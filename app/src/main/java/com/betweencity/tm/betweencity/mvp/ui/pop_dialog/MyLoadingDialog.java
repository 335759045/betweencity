package com.betweencity.tm.betweencity.mvp.ui.pop_dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;

import com.betweencity.tm.betweencity.R;
import com.betweencity.tm.betweencity.commom.Var;
import com.github.rahatarmanahmed.cpv.CircularProgressView;


/**
 * 自定义dialog
 */
public class MyLoadingDialog extends Dialog {
	private static MyLoadingDialog customProgressDialog = null;
	private Context context=null;
	private int theme ;
	public MyLoadingDialog(Context context){
		super(context);
		this.context=context;

	}
	public MyLoadingDialog(Context context, int theme){
		super(context, theme);
		this.context=context;
		this.theme=theme; 
	}
	public static MyLoadingDialog createDialog(Context context){
		customProgressDialog = new MyLoadingDialog(context, R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.loading_dialog);
//		/***********帧动画*********/
//		customProgressDialog.setContentView(R.layout.customprogressdialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		customProgressDialog.setCanceledOnTouchOutside(false);//点击外面消失dialog
		return customProgressDialog;
	}
	public void onWindowFocusChanged(boolean hasFocus){
    	
    	if (customProgressDialog == null){
    		return;
    	}
		CircularProgressView imageView = (CircularProgressView) customProgressDialog.findViewById(R.id.loading_dialog_image);
//		/***********帧动画*********/
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
//        animationDrawable.start();

//		Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.loading_dialog);
//		LinearInterpolator lin = new LinearInterpolator();
//		operatingAnim.setInterpolator(lin);
//		imageView.startAnimation(operatingAnim);
		imageView.startAnimation();
    }
	public MyLoadingDialog setTitile(String strTitle){
    	return customProgressDialog;
    }
//	public MyLoadingDialog setMessage(String strMessage){
//    	TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.loading_dialog_tex);
//
//    	if (tvMsg != null){
//    		tvMsg.setText(strMessage);
//    	}
//
//    	return customProgressDialog;
//    }
	public void showDialog(String str){
			if (customProgressDialog == null){
				customProgressDialog = MyLoadingDialog.createDialog(context);
//				customProgressDialog.setMessage(str);
			}
			customProgressDialog.show();
//			customProgressDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);//可以让在加载的时候操作后面的界面
	}
	public void closeDialog(){
		if (customProgressDialog != null){
			customProgressDialog.dismiss();
			customProgressDialog = null;

		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Var.getInstance().setVar(4);//监听dialog被取消
//			customProgressDialog.dismiss();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

package com.betweencity.tm.betweencity.wedget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.betweencity.tm.betweencity.R;

public class MyToast {
    private static Toast mToast;
    private static TextView textView;
    private View view;
    private static ImageView imageView;
    private static Context mContext;

    private MyToast(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_toast_view, null);
        textView = (TextView) view.findViewById(R.id.textView1);
        imageView = (ImageView) view.findViewById(R.id.iv_image);
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        mToast.setView(view);
    }

    public static void showText(CharSequence text, int gravity, int resource) {
        imageView.setBackgroundResource(resource);
        textView.setText(text);
        mToast.setGravity(gravity,0,0);
        mToast.show();
    }
    public static MyToast getInstance(Context context) {
        mContext = context;
        return SingletonHolder.INSTANCE;
    }
    //  创建单例
    private static class SingletonHolder {
        private static final MyToast INSTANCE = new MyToast(mContext);
    }
    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }
}

package com.betweencity.tm.betweencity.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.betweencity.tm.betweencity.R;


/**
 * Created by admin on 2017/7/18.
 */

public class ToastUtil {
    private static ToastUtil toastCommom;

    private Toast toast;

    private ToastUtil(){
    }

    public static ToastUtil createToastConfig(){
        if (toastCommom==null) {
            toastCommom = new ToastUtil();
        }
        return toastCommom;
    }

    /**
     * 显示Toast
     * @param context
     * @param tvString
     */

    public void ToastShow(Context context, String tvString){
        View layout = LayoutInflater.from(context).inflate(R.layout.toast_view,null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(tvString);
        text.setTextColor(context.getResources().getColor(R.color.white));
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}

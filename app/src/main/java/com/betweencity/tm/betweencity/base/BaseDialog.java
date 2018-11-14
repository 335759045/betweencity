package com.betweencity.tm.betweencity.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/**
 * Created by Jason Chen on 2017/2/22.
 */

public class BaseDialog extends Dialog {
    protected View view;
    protected LayoutInflater inflater;
    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.inflater = LayoutInflater.from(context);
    }
    public BaseDialog(Context context, int styleId) {
        super(context, styleId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.inflater = LayoutInflater.from(context);
    }
}

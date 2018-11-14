package com.betweencity.tm.betweencity.listner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * Created by yidong on 2017/12/28.
 */

public interface OnDrawItemDecorationListener {
    /**
     * 自定义的 GroupHeader 绘制接口
     *
     * @param c
     * @param paint
     * @param textPaint
     * @param params    共四个值left、top、right、bottom 代表GroupHeader所在区域的四个坐标值
     * @param position  原始数据源中的position
     */
    void onDrawGroupHeader(Canvas c, Paint paint, TextPaint textPaint, int[] params, int position);

    void onDrawSuspensionGroupHeader(Canvas c, Paint paint, TextPaint textPaint, int[] params, int position);
}

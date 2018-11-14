package com.betweencity.tm.betweencity.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by yidong on 2017/5/16.
 */

public class DataUtils {
    /**
     * 从本地读取地址json
     *
     * @return
     */
    public static String getFromAssets(final Context context, final String jsonName) {
        BufferedReader bufReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    context.getResources().getAssets().open(jsonName));
            bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                sb.append(line);
            }
            if (TextUtils.isEmpty(sb.toString())) {
                return "";
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}

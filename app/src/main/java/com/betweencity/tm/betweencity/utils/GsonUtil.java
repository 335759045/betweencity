package com.betweencity.tm.betweencity.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */

public class GsonUtil {
    private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }
    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }
    /**
     * 把modelA对象的属性值赋值给bClass对象的属性。
     * @param modelA
     * @param bClass
     * @param
     * @return
     */

    public static <A, B> B modelA2B(A modelA, Class<B> bClass) {
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            B instanceB = gson.fromJson(gsonA, bClass);

            Log.d("TTTTTTTTT", "modelA2B A=" + modelA.getClass() + " B=" + bClass + " 转换后=" + instanceB);
            return instanceB;
        } catch (Exception e) {
            Log.e("TTTTTTTTT", "modelA2B Exception=" + modelA.getClass() + " " + bClass + " " + e.getMessage());
            return null;
        }
    }
    /**
     * 将JSON数据中的 /r/n 替换 为 /n
     */
    public static String JsonFilter(String jsonstr) {
        jsonstr = jsonstr.replace("/N", "|N");
        return jsonstr;
    }
    /**
     * 转成list
     */
    public static <T> List<T> JsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        String s=JsonFilter(json);
        JsonArray array = new JsonParser().parse(s).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }
    /**
     * 转成list
     *
     * @param
     * @param cls
     * @return
     */
    public static <T> ArrayList<T> GsonToList2(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }
}

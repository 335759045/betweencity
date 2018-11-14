package com.betweencity.tm.betweencity.commom;


import com.betweencity.tm.betweencity.MyApp;

/**
 * Created by kw on 2018/9/18.
 */
public class LoginOut {
    public static void loginOutClean(){
        MyApp.sp.putString(Constans.APPKEY,"");
        MyApp.sp.putString(Constans.PHONE,"");
        MyApp.sp.putString(Constans.PREFIX,"");
        MyApp.sp.putString(Constans.AVATAR,"");
        MyApp.sp.putString(Constans.USERNAME,"");
        MyApp.sp.putBoolean(Constans.LOGIN,false);
        MyApp.sp.putString(Constans.CITY_ID,"");
        MyApp.sp.putString(Constans.CITY,"");
    }
}

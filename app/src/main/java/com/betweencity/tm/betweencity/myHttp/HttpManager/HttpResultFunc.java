package com.betweencity.tm.betweencity.myHttp.HttpManager;

/**
 * Created by Administrator on 2017/12/19.
 */



import com.betweencity.tm.betweencity.bean.HttpResult;

import io.reactivex.functions.Function;

/**
 * 用来处理请求的code
 * @param <T>返回的对象
 */
public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
    @Override
    public T apply(HttpResult<T> tHttpResult) {
//        Log.e("tttttttttttt",""+tHttpResult.toString());
        if (tHttpResult.getCode() != 0) {
            if(tHttpResult.getCode() == 20100){
                throw new RuntimeException(tHttpResult.getCode()+"");
            }else{
                throw new RuntimeException(tHttpResult.getMsg());
            }
        }
        return tHttpResult.getData();
    }
}
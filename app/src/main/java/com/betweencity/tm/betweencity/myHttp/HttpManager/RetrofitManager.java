package com.betweencity.tm.betweencity.myHttp.HttpManager;

import android.util.Log;

import com.betweencity.tm.betweencity.commom.Constans;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/12/19.
 */

public class RetrofitManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit retrofit;
    public RetrofitManager(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        httpClientBuilder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        httpClientBuilder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

//        // 添加公共参数拦截器 根据项目需求添加
//        String app_key = MyApp.sp.getString(Constans.APPKEY,"");
//        String phone = MyApp.sp.getString(Constans.PHONE,"");
//        String prefix = MyApp.sp.getString(Constans.PREFIX,"");
//        try {
//            app_key = app_key + "::" + phone + "::"+ prefix+"::" + SystemHelper.getNowTimeMills() / 1000;//秒时间戳
//            app_key = RSAUtil.EncryptDataOfPublicKey(app_key, MyApp.context.getResources().openRawResource(R.raw.paodao_rsa_public_key));
//        } catch (Exception e) {
//            Log.e("---------","app_key encryption failed for RSA");
//        }
//
//        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("X-Authentication",app_key)
//                .addHeaderParams("X-Package", "my.pdbroker")
//                .addHeaderParams("X-Os", "Android")
//                .addHeaderParams("X-Client", "Mobile")
//                .addHeaderParams("X-Version","")
//                .build();
//
//
//
//        httpClientBuilder.addInterceptor(commonInterceptor);

        //日志拦截  正式模式注释掉
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    i("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    i("OKHttp-----", message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(interceptor);
//
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        //Interceptor的典型使用场景，就是对request和response的Headers进行编辑
//                        Request mRequest = chain.request().newBuilder()
//                                .header("X-Authentication", finalApp_key)
//                                .build();
//                        return chain.proceed(mRequest);
//                    }
//                });
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constans.URL_CONTEXTPATH)
                .build();
    }

    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitManager getInstance(){
        return  new RetrofitManager();
    }
    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
    public static void i(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.e(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.e(tag, msg);
    }
}

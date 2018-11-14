package com.betweencity.tm.betweencity.myHttp;


import com.betweencity.tm.betweencity.api.ApiBasic;
import com.betweencity.tm.betweencity.myHttp.HttpManager.ObjectLoader;
import com.betweencity.tm.betweencity.myHttp.HttpManager.RetrofitManager;

/**方法类
 * Created by Administrator on 2017/12/19.
 */

public class HttpMethods extends ObjectLoader {
    private static ApiBasic moService;
    public HttpMethods(){
        moService = RetrofitManager.getInstance().create(ApiBasic.class);
    }
    public static HttpMethods getInstance(){
        return new HttpMethods();
    }

//    /**
//     * 登录
//     */
//    public void login(int loginType, Map<String,String> params, DisposableObserver<LoginBean> subscriber){
//        if(loginType==1){//验证码登录
//
//        }else  if(loginType==2){//密码登录
//            observe(moService.Login(params)).map(new HttpResultFunc<LoginBean>()).subscribe(subscriber);
//        }
////        moService.login(params)
////                .subscribeOn(Schedulers.io())sad as as
////                .observeOn(AndroidSchedulers.mainThread())
////                .map( new HttpResultFunc())
////                .subscribe(subscriber);
//    }
//    /**
//     * 获取验证码
//     */
//    public void getCode(String phone, DisposableObserver subscriber){
//        observe(moService.getCode(phone)).map(new HttpResultFunc()).subscribe(subscriber);
//    }
//    /**
//     * 修改手机号
//     */
//    public void changePhone(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.changePhone(params)).map(new HttpResultFunc()).subscribe(subscriber);
//    }
//    /**
//     * 修改密码
//     */
//    public void changePWD(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.changePWD(params)).map(new HttpResultFunc()).subscribe(subscriber);
//    }
//    /**
//     * 退出登录
//     */
//    public void loginOut( DisposableObserver subscriber){
//        Map<String,String> map=new HashMap<>();
//        observe(moService.loginOut(map)).map(new HttpResultFunc()).subscribe(subscriber);
//    }
//    /**
//     * 检测更新
//     */
//    public void checkVerson(DisposableObserver subscriber){
//        observe(moService.checkVerson()).map(new HttpResultFunc<VersonBean>()).subscribe(subscriber);
//    }
//    /**
//     * 获取秒拒验证参数和值
//     */
//    public void mjyzGetData(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.mjyzGetData(params)).map(new HttpResultFunc<MJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存数据
//     */
//    public void saveMJYZ(Map<String,String> params, int where, DisposableObserver subscriber){
//        if(where==1){
//            observe(moService.saveMJYZ(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//        }else if(where==4){
//            storeDealer(params,subscriber);
//        }
//    }
//    /**
//     * 发送驻行
//     */
//    public void fszh(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.fszh(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 上传征信图片
//     */
//    public void sczx(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.sczx(params)).map(new HttpResultFunc<SCZXBean>()).subscribe(subscriber);
//    }
//    /**
//     * 大数据查询
//     */
//    public void dsjcx(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.dsjyz(params)).map(new HttpResultFunc<DSJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 获取征信图片
//     */
//    public void getZXList(String params, DisposableObserver subscriber){
//        observe(moService.getZXList(params)).map(new HttpResultFunc<Images>()).subscribe(subscriber);
//    }
//    /**
//     * 获取申请人参数设置和上传值
//     */
//    public void basicRuleValue(String params, DisposableObserver subscriber){
//        observe(moService.basicRuleValue(params)).map(new HttpResultFunc<ApplyPeopleInfoBean>()).subscribe(subscriber);
//    }
//    /**
//     * 获取市场列表
//     */
//    public void getMarket(DisposableObserver subscriber){
//        observe(moService.getMarket()).map(new HttpResultFunc<MarketBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存基本信息
//     */
//    public void saveBasicData(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.saveBasicData(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 获取贷款信息参数和值
//     */
//    public void getLoanInfoData(String orderid, DisposableObserver<LoanInfoBean> subscriber){
//        observe(moService.getLoanInfoData(orderid)).map(new HttpResultFunc<LoanInfoBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存贷款信息
//     */
//    public void saveLoanInfoData(Map<String,String> params, DisposableObserver<SaveMJYZBean> subscriber){
//        observe(moService.postLoanInfoData(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 二手车图片参数和值
//     */
//    public void carRuleValue(String orderid, DisposableObserver<ESCImageBean> subscriber){
//        observe(moService.carRuleValue(orderid)).map(new HttpResultFunc<ESCImageBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存二手车图片（发送评估）
//     */
//    public void saveCarImg(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.saveCarImg(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 主贷人相关证件资料参数和值
//     */
//    public void loanRuleValue(String orderid, DisposableObserver<ESCImageBean> subscriber){
//        observe(moService.loanRuleValue(orderid)).map(new HttpResultFunc<ESCImageBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存主贷人相关图片
//     */
//    public void saveLoanImg(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.saveLoanImg(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 流水相关参数和值
//     */
//    public void wageWaterRuleValue(String orderid, int type, DisposableObserver subscriber){
//        if(type==1){
//            observe(moService.wageWaterRuleValue(orderid)).map(new HttpResultFunc<ESCImageBean>()).subscribe(subscriber);//流水
//        }else if(type==2){
//            observe(moService.houseRuleValue(orderid)).map(new HttpResultFunc<ESCImageBean>()).subscribe(subscriber);//房产
//        }
//    }
//    /**
//     * 保存流水
//     */
//    public void saveWagesFloW(Map<String,String> params, int type, DisposableObserver subscriber){
//        if(type==1){
//            observe(moService.saveWagesFloW(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);//保存流水
//        }else if(type==2){
//            observe(moService.saveHouse(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);//保存房产
//        }
//    }
//    /**
//     * 补充资料参数和值
//     */
//    public void otherRuleValue(String orderid, DisposableObserver subscriber){
//        observe(moService.otherRuleValue(orderid)).map(new HttpResultFunc<OtherInfoBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存补充资料
//     */
//    public void saveSupplement(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.saveSupplement(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
//    /**
//     * 商家收款账号列表
//     */
//    public void dealerLoan(DisposableObserver subscriber){
//        observe(moService.dealerLoan()).map(new HttpResultFunc<AccountBean>()).subscribe(subscriber);
//    }
//    /**
//     * 保存商家收款账号
//     */
//    public void storeDealer(Map<String,String> params, DisposableObserver subscriber){
//        observe(moService.storeDealer(params)).map(new HttpResultFunc<SaveMJYZBean>()).subscribe(subscriber);
//    }
}
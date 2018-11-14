package com.betweencity.tm.betweencity.commom;


import com.betweencity.tm.betweencity.listner.BroadListener;

/**
 * Created by Administrator on 2016/12/22.
 */

public class Var {
    private BroadListener broadListener;
    private  int var;
    private static Var varInstance;
    private Var(){

    }
    public static Var getInstance(){
        if(null==varInstance)
        {
            varInstance=new Var();
        }
        return varInstance;
    }

    public void setBroadListener(BroadListener broadListener){
        this.broadListener=broadListener;
    }
    /**
     * @return the var
     */
    public int getVar() {
        return var;
    }

    /**
     * @param var the var to set
     */
    public void setVar(int var) {
        this.var = var;
        if(null!=broadListener){
            if(var==1){
                broadListener.onSuccess(var);
            }else if(var==4){//dialog  dismiss监听
                broadListener.onSuccess(var);
            }
        }else{
        }
    }
}

package com.betweencity.tm.betweencity.wedget;

import android.content.Context;
import android.util.AttributeSet;

import com.betweencity.tm.betweencity.R;


/**
 */

public class CountDownTimerView extends android.support.v7.widget.AppCompatButton {

    private CountDownTimer countDownTimer;
    private OnCountDownListener mListener;
    private voiceListner voiceListner;

    public CountDownTimerView(Context context) {
        super(context);
    }

    public CountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTimerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void startCountDown(long millisInFuture) {
        setEnabled(false);
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer() {
                @Override
                protected void onTick(long millisLeft) {
                    int secondsLeft = (int) Math.ceil((double) millisLeft / 1000.0);
                    setText(getResources().getString(R.string.btn_send_code_again, secondsLeft));
                    setTextColor(getResources().getColor(R.color.gray_text));
                    setBackgroundResource(R.drawable.shape_galy_bk2);
                    if(voiceListner!=null&&secondsLeft==20){
                        voiceListner.voiceListnerBack(true);
                    }
                }

                @Override
                protected void onFinish() {
                    setText(R.string.btn_send_code);
                    setTextColor(getResources().getColor(R.color.red));
//                    setBackgroundColor(getResources().getColor(R.color.fen2));
                    setBackgroundResource(R.drawable.shape_get_code_btn);
                    if (mListener != null) {
                        setEnabled(mListener.onCountDownFinishState());
                    } else {
                        setEnabled(true);
                    }
                }
            };
        }
        countDownTimer.start(millisInFuture, 1000);
    }

    public void cancelCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        mListener = null;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled && countDownTimer != null && countDownTimer.isCountingDown()) {
            return;
        }
        super.setEnabled(enabled);
    }

    public void setVoiceListner(CountDownTimerView.voiceListner voiceListner) {
        this.voiceListner = voiceListner;
    }

    public void setOnCountDownListener(OnCountDownListener listener) {
        mListener = listener;
    }

    public interface OnCountDownListener {
        boolean onCountDownFinishState();
    }
    public interface voiceListner{
        void voiceListnerBack(boolean isVisible);
    }
}
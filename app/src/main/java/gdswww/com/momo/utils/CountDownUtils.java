package gdswww.com.momo.utils;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/**
 * 倒计时的工具
 *
 * @author Administrator
 */
public class CountDownUtils extends CountDownTimer {
    private TextView tv_display, tv_displays, tv_min, tv_second, tv_millons;
    private Button bt_display;


    public CountDownUtils(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        tv_display = textView;
    }

    public CountDownUtils(long millisInFuture, long countDownInterval, TextView min, TextView sec, TextView mill) {
        super(millisInFuture, countDownInterval);
        tv_min = min;
        tv_second = sec;
        tv_millons = mill;
    }

    public CountDownUtils(long millisInFuture, long countDownInterval, TextView textView, TextView textViews, Button button) {
        super(millisInFuture, countDownInterval);
        tv_displays = textView;
        tv_display = textViews;
        bt_display = button;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = millisUntilFinished / dd;
        long hour = (millisUntilFinished - day * dd) / hh;
        long minute = (millisUntilFinished - day * dd - hour * hh) / mi;
        long second = (millisUntilFinished - day * dd - hour * hh - minute * mi) / ss;
        if (tv_displays != null) {
            tv_displays.setEnabled(false);
            tv_displays.setText(millisUntilFinished / 1000 + "s重新发送");
        }
        if (tv_display != null) {
            String hours = hour < 10 ? "0" + hour : "" + hour;
            String minutes = minute < 10 ? "0" + minute : "" + minute;
            String seconds = second < 10 ? "0" + second : "" + second;
            tv_display.setText(hours + ":" + minutes + ":" + seconds);
        }
        if (tv_min != null && tv_second != null && tv_millons != null) {
            tv_min.setText("00");
            tv_second.setText("00");
            if (millisUntilFinished / 1000 < 60) {
                tv_millons.setText(millisUntilFinished / 1000 + "");
            }
            if (millisUntilFinished / 1000 > 60) {
                tv_min.setText(hour < 10 ? "0" + hour : "" + hour);
                tv_second.setText(minute < 10 ? "0" + minute : "" + minute);
                tv_millons.setText(second < 10 ? "0" + second : "" + second);
            }


        }
    }

    @Override
    public void onFinish() {
        if (bt_display != null) {
            bt_display.setEnabled(true);
            tv_displays.setText("重新发送");
        }
        if (tv_displays != null) {
            tv_displays.setEnabled(true);
            tv_displays.setText("重新发送");
        }

    }

}

package gdswww.com.momo.base;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.gdswww.library.toolkit.PreferenceHelper;

import java.util.List;

public class AppContext extends Application {
    public static PreferenceHelper ph;// 全局配置文件
    private Context context;
    private String appDir;
    private static AppContext instance;
    public Vibrator mVibrator;
    public static String provName = "";
    public static String cityName = "";
    // 当前已经打开了debug调试模式
    public static final boolean DEBUG_FLAG = true;

    public boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }



    /**
     * 错误信息打印
     *
     * @param tag
     * @param loginfo
     */
    public static void LogInfo(String tag, String loginfo) {
        if (AppContext.DEBUG_FLAG) {
            Log.e(tag, loginfo);
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getwidth() {
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getheight() {
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}


package gdswww.com.momo.utils;

import com.tencent.tauth.Tencent;

/**
 * 接口
 * Created by Administrator on 2017/5/18 0018.
 */
public class Contents {
    public static final String GET_USERINFO = "getuserinfo";
    public static final String SHAREQQ = "shareqq";
    public static final String APP_ID = "1105981335";
    private static String url = "http://192.168.1.111/momo/Api/Index/";
    public static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String SENDCODE = "Common/sendCode";
    private static final String EDITDATA = "User/editData";
    private static final String MAINPAGEINDEX = "index";
    private static String testUrl = "";
    public static Tencent mTencent = null;
    public static String token;

    public static void setToken(String token) {
        Contents.token = token;
    }

    public static String getToken() {
        return token;
    }

    /**
     * 登录
     *
     * @return
     */
    public static String login() {
        return url + LOGIN;
    }

    /**
     * 注册
     *
     * @return
     */
    public static String register() {
        return url + REGISTER;
    }

    /**
     * 发送验证吗
     *
     * @return
     */
    public static String getSendCode() {
        return url + SENDCODE;
    }

    /**
     * 编辑资料
     *
     * @return
     */
    public static String getEditData() {
        return url + EDITDATA;
    }

    /**
     * 首页
     *
     * @return
     */
    public static String getMainPageIndex() {
        return url + MAINPAGEINDEX;
    }
}

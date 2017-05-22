package gdswww.com.momo.base;

public class MyUrl {
    //    public final static String code = "code";
    public final static String success = "000";
    public final static String result = "result";
    public final static String nullToast = "访问失败";
    public final static String noMoreData = "没有更多数据";
//    public final static String baseUrl = "http://shenghuo.gdswww.com:88/index.php/home/index/";
    public final static String baseUrl = "http://life.gdswww.com/index.php/home/index/";
    public final static String picUrl = "http://192.168.1.9:8083";
//        public final static String baseUrl = "http://192.168.1.119:8083/index.php/home/index/";
//    192.168.1.9:8083/index.php/home/    http://192.168.1.119:8083/index.php/home/api      http://192.168.1.9:8083/index.php/home/api
    //http://life.gdswww.com/index.php/home/api 服务器地址
    public final static String companyUrl = "";

    //用户登录接口
    public static String login() {
        return baseUrl + "login";
    }

    //注册接口
    public static String register() {
        return baseUrl + "register";
    }

    //设置独立密码口
    public static String set_pwd() {
        return baseUrl + "set_pwd";
    }

    //发送验证码接口
    public static String code() {
        return baseUrl + "code";
    }

    //忘记密码接口
    public static String forgot_pw() {
        return baseUrl + "forgot_pw";
    }

    //添加收货地址接口
    public static String add_address() {
        return baseUrl + "add_address";
    }

    //收货地址列表接口
    public static String address_list() {
        return baseUrl + "address_list";
    }

    //设置默认地址接口
    public static String set_address() {
        return baseUrl + "set_address";
    }

    //收货地址修改查询接口
    public static String address_info() {
        return baseUrl + "address_info";
    }

    //修改收货地址接口
    public static String edit_address() {
        return baseUrl + "edit_address";
    }

    //判断是否注册接口
    public static String judge_login() {
        return baseUrl + "judge_login";
    }

    //判断是否设置密码接口
    public static String judge_pwd() {
        return baseUrl + "judge_pwd";
    }

    //设置接口
    public static String set_up() {
        return baseUrl + "set_up";
    }

    //添加店铺接口
    public static String add_shop() {
        return baseUrl + "add_shop";
    }

    //修改店铺接口
    public static String edit_shop() {
        return baseUrl + "edit_shop";
    }

    //修改手机号接口
    public static String edit_phone() {
        return baseUrl + "edit_phone";
    }

    //设置修改接口
    public static String edit_set() {
        return baseUrl + "edit_set";
    }

    //我的接口
    public static String wode() {
        return baseUrl + "wode";
    }

    //我的卡卷接口
    public static String wode_card_volume() {
        return baseUrl + "wode_card_volume";
    }

    //我的微卡接口
    public static String wode_weika() {
        return baseUrl + "wode_weika";
    }

    //我的收藏宝贝接口
    public static String wode_shoucang() {
        return baseUrl + "wode_shoucang";
    }

    //资讯分类接口
    public static String news_type() {
        return baseUrl + "news_type";
    }

    //资讯列表接口
    public static String news_lists() {
        return baseUrl + "news_lists";
    }

    //资讯详情接口
    public static String news_info() {
        return baseUrl + "news_info";
    }

    //资讯详情内容接口
    public static String news_details_info() {
        return baseUrl + "news_details_info";
    }

    //资讯详情评论接口
    public static String news_info_comment() {
        return baseUrl + "news_info_comment";
    }

    //评论资讯接口
    public static String add_comment() {
        return baseUrl + "add_comment";
    }

    //回复评论接口
    public static String reply_comment() {
        return baseUrl + "reply_comment";
    }

    //发布资讯接口
    public static String add_news() {
        return baseUrl + "add_news";
    }

    //上传资讯图片接口
    public static String add_news_img() {
        return baseUrl + "add_news_img";
    }

    //轮播图接口
    public static String advertising() {
        return baseUrl + "advertising";
    }

    //发现列表接口
    public static String faxian_list() {
        return baseUrl + "faxian_list";
    }

    //店铺详情接口
    public static String shop_info() {
        return baseUrl + "shop_info";
    }

    //店铺详情下的信息接口
    public static String shop_information() {
        return baseUrl + "shop_information";
    }
    //向商家申请会员卡
    public static String add_weika() {
        return baseUrl + "add_weika";
    }

    //商品详情接口
    public static String goods_info() {
        return baseUrl + "goods_info";
    }

    //图文详情接口
    public static String graphic_details() {
        return baseUrl + "graphic_details";
    }

    //加入购物车接口
    public static String add_shopping_cart() {
        return baseUrl + "add_shopping_cart";
    }

    //购物车列表接口
    public static String shopping_cart_list() {
        return baseUrl + "shopping_cart_list";
    }

    //购物车删除接口
    public static String shopping_cart_delete() {
        return baseUrl + "shopping_cart_delete";
    }

    //购物车商品数量加减接口
    public static String cart_add_subtract() {
        return baseUrl + "cart_add_subtract";
    }

    //计算购物车选中商品价格接口
    public static String cart_reckon() {
        return baseUrl + "cart_reckon";
    }

    //购物车确认订单接口
    public static String cart_confirm_order() {
        return baseUrl + "cart_confirm_order";
    }

    //购物车下单接口
    public static String cart_lower_order() {
        return baseUrl + "cart_lower_order";
    }

    //单独购买确认订单接口
    public static String alone_confirm_order() {
        return baseUrl + "alone_confirm_order";
    }

    //单独购买下单接口
    public static String alone_lower_order() {
        return baseUrl + "alone_lower_order";
    }

    //通讯录列表接口
    public static String mail_list() {
        return baseUrl + "mail_list";
    }

    //通讯录好友添加接口
    public static String mail_list_add() {
        return baseUrl + "mail_list_add";
    }

    //店铺列表接口
    public static String shop_list() {
        return baseUrl + "shop_list";
    }

    //通讯录详情接口
    public static String mail_list_info() {
        return baseUrl + "mail_list_info";
    }

    //通讯录修改名片接口
    public static String mail_list_edit() {
        return baseUrl + "mail_list_edit";
    }

    //创建群组接口
    public static String add_group() {
        return baseUrl + "add_group";
    }

    //群组添加好友接口
    public static String add_group_user() {
        return baseUrl + "add_group_user";
    }

    //群组列表接口
    public static String group_list() {
        return baseUrl + "group_list";
    }

    //群组下的用户列表接口
    public static String group_user_list() {
        return baseUrl + "group_user_list";
    }

    //群组下的用户删除接口
    public static String group_user_delete() {
        return baseUrl + "group_user_delete";
    }

    //我的资讯接口
    public static String wode_news() {
        return baseUrl + "wode_news";
    }

    //我的关注接口
    public static String wode_follow() {
        return baseUrl + "wode_follow";
    }

    //用户详细信息
    public static String user_details() {
        return baseUrl + "user_details";
    }

    //咨询，相册，关于我
    public static String user_details_tow() {
        return baseUrl + "user_details_tow";
    }

    //发送资讯
    public static String send_out() {
        return baseUrl + "send_out";
    }

    //显示发送资讯界面
    public static String show_send_out() {
        return baseUrl + "show_send_out";
    }

    //关注
    public static String follow() {
        return baseUrl + "follow";
    }

    //取消关注
    public static String cancel_follow() {
        return baseUrl + "cancel_follow";
    }

    //支付订单
    public static String pay_cart_lower_order() {
        return baseUrl + "pay_cart_lower_order";
    }

    //充值订单
    public static String charge_money() {
        return baseUrl + "charge_money";
    }

    //充值支付
    public static String pay_charge_money() {
        return baseUrl + "pay_charge_money";
    }

    //充值消费记录
    public static String record() {
        return baseUrl + "record";
    }

    //我的订单
    public static String my_order() {
        return baseUrl + "my_order";
    }

    //收藏
    public static String collection() {
        return baseUrl + "collection";
    }

    //删除地址
    public static String delete_addes() {
        return baseUrl + "delete_addes";
    }

    //筛选分类
    public static String screen() {
        return baseUrl + "screen";
    }

    //显示发布资讯界面
    public static String up_news() {
        return baseUrl + "up_news";
    }

    //发现搜索接口
    public static String find_search() {
        return baseUrl + "find_search";
    }

    //首页搜索
    public static String home_search() {
        return baseUrl + "home_search";
    }

    //通讯录搜索
    public static String Contacts_search() {
        return baseUrl + "Contacts_search";
    }

    //显示订单评价
    public static String show_order_evaluate() {
        return baseUrl + "show_order_evaluate";
    }

    //评价订单
    public static String evaluate_order() {
        return baseUrl + "evaluate_order";
    }

    //城市数据
    public static String CityList() {
        return baseUrl + "CityList";
    }

    //上传资讯封面图片
    public static String UpImage() {
        return baseUrl + "UpImage";
    }

    //上传资讯图片
    public static String fileupload() {
        return baseUrl + "fileupload";
    }


}

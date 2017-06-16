package gdswww.com.momo.view;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gdswww.com.momo.R;

import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by Administrator on 2017/6/15 0015.
 * 自定义 dialog样式
 */

public class ZhuBoDialog extends Dialog {
    public ZhuBoDialog(Context context) {
        super(context);
    }

    public ZhuBoDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;//当前页面
        private String name;//主播名字
        private String imgPath;//头像路径
        private String sex;//性别 男 1   女 2
        private String message;
        private String city;//城市
        private String fensi;//粉丝
        private String huozeng;//获赠
        private String guanzhu;//关注
        private String songchu;//送出
        private View view;//布局
        private View.OnClickListener quxiaoOnCliCKListener;//取消事件
        private View.OnClickListener guanzhuOnCliCKListener;//关注
        private View.OnClickListener aiteOnCliCKListener;//艾特
        private View.OnClickListener songliOnCliCKListener;//送礼
        private View.OnClickListener quanxianOnCliCKListener;//权限

        /**
         * 当前页面
         *
         * @param context
         */
        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置主播莫名字
         *
         * @param name
         * @return
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * 设置性别
         *
         * @param sex
         */
        public Builder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        /**
         * 关注
         *
         * @param guanzhu
         * @return
         */
        public Builder setGuanZhu(String guanzhu) {
            this.guanzhu = guanzhu;
            return this;
        }

        /**
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置城市
         *
         * @param city
         */
        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * 粉丝数量
         *
         * @param fensi
         */
        public Builder setFensi(String fensi) {
            this.fensi = fensi;
            return this;
        }

        /**
         * 获赠量
         *
         * @param huozeng
         */
        public Builder setHuozeng(String huozeng) {
            this.huozeng = huozeng;
            return this;
        }

        /**
         * 送出
         *
         * @param songchu
         */
        public Builder setSongchu(String songchu) {
            this.songchu = songchu;
            return this;
        }

        /**
         * 设置布局
         *
         * @param view
         * @return
         */
        public Builder setContentView(View view) {
            this.view = view;
            return this;
        }

        /**
         * 取消
         *
         * @param
         * @return
         */
        public Builder setQuxiaoOnCliCKListener(View.OnClickListener quxiaoOnCliCKListener) {
            this.quxiaoOnCliCKListener = quxiaoOnCliCKListener;
            return this;
        }

        /**
         * 关注
         *
         * @param guanzhuOnCliCKListener
         * @return
         */
        public Builder setGuanzhuOnCliCKListener(View.OnClickListener guanzhuOnCliCKListener) {
            this.guanzhuOnCliCKListener = guanzhuOnCliCKListener;
            return this;
        }

        /**
         * 艾特
         *
         * @param aiteOnCliCKListener
         * @return
         */
        public Builder setAiTeOnCliCKListener(View.OnClickListener aiteOnCliCKListener) {
            this.aiteOnCliCKListener = aiteOnCliCKListener;
            return this;
        }

        /**
         * 送礼
         *
         * @param songliOnCliCKListener
         * @return
         */
        public Builder setSongLiOnCliCKListener(View.OnClickListener songliOnCliCKListener) {
            this.songliOnCliCKListener = songliOnCliCKListener;
            return this;
        }

        /**
         * 权限
         *
         * @param quanxianOnCliCKListener
         * @return
         */
        public Builder setQuanXianOnCliCKListener(View.OnClickListener quanxianOnCliCKListener) {
            this.quanxianOnCliCKListener = quanxianOnCliCKListener;
            return this;
        }

        public ZhuBoDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ZhuBoDialog dialog = new ZhuBoDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_zhubo, null);
            dialog.addContentView(layout, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            (ImageView)layout.findViewById(R.id.zhubo_touxiang_iv).setBackgroundResource(R.mipmap.zhubo);//主播
            ((TextView) layout.findViewById(R.id.zhubo_addr_tv)).setText(city);//地址
            ((TextView) layout.findViewById(R.id.zhubo_name_tv)).setText(name);//名字加ID
            ((TextView) layout.findViewById(R.id.zhubo_guanzhu_tv)).setText(guanzhu);//关注
            ((TextView) layout.findViewById(R.id.zhubo_fs_tv)).setText(fensi);//粉丝
            ((TextView) layout.findViewById(R.id.zhubo_huozeng_tv)).setText(huozeng);//获赠
            ((TextView) layout.findViewById(R.id.zhubo_songchu_tv)).setText(songchu);//蹭出
            layout.findViewById(R.id.click_aite_tv).setOnClickListener(aiteOnCliCKListener);//艾特
            layout.findViewById(R.id.click_guanzhu_tv).setOnClickListener(guanzhuOnCliCKListener);//关注
            layout.findViewById(R.id.click_quanxian_tv).setOnClickListener(quanxianOnCliCKListener);//权限
            layout.findViewById(R.id.click_songliwu_tv).setOnClickListener(songliOnCliCKListener);//送礼
            layout.findViewById(R.id.dialog_guanbi_iv).setOnClickListener(quxiaoOnCliCKListener);//关闭 quxiao

            //aite

//            ((ImageView)layout.findViewById(R.id.zhubo_sex_iv)).(songchu);//性别
            dialog.setContentView(layout);
            return dialog;
        }

    }


}

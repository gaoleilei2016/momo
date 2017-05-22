package gdswww.com.momo.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.gdswww.library.fragment.BaseFragment;

import gdswww.com.momo.R;

/**
 * Created by Administrator on 2016/9/8.
 */
public abstract class MyBaseFragment extends BaseFragment {
    //Fragment当前状态是否可以见
    protected boolean isVisible;

    public MyBaseFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    protected void setMyTitle(String str) {
        aq.id(R.id.tv_activity_title).text(str);
    }

//    protected void setRightIv(int imgRes) {
//        aq.id(R.id.iv_activity_title_right).visibility(View.VISIBLE).image(imgRes);
//    }

    protected void showRight(String rightStr, View.OnClickListener listener) {
        aq.id(R.id.iv_activity_title_right).visibility(View.VISIBLE).text(rightStr).clicked(listener);
    }

    public void showRightText(String rightStr, View.OnClickListener listener) {
        aq.id(R.id.tv_activity_title_right).visibility(View.VISIBLE).text(rightStr).clicked(listener);
    }

    protected void goActivityFromFragment(Class<? extends Activity> cls) {
        getActivity().startActivity(new Intent(getActivity(), cls));
    }

    protected void goActivityFinishFromFragment(Class<? extends Activity> cls) {
        getActivity().startActivity(new Intent(getActivity(), cls));
        getActivity().finish();
    }

    protected void hindeBack() {
        aq.id(R.id.ib_activity_title_back).visibility(View.INVISIBLE);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 当前可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 当前不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     */
    protected abstract void lazyLoad();

    /**
     * 为视图注册点击事件
     *
     * @param viewId   视图viewid
     * @param listener 监听器
     */
    public void clickView(int viewId, View.OnClickListener listener) {
        aq.id(viewId).clicked(listener);
    }

    /**
     * 视图设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, String text) {
        aq.id(viewId).text(text);
    }

}

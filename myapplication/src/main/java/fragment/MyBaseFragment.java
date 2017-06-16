package fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.gdswww.library.fragment.BaseFragment;
public abstract class MyBaseFragment extends BaseFragment {
    //Fragment当前状态是否可以见
    protected boolean isVisible;

    public MyBaseFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
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

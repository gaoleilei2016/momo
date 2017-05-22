package com.gdswww.library.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.HashMap;

@SuppressWarnings("deprecation")
public abstract class BaseTabActivity extends TabActivity implements
        OnClickListener {
    public abstract void initView();

    protected TabButton<Intent> current;

    public TabButton<Intent> getCurrent() {
        return current;
    }

    // 记录当前选中的按钮
    public void setCurrent(TabButton<Intent> current) {
        this.current = current;
    }

    // 按钮的集合
    private HashMap<String, TabButton<Intent>> buttons = new HashMap<String, TabButton<Intent>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initView();
    }

    // 通过View对象创建一个tab按钮
    public void addTabButtonByView(View v, Intent intent) {
        putToButtons(new TabButton<Intent>(v, this, intent));
    }

    // 通过View的id来创建一个tab按钮
    public void addTabButtonById(int id, Intent intent) {
        putToButtons(new TabButton<Intent>(findViewById(id), this, intent));
    }

    public void addTabButtonByView(View v, Class<?> cls) {
        putToButtons(new TabButton<Intent>(v, this, getIntent(cls)));
    }

    public void addTabButtonById(int id, Class<?> cls) {
        putToButtons(new TabButton<Intent>(findViewById(id), this,
                getIntent(cls)));
    }

    // 将tab按钮添加到按钮集合
    private void putToButtons(TabButton<Intent> tabButton) {
        buttons.put(tabButton.getIdStr(), tabButton);// 键值对保存每个按钮
        setTabContent(tabButton);
    }

    // 把每个view的id字符串 设为tab标签的标识，该标识跟集合中的key保持一直
    // 根据每个view点击传递的viewId来确定对应的内容
    protected void setTabContent(TabButton<Intent> button) {
        getTabHost().addTab(
                getTabHost().newTabSpec(button.getIdStr())
                        .setContent(button.getAction())
                        .setIndicator(button.getIdStr())

        );
    }

    private Intent getIntent(Class<?> cls) {
        return new Intent(getApplicationContext(), cls);
    }

    // 通过id设置选项卡
    protected void setTabById(int id) {
        String strId = String.valueOf(id);
        if (buttons.containsKey(strId)) {// 前提下必须初始化该id对应Button，否则切换不成功
            getTabHost().setCurrentTabByTag(strId);
            setCurrent(buttons.get(strId));
        }
    }

    // 通过View对象设置选项卡
    protected void setTabByView(View v) {
        String strId = String.valueOf(v.getId());
        if (buttons.containsKey(strId)) {
            getTabHost().setCurrentTabByTag(strId);
            setCurrent(buttons.get(strId));
        }
    }

    @SuppressWarnings("unchecked")
    /**
     *可以代替findViewById方法功能
     * @param viewId
     * @return
     */
    public <T extends View> T viewId(int viewId) {
        return (T) findViewById(viewId);
    }

    public void back(View v) {
        finish();
    }

}

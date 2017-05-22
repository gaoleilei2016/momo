package com.gdswww.library.tab;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseTabFragment extends FragmentActivity implements
		OnClickListener {
	private final int NULL_ID = -1;
	int containerId = NULL_ID;//容器的ID
	FragmentManager fragmentManager;
	Fragment currrentFragment;
	public int getContainerId() {
		return containerId;
	}
	public void setCurrentContent(Fragment f){
		this.currrentFragment = f;
	}
	public abstract void setContentId();
	
	public abstract void initView();
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentId();
		fragmentManager = getSupportFragmentManager();
		initView();
	}
	public HashMap<String, TabButton<Fragment>> getButtons() {
		return buttons;
	}

	public void setButtons(HashMap<String, TabButton<Fragment>> buttons) {
		this.buttons = buttons;
	}

	private HashMap<String, TabButton<Fragment>> buttons = new HashMap<String,  TabButton<Fragment>>();

	//通过View对象创建一个tab按钮
	public void addTabButtonByView(View v, Fragment fragment) {
		putToButtons(new TabButton<Fragment>(v, this, fragment));
	}

	//通过View的id来创建一个tab按钮
	public void addTabButtonById(int id, Fragment fragment) {
		putToButtons(new TabButton<Fragment>(findViewById(id), this, fragment));
	}
	
	//将tab按钮添加到按钮集合
	private void putToButtons( TabButton<Fragment> tabButton){
		buttons.put(tabButton.getIdStr(), tabButton);
	}
	
	public void changeContent(View v){
		if(containerId == NULL_ID){
			throw new IllegalArgumentException("请提供正确的容器ID");
		}
		String strId = String.valueOf(v.getId());
		if(buttons.containsKey(strId)){
			setContent(buttons.get(strId));
		}
	}
	
	public void changeContent(int id){
		if(containerId == NULL_ID){
			throw new IllegalArgumentException("请提供正确的容器ID");
		}
		String strId = String.valueOf(id);
		if(buttons.containsKey(strId)){
			setContent(buttons.get(strId));
		}
	}
	
	public void setContent(TabButton<Fragment> button){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if(currrentFragment!=null){
			transaction.hide(currrentFragment);
		}
		Fragment fragment = button.getAction();
		if(!fragment.isAdded()){
			transaction.add(containerId, fragment);
		}
		transaction.show(fragment).commit();
		setCurrentContent(fragment);
	}

}

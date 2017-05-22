package com.gdswww.library.tab;
import android.view.View;
import android.view.View.OnClickListener;

public final class TabButton<T> {
	protected View tabButton;
	private T action;//可能是一个Intent或者一个Fragment
	public T getAction() {
		return this.action;
	}
	public void setAction(T action) {
		this.action = action;
	}
	public String getIdStr(){
		return String.valueOf(tabButton.getId());
	}
	public View getTabButton() {
		return tabButton;
	}
	public void setTabButton(View tabButton) {
		this.tabButton = tabButton;
	}
	public OnClickListener getOnClickListener() {
		return onClickListener;
	}
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
	private OnClickListener onClickListener;
	
	public TabButton(View tabButton,OnClickListener onClickListener,T action){
		this.tabButton = tabButton;
		this.onClickListener = onClickListener;
		this.action = action;
		this.tabButton.setOnClickListener(onClickListener);
	}
}

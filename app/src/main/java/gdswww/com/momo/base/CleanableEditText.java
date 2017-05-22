package gdswww.com.momo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * 在焦点变化时和输入内容发生变化时均要判断是否显示右边clean图标
 */
public class CleanableEditText extends EditText {
	private Drawable mRightDrawable;
	private boolean isHasFocus;

	public CleanableEditText(Context context) {
		super(context);
		init();
	}

	public CleanableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CleanableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// getCompoundDrawables:
		// Returns drawables for the left, top, right, and bottom borders.
		Drawable[] drawables = this.getCompoundDrawables();
		// 取得right位置的Drawable
		// 即我们在布局文件中设置的android:drawableRight
		mRightDrawable = drawables[2];
		// 设置焦点变化的监听
		this.setOnFocusChangeListener(new FocusChangeListenerImpl());
		// 设置EditText文字变化的监听
		this.addTextChangedListener(new TextWatcherImpl());
		// 初始化时让右边clean图标不可见
		setClearDrawableVisible(false);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			boolean isClean = (event.getX() > (getWidth() - getTotalPaddingRight()))
					&& (event.getX() < (getWidth() - getPaddingRight()));
			if (isClean) {
				setText("");
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	private class FocusChangeListenerImpl implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			isHasFocus = hasFocus;
			if (isHasFocus) {
				boolean isVisible = getText().toString().length() >= 1;
				setClearDrawableVisible(isVisible);
			} else {
				setClearDrawableVisible(false);
			}
		}

	}

	// 当输入结束后判断是否显示右边clean的图标
	private class TextWatcherImpl implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			setClearDrawableVisible(getText().toString().trim().length() > 0);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

	}

	// 隐藏或者显示右边clean的图标
	protected void setClearDrawableVisible(boolean isVisible) {
		Drawable rightDrawable;
		if (isVisible) {
			rightDrawable = mRightDrawable;
		} else {
			rightDrawable = null;
		}
		// 使用代码设置该控件left, top, right, and bottom处的图标
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], rightDrawable,
				getCompoundDrawables()[3]);
	}

}
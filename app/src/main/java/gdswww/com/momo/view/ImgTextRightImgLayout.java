//package gdswww.com.momo.view;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
///**
// * Created by Administrator on 2017/5/19 0019.
// */
//
//public class ImgTextRightImgLayout extends RelativeLayout {
//    Button leftButton;
//    TextView titleTextView;
//    Button rightButton;
//    LayoutParams mLeftLayoutParams, mCenterLayoutParams, mRightLayoutParams;
//    int titleLeftTextColor = 0;
//    int titleLeftBackground = 0;
//    int titleLeftText = 0;
//    int titleRightTextColor = 0;
//    int titleRightBackground = 0;
//    int titleRightText = 0;
//    int titleTextColor = 0;
//    int titleTextSize = 0;
//    String title = "";
//
//    public ImgTextRightImgLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    public ImgTextRightImgLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
////        addView();
//    }
//
//    public ImgTextRightImgLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public ImgTextRightImgLayout(Context context) {
//        super(context);
//    }
//
//    private void initView() {
//         leftButton = new Button(getContext());
//         titleTextView = new TextView(getContext());
//         rightButton = new Button(getContext());
//         
//         leftButton.setTextColor(titleLeftTextColor);
//         leftButton.setBackgroundDrawable(titleLeftBackground);
//         leftButton.setText(titleLeftText);
//         
//         rightButton.setTextColor(titleRightTextColor);
//         rightButton.setBackgroundDrawable(titleRightBackground);
//         rightButton.setText(titleRightText);
//         
//         titleTextView.setText(title);
//         titleTextView.setTextSize(titleTextSize);
//         titleTextView.setTextColor(titleTextColor);
//         
//         mLeftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.MATCH_PARENT);
//         mLeftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//         addView(leftButton, mLeftLayoutParams);
//         
//         mCenterLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.MATCH_PARENT);
//         mCenterLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//         addView(titleTextView, mCenterLayoutParams);
//         
//         mRightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.MATCH_PARENT);
//         mRightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//         addView(rightButton, mRightLayoutParams);
//    }
//}

package com.gdswww.library.toolkit;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.Editable;
import android.widget.EditText;
public class TextUtil {
	/**
	 * 检查内容是否为空
	 * 
	 */
	public static boolean checkIsEmpty(String text) {
		if (text == null || text.trim().equals("")) {
			return true;
		}
		return false;
	}
	/**
	 * 检测是否有内容输入
	 * @param et
	 * @return
	 */
	public static boolean checkIsInput(EditText et){
		if (et == null || getEditText(et).equals("")) {
			return false;
		}
		return true;
	}
	/**
	 * 判断是否为手机号码
	 * @param mobiles
	 * @return true为手机号码  否则手机号码是不合法的
	 */
	public static boolean isMobileNumber(String mobiles){
		if(mobiles==null || mobiles.trim().equals("")){
			return false;
		}
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static boolean isEmail(String strEmail) {
		String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}
	/**
	 * 检测是否为姓名
	 * @param name
	 * @return 如果包含非法字符 返回false 否则为真 
	 */
	public static boolean  isPersonName(String name) {
		if(name == null || name.trim().equals("") ){
			return false;
		}
		if(Pattern.matches("(([\u4E00-\u9FA5]{2,15}))",name)){
			return true;
		}
		return false;
	}
	
	/**
	 * 检测是否为中文
	 * @param name
	 * @return 如果包含非法字符 返回false 否则为真 
	 */
	public static boolean  isAllChinese(String cn) {
		if(cn == null || cn.trim().equals("") ){
			return false;
		}
		if(Pattern.matches("(([\u4E00-\u9FA5]{1,10}))",cn)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取输入框的内容
	 * @param e
	 * @return
	 */
	public static String getEditText(EditText et){
		return et == null ?"":et.getText().toString().trim();
	}
	
	/**
	 * 文本如果为空范围空串
	 * @param text
	 * @return
	 */
	public static String textNullToString(String text){
		return text == null ? "" : text;
	}
	
	public static String getTextByEditable(Editable e){
		return e == null ? "" : e.toString().trim();
	}
	public static long getLength(String str) {
		if (str == null || str.equals(""))
			return 0;
		byte[] b = null;
		try {
			b = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (b != null)
			return b.length;

		return 0;
	}
	

	
	
}

package com.gdswww.library.toolkit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * 字符串工具类
 */
public class StrUtil {
	private static StringBuffer sb=new StringBuffer();
	/**
	 * 字符串的附加
	 * @param args
	 * @return 附加后的字符串
	 */
	public static String appendString(String...args){
		if(args==null || args.length<1){
			return "";
		}
		sb.setLength(0);
		for(int i=0;i<args.length;i++){
			sb.append(args[i]);
		}
		return sb.toString();
	}
	/**
	 * 字符串的附加
	 * @param args
	 * @return 附加后的字符串
	 */
	public static String appendString(Object...args){
		if(args==null || args.length<1){
			return "";
		}
		sb.setLength(0);
		for(int i=0;i<args.length;i++){
			sb.append(args[i]);
		}
		return sb.toString();
	}

	public static int stringToNum(String s){
		int num = 0;
		try {
			num = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * 格式化数字为int
	 * 
	 * @param v
	 * @return
	 */
	public static int nullToInt(Object vStr) {
		int str = 0;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Integer.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为int
	 * 
	 * @param v
	 * @return
	 */
	public static Long nullToLong(Object vStr) {
		Long str = 0L;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Long.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为double
	 * 
	 * @param v
	 * @return
	 */
	public static Double nullToDouble(Object vStr) {
		Double str = 0.00;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Double.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为Boolean
	 * 
	 * @param v
	 * @return
	 */
	public static boolean nullToBoolean(Object vStr) {
		boolean str = false;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Boolean.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 假如obj对象 是null返回""
	 * 
	 * @param obj
	 * @return
	 */
	public static String nullToStr(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}

	public static int StringToInt(String s) {
		int tmp = 0;
		if (s == null)
			return 0;
		try {
			tmp = Integer.parseInt(s);
		} catch (Exception e) {
			tmp = 0;
		}
		return tmp;
	}

	public static float StringToFloat(String s) {
		float tmp = 0;
		if (s == null)
			return 0;
		try {
			tmp = Float.parseFloat(s);
		} catch (Exception e) {
			tmp = 0;
		}
		return tmp;
	}

	/**
	 * 把字符串转换成BigDecimal,并用format进行格式化操作
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal formatBigDecimal(Number bd, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return new BigDecimal(df.format(bd));
	}

	/**
	 * 把字符串转换成BigDecimal
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal nullToBigDecimal(Object obj) {
		if ("".equals(StrUtil.nullToStr(obj))) {
			obj = "0.00";
		}
		BigDecimal bd = new BigDecimal(obj.toString());
		return bd;
	}

	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static String decode(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	public static Date getDate(String dateStr, String format) {
		if (dateStr==null || dateStr.trim().equals("")) {
			return null;
		}
		if (format==null || format.trim().equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.getDefault());
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}


	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}


	/**
	 * 对象转浮点
	 *
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static double toDouble(String obj) {
		try {
			return Double.parseDouble(obj);
		} catch (Exception e) {
		}
		return 0;
	}
	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}
	// 解压缩   
	public static String uncompress(InputStream str) throws IOException {   
		if (str == null ) {   
			return "";   
		}   
		ByteArrayOutputStream out = new ByteArrayOutputStream();   

		GZIPInputStream gunzip = new GZIPInputStream(str);   
		byte[] buffer = new byte[256];   
		int n;   
		while ((n = gunzip.read(buffer))>= 0) {   
			out.write(buffer, 0, n);   
		}   
		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)   
		return out.toString("utf-8");   
	}  
	//M5D加密
	public  static String MD5(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final double EARTH_RADIUS = 6378137.0;  
	public static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
}

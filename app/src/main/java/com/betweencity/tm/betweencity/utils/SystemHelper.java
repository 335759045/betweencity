package com.betweencity.tm.betweencity.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.jessyan.autosize.utils.LogUtils;

import static android.content.Context.TELEPHONY_SERVICE;


/**
 * 获取系统信息的工具类
 */
public class SystemHelper {
	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new Gson();
		}
	}

	/**
	 * TabLayout线条长度调整
	 * @param tabs
	 * @param leftDip
	 * @param rightDip
	 */
//	public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
//		Class<?> tabLayout = tabs.getClass();
//		Field tabStrip = null;
//		try {
//			tabStrip = tabLayout.getDeclaredField("mTabStrip");
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}
//
//		tabStrip.setAccessible(true);
//		LinearLayout llTab = null;
//		try {
//			llTab = (LinearLayout) tabStrip.get(tabs);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//
//		int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
//		int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
//
//		for (int i = 0; i < llTab.getChildCount(); i++) {
//			View child = llTab.getChildAt(i);
//			child.setPadding(0, 0, 0, 0);
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//			params.leftMargin = left;
//			params.rightMargin = right;
//			child.setLayoutParams(params);
//			child.invalidate();
//		}
//
//	}
	/**
	 * 获取当前机器的屏幕信息对�?br/> 另外：�?过android.os.Build类可以获取当前系统的相关信息
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getScreenInfo(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		// dm.widthPixels;//寬度
		// dm.heightPixels; //高度
		// dm.density; //密度
		return dm;
	}
	/**
	 * �?��当前的网络连接是否可�?br/> 注意：需要添加权�?lt;uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		boolean flag = false;
		try {
			ConnectivityManager connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != connManager) {
				NetworkInfo info = connManager.getActiveNetworkInfo();
				if (null != info && info.isAvailable()) {
					flag = true;
				}
			}
		} catch (Exception e) {
			Log.e("NetworkInfo", "Exception", e);
		}
		return flag;
	}

	/**
	 * �?��当前网络连接的类�?br/> 注意：需要添加权�?<uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE"/>;
	 * 
	 * @param context
	 * @return 返回0代表GPRS网络;返回1,代表WIFI网络;返回-1代表网络不可�?
	 */
	public static int getNetworkType(Context context) {
		int code = -1;
		try {
			ConnectivityManager connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != connManager) {
				State state = connManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState();
				if (State.CONNECTED == state) {
					code = ConnectivityManager.TYPE_WIFI;
				} else {
					state = connManager.getNetworkInfo(
							ConnectivityManager.TYPE_MOBILE).getState();
					if (State.CONNECTED == state) {
						code = ConnectivityManager.TYPE_MOBILE;
					}
				}
			}
		} catch (Exception e) {
			Log.e("NetworkInfo", "Exception", e);
		}
		return code;
	}

	/**
	 * 返回当前程序版本代码,�?1
	 * 
	 * @param context
	 * @return 当前程序版本代码
	 */
	public static int getAppVersionCode(Context context) {
		int versionCode = -1;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;

		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionCode;
	}

	/**
	 * 返回当前程序版本�?�?1.0.1
	 * 
	 * @param context
	 * @return 当前程序版本�?
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;

		} catch (Exception e) {
			// Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	/**
	 * 判断GPS是否支持
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasGPSDevice(Context context) {
		final LocationManager mgr = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (mgr == null)
			return false;
		final List<String> providers = mgr.getAllProviders();
		if (providers == null)
			return false;
		return providers.contains(LocationManager.GPS_PROVIDER);
	}

	/**
	 * �?��，关闭GPS
	 * 
	 * @param context
	 */
	public static void setGpsStatus(Context context, boolean enabled) {
		Secure.setLocationProviderEnabled(context.getContentResolver(),
				LocationManager.GPS_PROVIDER, enabled);
	}

	/**
	 * 判断GPS是否�?��，GPS或�?AGPS�?���?��就认为是�?���?
	 * 
	 * @param context
	 * @return true 表示�?��
	 */
	public static boolean getGpsState(Context context) {
		ContentResolver resolver = context.getContentResolver();
		boolean open = Secure.isLocationProviderEnabled(resolver,
				LocationManager.GPS_PROVIDER);
		return open;
	}

	/**
	 * 判断为手机号
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;
		/*
		 * 可接受的电话格式有：
		 */
		String expression = "[1][3456789]\\d{9}";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
	/**
	 * 判断邮箱是否合法
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if (null==email || "".equals(email)) return false;
		//Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 计算地球两点间的距离
	 * 
	 * @param longt1
	 * @param lat1
	 * @param longt2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double longt1, double lat1, double longt2,
			double lat2) {
		double x, y, distance;
		x = (longt2 - longt1) * 3.14159265358979323 * 6371229
				* Math.cos(((lat1 + lat2) / 2) * 3.14159265358979323 / 180)
				/ 180;
		y = (lat2 - lat1) * 3.14159265358979323 * 6371229 / 180;
		distance = Math.hypot(x, y);
		return distance;
	}

	/**
	 * double相减
	 * @param d1
	 * @param d2
     * @return
     */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}
	/**
	 * double相加
	 * @param
	 * @param
	 * @return
	 */
	public static double add(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * double相乘
	 * @paramd1
	 * @paramd2
	 * @return
	 */
	public static double mul(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1,double v2,int scale){
		if(scale<0){
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@SuppressLint("SimpleDateFormat")

	/**
	 * String转long
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
	 * 根据时间计算年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
     */
	public static int getAge(Date birthDay) throws Exception
	{
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay))
		{
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth)
		{
			if (monthNow == monthBirth)
			{
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			}
			else
			{
				age--;
			}
		}
		return age;
	}
	//十三位数
	public static String timestamp2Date(String str_num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date(toLong(str_num)));
			return date;
	}
	//十位数
	public static String timesOne(String time) {
		SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		@SuppressWarnings("unused")
		long lcc = Long.valueOf(time);
		String times = sdr.format(new Date(lcc * 1000L));
		return times;
	}
	//十位数时间戳转字符串
	public static String timesOne2(String time) {
		SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日");
		@SuppressWarnings("unused")
		long lcc = Long.valueOf(time);
		String times = sdr.format(new Date(lcc * 1000L));
		return times;
	}
	//string类型转换为date类型
	public static Date stringToDate(String strTime)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}
	// 将字符串转为时间�?

	@SuppressLint("SimpleDateFormat")
	public static String getTime(String user_time) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d;
		try {

			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}
	@SuppressLint("SimpleDateFormat")
	public static String getDate(String user_time) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {

			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}
	@SuppressLint("SimpleDateFormat")
	public static String zhtDate1(String user_time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		  
		String sd = sdf.format(new Date(Long.parseLong(user_time)));
		return sd;
	}
	@SuppressLint("SimpleDateFormat")
	public static String zhtDate2(String user_time) {
		SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
		@SuppressWarnings("unused")
		long lcc = Long.valueOf(user_time);
		String times = sdr.format(new Date(lcc * 1000L));
		return times;
	}
	public static final void showResultDialog(Context context, String msg,
                                              String title) {
		if(msg == null) return;
		String rmsg = msg.replace(",", "\n");
		Log.d("Util", rmsg);
		new AlertDialog.Builder(context).setTitle(title).setMessage(rmsg)
				.setNegativeButton("知道了", null).create().show();
	}

	@SuppressWarnings("unused")
	public static void doStartApplicationWithPackageName(String packagename, Context context, String url) {

	    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
	    PackageInfo packageinfo = null;
	    try {
	        packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
	    } catch (NameNotFoundException e) {
	        e.printStackTrace();
	    }
	    if (packageinfo == null) {
	    	Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
	    		    context.startActivity(viewIntent);
	        return;
	    }

	    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
	    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
	    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	    resolveIntent.setPackage(packageinfo.packageName);

	    // 通过getPackageManager()的queryIntentActivities方法遍历
	    List<ResolveInfo> resolveinfoList = context.getPackageManager()
	            .queryIntentActivities(resolveIntent, 0);

	    ResolveInfo resolveinfo = resolveinfoList.iterator().next();
	    if (resolveinfo != null) {
	        // packagename = 参数packname
	        String packageName = resolveinfo.activityInfo.packageName;
	        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
	        String className = resolveinfo.activityInfo.name;
	        // LAUNCHER Intent
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);

	        // 设置ComponentName参数1:packagename参数2:MainActivity路径
	        ComponentName cn = new ComponentName(packageName, className);

	        intent.setComponent(cn);
	        context.startActivity(intent);
	    }
	}


	/**
	 * 将Bitmap转换成字符串
	 * @param bitmap
	 * @return
     */
	public static String bitmaptoString(Bitmap bitmap) {
		String string = null;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
		byte[] bytes = bStream.toByteArray();
		string = Base64.encodeToString(bytes, Base64.DEFAULT);
		return string;
	}
//	/**
//	 * 检查取得权限
//	 */
//	public static boolean checkPermissions(PermissionsHelper permissionsHelper, String[]  permissions) {
//		if (permissionsHelper.checkAllPermissions(permissions)) {
//			permissionsHelper.onDestroy();
//			return true;
//		} else {
//			//申请权限
//			permissionsHelper.startRequestNeedPermissions();
//		}
//		return false;
//	}
	/**
	 * 将Bitmap转换成文件
	 * 保存文件
	 * @param bm
	 * @param fileName
	 */
	public static File saveFile(Bitmap bm, String path, String fileName) throws IOException {
		File dirFile = new File(path);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		File myCaptureFile = new File(path , fileName);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		bos.flush();
		bos.close();
		return myCaptureFile;
	}

//	/**
//	 * TabLayout线条长度调整
//	 * @param tabs
//	 * @param leftDip
//	 * @param rightDip
//     */
//	public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
//		Class<?> tabLayout = tabs.getClass();
//		Field tabStrip = null;
//		try {
//			tabStrip = tabLayout.getDeclaredField("mTabStrip");
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}
//
//		tabStrip.setAccessible(true);
//		LinearLayout llTab = null;
//		try {
//			llTab = (LinearLayout) tabStrip.get(tabs);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//
//		int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
//		int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
//
//		for (int i = 0; i < llTab.getChildCount(); i++) {
//			View child = llTab.getChildAt(i);
//			child.setPadding(0, 0, 0, 0);
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//			params.leftMargin = left;
//			params.rightMargin = right;
//			child.setLayoutParams(params);
//			child.invalidate();
//		}
//
//	}

	/**
	 * 获取当前时间
	 * @return
     */
	public static String getNowDate(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	/**
	 * 获取手机唯一标示
	 * @return
	 */
	public static String getphoneUUID(Context context){
		TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
	}
	/**
	 * 获取当前毫秒时间戳
	 *
	 * @return 毫秒时间戳
	 */
	public static long getNowTimeMills() {
		return System.currentTimeMillis();
	}
	/*
* Java文件操作 获取文件扩展名
* */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot >-1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	public static int getFileType(String mimeType) {
		switch (mimeType) {
			case "png":
			case "PNG":
			case "jpeg":
			case "JPEG":
			case "webp":
			case "WEBP":
			case "gif":
			case "GIF":
				return 1;
			case "3gp":
			case "3gpp":
			case "avi":
			case "mp4":
			case "x-msvideo":
				return 2;
		}
		return 1;
	}
	/**
	 * 获取六位数的随机数
	 * @param number
	 * @return
	 */
	public static String randNumber(int number){

		String strRand="" ;
		for(int i=0;i<number;i++){
			strRand += String.valueOf((int)(Math.random() * 10)) ;
		}
		return strRand;
	}
	public static Boolean isIDCard(String idcard){
		boolean isCard = false;
		String expression = "/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}[\\dx]$)/i";
		CharSequence inputStr = idcard;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches()) {
			isCard = true;
		}
		return isCard;
	}

	/**
	 * 设置View的margins
	 * @param v
	 * @param l
	 * @param t
	 * @param r
	 * @param b
	 */
	public static void setMargins (View v, int l, int t, int r, int b) {
		if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
			p.setMargins(l, t, r, b);
			v.requestLayout();
		}
	}
	/**
	 * View渐隐动画效果
	 */
	public static void setHideAnimation(View view, int duration)
	{
		AlphaAnimation mHideAnimation = null;
		if (null == view || duration < 0)
		{
			return;
		}

		if (null != mHideAnimation)
		{
			mHideAnimation.cancel();
		}
		// 监听动画结束的操作
		mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
		mHideAnimation.setDuration(duration);
		mHideAnimation.setFillAfter(true);
		view.startAnimation(mHideAnimation);
	}
	/**
	 * Desc: 获取虚拟按键高度 放到工具类里面直接调用即可
	 */
	public static int getNavigationBarHeight(Context context) {
		int result = 0;
		if (hasNavBar(context)) {
			Resources res = context.getResources();
			int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = res.getDimensionPixelSize(resourceId);
			}
		}
		LogUtils.e("虚拟键盘高度"+result);
		return result;
	}

	/**
	 * 检查是否存在虚拟按键栏
	 *
	 * @param context
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public static boolean hasNavBar(Context context) {
		Resources res = context.getResources();
		int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
		if (resourceId != 0) {
			boolean hasNav = res.getBoolean(resourceId);
			// check override flag
			String sNavBarOverride = getNavBarOverride();
			if ("1".equals(sNavBarOverride)) {
				hasNav = false;
			} else if ("0".equals(sNavBarOverride)) {
				hasNav = true;
			}
			return hasNav;
		} else { // fallback
			return !ViewConfiguration.get(context).hasPermanentMenuKey();
		}
	}

	/**
	 * 判断虚拟按键栏是否重写
	 *
	 * @return
	 */
	private static String getNavBarOverride() {
		String sNavBarOverride = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			try {
				Class c = Class.forName("android.os.SystemProperties");
				Method m = c.getDeclaredMethod("get", String.class);
				m.setAccessible(true);
				sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
			} catch (Throwable e) {
			}
		}
		return sNavBarOverride;
	}
}

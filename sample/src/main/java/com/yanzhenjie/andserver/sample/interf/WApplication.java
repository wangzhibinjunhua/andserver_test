package com.yanzhenjie.andserver.sample.interf;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.util.ArrayList;
import java.util.List;


import com.yanzhenjie.andserver.sample.util.LogUtil;
import com.yanzhenjie.andserver.sample.util.SharedPreferencesUtil;


/**
 * @author wzb<wangzhibin_x@qq.com>
 * @date May 8, 2017 2:54:00 PM
 */
public class WApplication extends Application {

	/**
	 * 全局上下文环境.
	 */
	public static Context CONTEXT;
	/**
	 * SP读写工具.
	 */
	public static SharedPreferencesUtil sp;
	/**
	 * 用户信息.
	 */
	// public static UserBean user;
	/**
	 * 文件根目录
	 */
	public static final String BASE_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
			+ "/andserver/";
	/**
	 * 图片文件目录
	 */
	public static final String IMAGE_FILE_PATH = BASE_FILE_PATH + "image/";

	/**
	 * 保存全部activity,便于管理
	 */
	public static List<Activity> activityList = new ArrayList<Activity>();

	public static boolean isNetWork = true;

	/**
	 * SP文件名.
	 */
	private final String SP_NAME = "andserver";

	public static SQLiteDatabase db;

	public static int USER_LEVEL = 0;


	public static SharedPreferencesUtil sp_ext;

	@Override
	public void onCreate() {
		super.onCreate();

		CONTEXT = getApplicationContext();
		sp = new SharedPreferencesUtil(SP_NAME, SharedPreferencesUtil.PRIVATE, CONTEXT);
		init_ext_sp();
		LogUtil.openLog(); // 正式发布请注释此程序语句.

	}


	private void init_ext_sp(){
		Context c = null;
		int MODE = SharedPreferencesUtil.OTHER_READ_AND_WRITE;
		try {
			c = CONTEXT.createPackageContext("com.wzb.setting", Context.CONTEXT_IGNORE_SECURITY);

		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sp_ext = new SharedPreferencesUtil("setting", MODE,c);
	}
	




}

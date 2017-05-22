package com.gdswww.library.toolkit;

import android.app.Activity;
import android.content.Context;

import java.io.PrintWriter;
import java.io.StringWriter;

public class UncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
	private final Context myContext;

	public UncaughtExceptionHandler(Context context) {
		myContext = context;
	}
	public void uncaughtException(Thread thread, Throwable exception) {
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		System.err.println(stackTrace);
		if (myContext instanceof Activity) {
			((Activity)myContext).finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid()); 
		System.exit(10);
	}
}
package com.haoche666.buyer.photoview;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(23)
public class SDK16 {

	public static void postOnAnimation(View view, Runnable r) {
		view.postOnAnimation(r);
	}
	
}

package com.duckduckgo.mobile.android;

import com.duckduckgo.mobile.android.download.FileCache;
import com.duckduckgo.mobile.android.download.ImageCache;
import com.duckduckgo.mobile.android.download.ImageDownloader;
import com.duckduckgo.mobile.android.network.DDGNetworkConstants;
import com.duckduckgo.mobile.android.util.DDGControlVar;
import com.duckduckgo.mobile.android.util.SCREEN;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DDGApplication extends Application {

	private static final ImageCache imageCache = new ImageCache(null);
	private static final ImageDownloader imageDownloader = new ImageDownloader(imageCache);
	private static SharedPreferences sharedPreferences = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		imageCache.setFileCache(new FileCache(this.getApplicationContext()));
		
		DDGNetworkConstants.initialize();
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		DDGControlVar.START_SCREEN = SCREEN.getByCode(Integer.valueOf(sharedPreferences.getString("startScreenPref", "0")));
     
	}
	
	public static ImageDownloader getImageDownloader() {
		return imageDownloader;
	}
	
	public static SharedPreferences getSharedPreferences() {
		return sharedPreferences;
	}
}
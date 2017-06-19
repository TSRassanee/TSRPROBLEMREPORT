package com.tsr.tsrproblemreport.activity;

import android.content.Context;
import android.content.SharedPreferences;

public class ManageSharedPreferences {
	
	
	public static String LoadPreferences(Context ctx, String filenamepre, String key){
		String strSavedMem1;
		
    	SharedPreferences sharedPreferences = ( ctx).getSharedPreferences(filenamepre, 0);
    	strSavedMem1 = sharedPreferences.getString(key, "");
    	return strSavedMem1;
    }
	
	public static void SavePreferences(Context ctx, String filenamepre, String key, String value) {
	
		SharedPreferences sharedPreferences = ( ctx).getSharedPreferences(filenamepre, 0);;
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

}

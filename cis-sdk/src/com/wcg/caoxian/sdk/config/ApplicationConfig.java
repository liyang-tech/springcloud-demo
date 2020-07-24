package com.wcg.caoxian.sdk.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.wcg.caoxian.sdk.exception.ErrorHandler;


public class ApplicationConfig {

	@SuppressWarnings("unused")
	private static String sdk_result_hasThrowable = "sdk.result.hasThrowable";
	
	private static ApplicationConfig instance = null;
	
	Properties properties = new Properties();
	
	public static synchronized ApplicationConfig getInstance(){
		if(instance == null){
			instance = new ApplicationConfig();
		}
		return instance;
	}
	
	private ApplicationConfig(){
		try {
			InputStream publicPro = ApplicationConfig.class.getResourceAsStream("/application.yml");
			properties.load(new InputStreamReader(publicPro, "UTF-8"));
		} catch (IOException e) {
			ErrorHandler.reportError("10201", e);
		}
	}
	
	public String getValue(String key, String defVal){
		return properties.getProperty(key, defVal);
	}
	
	public String getValue(String key){
		return properties.getProperty(key);
	}
	
	public boolean hasThrowable(){
		/*String hasThrowable = properties.getProperty(ApplicationConfig.sdk_result_hasThrowable, "true");
		return "true".equalsIgnoreCase(hasThrowable);*/
		return false;
	}
	
}

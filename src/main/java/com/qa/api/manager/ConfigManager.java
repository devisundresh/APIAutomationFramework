package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
		
	//Properties class is from JAVA used to read the config properties file.
	
	private static Properties properties = new Properties();
	//No static block in Selenium multiple test cases will read same properties so ensure threads safety and ensure all threads are getting Thread copy.
	
	static { 
		
		//static block execute the moment class loaded before main method. The moment class loaded in class loader, static block get executed.
		//mvn clean install -Denv=qa/stage/dev/uat/prod (System Environment variables)
		//if env not given.. run QA by default
		
		String envName = System.getProperty("env", "prod");
		System.out.println("Running tests on env:" +envName);		
		String fileName = "config_"+ envName +".properties"; //config_stage.properties -> example
		
		InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName); 
		
		//InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties");//when use only one config file //Make connection between config.properties file and config manager class. //In selenium we read from FileInputSream
		//ConfigManager.class.getClassLoader() is reflection is faster than normal file input stream..
		
		if(input!=null) {
			try {
				properties.load(input);
				System.out.println(properties);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	} //Now static block available any where for any class in any time
	
	public static String get(String key) {
		return properties.getProperty(key).trim();
	}
	
	public static void set(String key, String value) {
		properties.setProperty(key, value.trim());
	}

}

package com.hellofresh.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	Properties prop = new Properties();
	InputStream inputConfig = null;
	
	public Config() {
		try {
			inputConfig = new FileInputStream("src/test/resources/properties/config.properties");
			// load a properties file
			prop.load(inputConfig);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getConfigProperty(String key) {
			return prop.getProperty(key).trim();
	}
}

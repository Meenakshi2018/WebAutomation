package com.hellofresh.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class TestData {
	Properties prop = new Properties();
	InputStream inputTest = null;

	public TestData() {
		try {
			inputTest = new FileInputStream("src/test/resources/properties/testdata.properties");
			// load a properties file
			prop.load(inputTest);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return prop.getProperty(key).trim();
		// this.configFile.getProperty(key);
	}
	
	public String getNewEmail() {
		String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        return email;
	}
}

package com.realme.configurations;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestConfigurations {
	private Properties prop;
	private InputStream file;
	
	public TestConfigurations() {
		prop=new Properties();
		String path=System.getProperty("user.dir");
		try {
			file=new FileInputStream(path+"/src/test/java/com/realme/configurations/config.properties");
			prop.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUrl() {
		return prop.getProperty("url");
	}
	
	public String getSearchTerm() {
		return prop.getProperty("searchTerm");
	}

}

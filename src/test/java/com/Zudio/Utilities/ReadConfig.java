package com.Zudio.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	String dir = System.getProperty("user.dir");
	String filePath = dir+"/Configuration/config.properties";
	Properties pro;
	
	public ReadConfig() {
		try {
			System.out.println(filePath);
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			pro = new Properties();
			pro.load(fis);
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	public String getUrl() {
		String url = pro.getProperty("url");
		return url;
	}
	
	public String getchromeDriverPath() {
		String driverPath = pro.getProperty("chromedriverPath");
		return driverPath;
	}
	
	public String getExcelFilePath() {
		String excelPath = pro.getProperty("ExcelFilePath");
		return excelPath;
	}

}

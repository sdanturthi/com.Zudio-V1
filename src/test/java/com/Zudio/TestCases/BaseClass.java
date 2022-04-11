package com.Zudio.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.Zudio.Utilities.ReadConfig;

public class BaseClass {
	
	WebDriver driver;
	ReadConfig rc = new ReadConfig();
	String urlpath = rc.getUrl();
	String cdPath = rc.getchromeDriverPath();
	
	@BeforeClass
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", cdPath);		
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void TearDown() {
		//driver.quit();
	}

}

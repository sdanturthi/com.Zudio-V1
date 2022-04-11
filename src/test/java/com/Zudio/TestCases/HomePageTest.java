package com.Zudio.TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Zudio.PageObjects.HomePage;

public class HomePageTest extends BaseClass {

	@Test
	public void TC001_zudioHomePage() throws InterruptedException, IOException {
		driver.get(urlpath);
		HomePage hp = new HomePage(driver);
		hp.clickStoreLocator();
		hp.clickStoreDropDown();
	}
}

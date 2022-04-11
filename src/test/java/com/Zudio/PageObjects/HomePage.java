package com.Zudio.PageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.Zudio.Utilities.ReadConfig;
import com.Zudio.Utilities.XLUtils;

public class HomePage {
	
	XLUtils excelUtility = new XLUtils();	
	ReadConfig rc = new ReadConfig();	
	
	WebDriver ldriver;
	
	Select selectDropDown;
	List<WebElement> stateOptions;
	int lengthStateOptions;

	Select cityDropDownList;
	List<WebElement> cityList;
	WebElement cityDropDown;	
	
	public HomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//a/span[text()='Zudio near you' and @class='site-nav__label']")
	WebElement storeLocator;

	public void clickStoreLocator() {
		storeLocator.click();
	}

	@FindBy(xpath = "//div/select[@id='store_state']")
	@CacheLookup
	WebElement stateDropDown;

	public void clickStoreDropDown() throws InterruptedException, IOException {
		
		String xlPath = System.getProperty("user.dir") + rc.getExcelFilePath();
		String xlSheet = "Sheet1";	
		
		
		stateDropDown.click();
		selectDropDown = new Select(stateDropDown);
		stateOptions = selectDropDown.getOptions();
		ArrayList<String> stateList = new ArrayList<String>();
		int ctrl = 0;
		for (WebElement state : stateOptions) {
			stateList.add(state.getText());
		}

		for (int i = 0; i < stateList.size(); i++) {

			if (ctrl > 0) {
				String xpathValue = "//div/select/option[@value=" + "'" + stateList.get(i) + "'" + "]";
				
				int lastRowValue = excelUtility.getLastRow(xlPath, xlSheet);
				
				excelUtility.setCellValue(xlPath, xlSheet, lastRowValue+1, 0, stateList.get(i));
				
				ldriver.findElement(By.xpath(xpathValue)).click();
				ArrayList<String> cities = new ArrayList<String>();
				Thread.sleep(2000);
				cityDropDown = ldriver.findElement(By.xpath("//div/select[@id='store_city']"));
				cityDropDownList = new Select(cityDropDown);
				cityList = cityDropDownList.getOptions();
				for (WebElement city : cityList) {
					cities.add(city.getText());
				}
				Thread.sleep(200);
				int citiesListLength = cities.size();
				int flag = 0;
				for (int j = 0; j < citiesListLength; j++) {
					
					if (flag > 0) {
						
						lastRowValue = excelUtility.getLastRow(xlPath, xlSheet);
						String xpathValue1 = "//div/select/option[@value=" + "'" + cities.get(j) + "'" + "]";
						excelUtility.setCellValue(xlPath, xlSheet, lastRowValue+1, 1, cities.get(j));
						ldriver.findElement(By.xpath(xpathValue1)).click();
						Thread.sleep(1000);
						WebElement storeDetailFromEachCity = ldriver
								.findElement(By.xpath("//div[@id='render_append']/header/h2/span"));
						String storeDetailValue = storeDetailFromEachCity.getText();
						System.out.println(storeDetailValue);			
						
						lastRowValue = excelUtility.getLastRow(xlPath, xlSheet);
						excelUtility.setCellValue(xlPath, xlSheet, lastRowValue, 2, storeDetailValue);
						
						
					}
					flag++;
				}
			}
			ctrl++;
		}

	}
}

package com.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;

import utility.CommonUtils;

public class Booking extends CommonUtils {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public void selectLocations() throws Exception {
		waitForElementToBeClickable(getElement("OriginCity"));
		getElement("OriginCity").sendKeys("bhu");
		waitForElementToBeVisible(getElement("OriginResponseDropdown"));
		Thread.sleep(3000);
		List<WebElement> origincities = getElements("originCitiesResults");
		
		for(WebElement elem : origincities) {
			waitForElementToBeClickable(elem);
			String s = elem.getText();
			if(elem.getText().contains("BHUBANESWAR")) {
				elem.click();
				break;}
		}
		
		// Destination city
		waitForElementToBeClickable(getElement("DestinationCity"));
		getElement("DestinationCity").sendKeys("pun");
		waitForElementToBeVisible(getElement("DestResponseDropdown"));
		Thread.sleep(2000);
		List<WebElement> destCities = getElements("destCitiesResults");
		
		for(WebElement elem : destCities) {
			waitForElementToBeClickable(elem);
			String s = elem.getText();
			if(elem.getText().contains("PUNE")) {
				elem.click();
				break;}
		}
	}
	public void findLowestPrice(String date) throws InterruptedException {
		Integer lowestPrice = Integer.MAX_VALUE;
		
		List<WebElement> lowestDate = null;
		List<WebElement> datePricecontainer = driver.findElements(By.xpath("((//div[contains(@class , 'ui-datepicker')])[1]//span[text() = '"+date.split(" ")[0]+" - 2025']/../../../../..)//td[contains(@id , 'spnselectdate')]"));
		for(WebElement eachDate : datePricecontainer) {
			Thread.sleep(3000);
			List <WebElement> priceAndDate1 =  eachDate.findElements(By.tagName("td"));
			int b = priceAndDate1.size();
			if(priceAndDate1.size() == 2) {
			String SpecPrice = priceAndDate1.get(1).getText().replace("₹", "").trim();
			if(!SpecPrice.isEmpty() && Integer.parseInt(SpecPrice) < lowestPrice ) {
				lowestPrice = Integer.parseInt(SpecPrice);
				lowestDate = priceAndDate1;
			}
			}
					
		}
		String SpecDate = lowestDate.get(0).getText();
		lowestDate.get(0).click();
	}
	
	public void selectDate(String date) throws Exception {
		SimpleDateFormat inputformat = new SimpleDateFormat("MMM yyyy");
		SimpleDateFormat outformat = new SimpleDateFormat("MM yyyy");
		
		SimpleDateFormat inputformat1 = new SimpleDateFormat("dd MMM");
		SimpleDateFormat outformat2 = new SimpleDateFormat("dd MM");
		String currMonthYear = getElement("CurrentDateText").getText();
		
		Date Date = inputformat.parse(date);
		String formattedDate = outformat.format(Date);
		
		Date Date2 = inputformat1.parse(currMonthYear.split(",")[1].trim());
		String currentMonthYear = outformat2.format(Date2);
		String currMonth = currentMonthYear.split(" ")[1];
		String givenMonth = formattedDate.split(" ")[0];
		
		Date currentDateTime = new Date();
		if(Date.before(currentDateTime)){
			throw new Exception("Can not parse a past date");
		}
		
//		js.executeScript("arguments[0].click();", getElement("DepartureDateDrop"));
		 getElement("CurrentDateText").click();
		 js.executeScript("window.scrollBy(0,100)");
		 waitForPageReloadToComplete();
		Integer var = Integer.parseInt(currMonth);
		
		while(true) {		
			if(Integer.parseInt(givenMonth) == var || Integer.parseInt(givenMonth) == var+1) { 
				findLowestPrice(date);
				break;
			}
			else if(Integer.parseInt(givenMonth) > Integer.parseInt(currMonth)) {
				CommonUtils.waitForElementToBeClickableLongWait(getElement("MoveArrow"));
				Thread.sleep(2000);
				getElement("MoveArrow").click();
				Thread.sleep(2000);
				var +=2;
			}
			
		}
	}
}

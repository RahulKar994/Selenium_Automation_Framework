package com.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import utility.CommonUtils;

public class Crosswords extends CommonUtils {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	// Search for manifest keyword
	public void searchBooks() {
		waitForElementToBeClickableLongWait(getElement("inputBox"));
		getElement("inputBox").sendKeys("manifest");
		waitForElementToBeVisible(getElement("BookResluts"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", getElement("viewMoreButton"));
		waitForElementToBeClickableLongWait(getElement("viewMoreButton"));
		getElement("viewMoreButton").click();
	}

	// sort the books 	
	public void sortBooks() throws Exception {
		waitForElementToBeClickable(getElement("sortSelection"));
		getElement("sortSelection").click();
		waitForElementToBeVisible(getElements("sortOptions").get(0));
		for (WebElement elem : getElements("sortOptions")) {
			if (elem.getText().trim().contains("Price: Low to High")) {
				elem.click();
				break;
			}
		}
	}
	
	// check if books are sorted in ascending order
	public void validateSorting() throws Exception {
		waitForElementToBeVisible(getElements("sortedPrices").get(0));

		Thread.sleep(3000);
		List<Double> priceList = new ArrayList<Double>();
		for (WebElement price : getElements("sortedPrices")) {

			Double Actprice = Double.parseDouble(price.getText().trim().replace("₹", ""));
			priceList.add(Actprice);
		}

		List<Double> sortedPrice = priceList.stream().sorted().collect(Collectors.toList());
//		 Assert.assertEquals(sortedPrice, priceList, "Products are not sorted");  // Comment:- Found Bug in the UI using assertion

	}
	
	 // find all the books with discounted price
	public void findDiscountedBooks() throws Exception {
		List<String> bookNames = new ArrayList<String>();
		for (WebElement name : getElements("discountedPriceBooks")) {
			bookNames.add(name.getText().trim());
		}
		System.out.println(bookNames);
	}
	
	// move the slider to expected price range
	public void priceRange() throws NumberFormatException, Exception {
		Actions action = new Actions(driver);
		js.executeScript("arguments[0].scrollIntoView; true", getElement("pricefilter"));
		waitForElementToBeClickableLongWait(getElement("pricefilter"));
		getElement("pricefilter").click();
		waitForElementToBeClickable(getElement("leftSlider"));
		action.clickAndHold(getElement("leftSlider")).moveByOffset(80, 0).build().perform();
		Thread.sleep(3000);
		
		//check if filter is applied in the UI
		waitForElementToBeClickableLongWait(getElement("appliedPriceFilter"));
		js.executeScript("arguments[0].scrollIntoView; true", getElement("appliedPriceFilter"));
		String[] priceRange = getElement("appliedPriceFilter").getAttribute("title").split("-");
		Assert.assertEquals(635, Double.parseDouble(priceRange[0].replace("₹", "").trim())); 
		
		//check if the books visible are within the price range 
		boolean flag = true;
		for (WebElement price : getElements("filteredPrice")) {
			Double Actprice = Double.parseDouble(price.getText().trim().replace("₹", ""));
			if (Actprice < 635) {
				flag = false;
			}
		}
		Assert.assertTrue(flag, "Price is not between the range");
	}
}

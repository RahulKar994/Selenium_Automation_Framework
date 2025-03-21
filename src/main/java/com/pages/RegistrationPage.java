package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.CommonUtils;

public class RegistrationPage extends CommonUtils{
	
	public static WebDriver driver = CommonUtils.driver;
	
	public RegistrationPage(WebDriver driver) {
		RegistrationPage.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public RegistrationPage() {
		this(driver);
	}

	@FindBy(xpath = "//span[text() = 'My Account']")
	WebElement MyAccountButton;
	@FindBy(xpath = "//span[text() = 'My Account']/following::ul[contains(@class, 'dropdown-menu')]")
	WebElement MyAccountButtonDropDown;
	@FindBy(xpath = "//a[text() = 'Register']/parent::li")
	WebElement RegisterButton;	
	@FindBy(xpath = "//*[@id = 'content']//h1")
	WebElement ContentHeading;
	@FindBy(xpath = "//input[@name = 'firstname']")
	WebElement RegistrationFirstName;
	@FindBy(xpath = "//input[@name = 'lastname']")
	WebElement RegistrationLastName;
	@FindBy(xpath = "//input[@name = 'email']")
	WebElement RegistrationEmail;
	@FindBy(xpath = "//input[@name = 'password']")
	WebElement password;
	@FindBy(xpath = "//input[@name = 'telephone']")
	WebElement phone;
	@FindBy(xpath = "//input[@name = 'confirm']")
	WebElement confirmPassword;
	@FindBy(xpath = "//input[@name = 'agree']")
	WebElement terms;
	@FindBy(xpath = "//input[@value = 'Continue']")
	WebElement contnueButton;
	
	String [][] productDetails = {
			{"MacBook", "2"},{"iphone","3"},{"imac", "4"}	
			};
	
	public void handleShadowDom() throws InterruptedException {
		Thread.sleep(4000);
		waitForElementToBeVisible(getElement("ShadowDomContainer"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement shadowElem = (WebElement) js.executeScript("return arguments[0].shadowRoot", getElement("ShadowDomContainer"));
		WebElement iframe1 =  shadowElem.findElement(By.tagName("iframe"));
	}
	
	public void addMultipleProducts() throws Exception {
			for(String[] products : productDetails) {
				waitForElementToBeClickable(getElement("searchBox"));
				getElement("searchBox").sendKeys(products[0], Keys.ENTER);
				waitForElementToBeVisible(getElements("searchedProds").get(0));
				
				for(WebElement elem : getElements("searchedProds")) {
					if(elem.getText().equals(products[0])) {
						elem.click();
						
						// locate quantity
						waitForElementToBeClickable(getElement("quantityTextBox"));
						getElement("quantityTextBox").sendKeys(products[1]);
						waitForElementToBeClickable(getElement("addToCart"));
						getElement("addToCart").click();
						Assert.assertTrue(getElement("successMsg").isDisplayed());
						getElement("homePage").click();
						waitForPageReloadToComplete();
						break;
					}else
						throw new Exception("product is not present");
				}
			}	
		
	}
	
	public void validateCartPage() throws Exception {
		getElement("cartIcon").click();
		waitForPageReloadToComplete();
		getElements("cartProducts");
		
		
	}
	
	public void registerInAccount() {
		CommonUtils.waitForPageReloadToComplete();
		CommonUtils.waitForElementToBeClickableLongWait(getElement("MyAccount"));
		if(isElementPresent("//p[text() = '']")) {
			
		}
		MyAccountButton.click();
		CommonUtils.waitForElementToBeVisible(MyAccountButtonDropDown);
		RegisterButton.click();
		CommonUtils.waitForPageReloadToComplete();	
		Assert.assertEquals(ContentHeading.getText(), "Register Account");	
	}
	public void fillNewAccountDetails() {
		CommonUtils.scrollTillElement(RegistrationFirstName);
		CommonUtils.senddKeysToElement(RegistrationFirstName, "Dummy");
		CommonUtils.senddKeysToElement(RegistrationLastName, "Dummy");
		String email = CommonUtils.generateRandomEmail();
		String pasword = CommonUtils.generateRandomPassword(10);
		
		setVariableValue("userEmail", email);
		setVariableValue("userPassword", pasword);
		
		CommonUtils.senddKeysToElement(RegistrationEmail, email);
		CommonUtils.senddKeysToElement(phone, "09876533");
		CommonUtils.senddKeysToElement(password, pasword);
		CommonUtils.senddKeysToElement(confirmPassword, pasword);
		if(!CommonUtils.checkBoxSelected(terms));
			terms.click();
		CommonUtils.waitForElementToBeClickable(contnueButton);
		contnueButton.click();
		Assert.assertEquals(ContentHeading.getText(), "Your Account Has Been Created!");	
	}
	
	public void checkUserCreationDetailsInAdmin() {
		
	}
}

package com.pages;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import utility.CommonUtils;

public class LoginPage extends CommonUtils {
	public void continueToLogin() {
		getElement("LoginPage_ContinueBtn").click();
		CommonUtils.waitForElementToBeClickable(getElement("LoginPage_MyAccount"));
		getElement("LoginPage_MyAccount").click();
		CommonUtils.waitForElementToBeVisible(getElement("LoginPage_MyAccountDropdown"));
		getElement("LoginPage_Logout").click();
		
		Assert.assertEquals(getElement("LoginPage_LogoutText").getText(), "Account Logout" , "Account logged out failed");
		getElement("LoginPage_ContinueBtn").click();
		
		
		CommonUtils.waitForElementToBeClickable(getElement("LoginPage_MyAccount"));
		getElement("LoginPage_MyAccount").click();
		CommonUtils.waitForElementToBeVisible(getElement("LoginPage_MyAccountDropdown"));
		getElement("LoginPage_Login").click();
		CommonUtils.waitForElementToBeVisible(getElement("LoginPage_EmailField"));
		getElement("LoginPage_EmailField").sendKeys(getVariableValue("userEmail"));
		getElement("LoginPage_PasswordField").sendKeys(getVariableValue("userPassword"));
		getElement("LoginPage_LoginBtn").click();
	}
}

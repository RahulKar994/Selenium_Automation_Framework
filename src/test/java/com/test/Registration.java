package com.test;

import org.testng.annotations.Test;

import com.pages.Booking;
import com.pages.LoginPage;
import com.pages.RegistrationPage;

import baseTest.BaseClass;

public class Registration extends BaseClass{
	@Test
	public void CreateUser() throws Exception {
		RegistrationPage register = new RegistrationPage();
		LoginPage login = new LoginPage();
		Booking datePicker = new Booking();
		datePicker.selectLocations();
		datePicker.selectDate("May 2025");
		
//		register.handleShadowDom();;
//		register.registerInAccount();
//		register.fillNewAccountDetails();
//		login.continueToLogin();
	}
}

package com.test;

import org.testng.annotations.Test;

import com.pages.RegistrationPage;

import baseTest.BaseClass;

public class Registration extends BaseClass{
	@Test
	public void CreateUser() {
		RegistrationPage register = new RegistrationPage();
		register.registerInAccount();
	}
}

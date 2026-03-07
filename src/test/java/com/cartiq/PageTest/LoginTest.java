package com.cartiq.PageTest;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cartiq.base.BaseClass;
import com.cartiq.pages.LoginClass;
import com.cartiq.utils.ExcelUtils;

public class LoginTest extends BaseClass{
	
	
	LoginClass login;
	 @DataProvider(name = "loginData")
	    public Object[][] getLoginData() throws Exception {
	        return ExcelUtils.getData();
	    }
	 
	 @Test(dataProvider = "loginData")
	 public void User(String testCaseId,
	                  String username,
	                  String password,
	                  String expected) {
		 driver.get("https://www.saucedemo.com/");
		 login = new LoginClass(driver);
	     login.enterusername(username); 
	     login.enterpassword(password); 
	     login.clicksubmit();
	     if (expected.equalsIgnoreCase("success")) {
	    	    Assert.assertTrue(login.isLoginSuccessful());
	    	} else {
	    	    Assert.assertFalse(login.isLoginSuccessful());
	    	}
	 }
	
}

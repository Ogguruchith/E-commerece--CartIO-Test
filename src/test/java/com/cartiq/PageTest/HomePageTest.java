package com.cartiq.PageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cartiq.base.BaseClass;
import com.cartiq.pages.HomePage;
import com.cartiq.pages.LoginClass;

public class HomePageTest extends BaseClass{

	HomePage page;
	
	
	@Test
	public void verifyHomePageLoaded()
	{
		driver.get("https://www.saucedemo.com/");
		LoginClass login=new LoginClass(driver);
		login.enterusername("standard_user");
		login.enterpassword("secret_sauce");
		login.clicksubmit();
		try {
	        Thread.sleep(1000);
	        driver.switchTo().alert().accept();
	    } catch (Exception e) {
	        // no popup = continue!
	    }

		Assert.assertTrue(login.isLoginSuccessful() , "Login Failed");
		
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
	    String actualUrl = driver.getCurrentUrl();
	    Assert.assertEquals(actualUrl, expectedUrl,"Not on HomePage!");
	    
		page=new HomePage(driver);
		page.First_Click();
		page.Second_Click();
		page.Third_Click();
		page.Fourth_Click();
		page.Five_Click();
		page.Six_Click();
		Assert.assertEquals( page.getCartCount(), 6,"Cart should have 6 items!");
		
	
			page.clickCartIcon();
			Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Not on cart page!");
		}
	
}

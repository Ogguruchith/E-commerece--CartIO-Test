package com.cartiq.PageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cartiq.base.BaseClass;
import com.cartiq.pages.CartPage;
import com.cartiq.pages.HomePage;
import com.cartiq.pages.LoginClass;

public class CartTest extends BaseClass{

	CartPage cart;
	@Test
	public void cartTest() throws InterruptedException
	{
		 driver.get("https://www.saucedemo.com/");
		 LoginClass login=new LoginClass(driver);
		 login.enterusername("standard_user");
		 login.enterpassword("secret_sauce");
		 login.clicksubmit();
		 
		 Assert.assertTrue(login.isLoginSuccessful());
		 
		 HomePage home = new HomePage(driver);
	        home.First_Click();
	        home.Second_Click();
	        home.Third_Click();
	        home.Fourth_Click();
	        home.Five_Click();
	        home.Six_Click();
	       
	        Assert.assertEquals(home.getCartCount(), 6,"Cart should have 6 items!");
	        home.clickCartIcon();
	        
	        cart=new CartPage(driver);
	        cart.CancelFirst();
	        cart.CancelSecond();
	        cart.CancelThird();
	        cart.CancelFive();
	        
	        int remainingItems = cart.getCartCount();
	        System.out.println("Items remaining in cart: "+ remainingItems);
	        
	        Assert.assertEquals( home.getCartCount(), 2,"Cart should have 2 items!");
	        
	        cart.checkOut();
	        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"),"Not on checkout page!");
	}
}

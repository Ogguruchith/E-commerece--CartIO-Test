package com.cartiq.PageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cartiq.base.BaseClass;
import com.cartiq.pages.CartPage;
import com.cartiq.pages.CheckoutPage;
import com.cartiq.pages.HomePage;
import com.cartiq.pages.LoginClass;

public class CheckoutTest extends BaseClass {

	@Test
	public void checkoutTest() {
		driver.get("https://www.saucedemo.com/");
		LoginClass login = new LoginClass(driver);
		login.enterusername("standard_user");
		login.enterpassword("secret_sauce");
		login.clicksubmit();

		Assert.assertTrue(login.isLoginSuccessful(), "Login Failed!");
		System.out.println("Login Successful!");
		extentTest.info("Login Successful!");
		HomePage home = new HomePage(driver);
		home.First_Click();
		home.Second_Click();
		home.Third_Click();
		home.Fourth_Click();
		home.Five_Click();
		home.Six_Click();

		Assert.assertEquals(home.getCartCount(), 6, "Cart should have 6 items!");
		System.out.println("Total items in cart: " + home.getCartCount());
		extentTest.info("Total items added to cart: 6");
		home.clickCartIcon();
		Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Not on cart page!");
		CartPage cart = new CartPage(driver);
		cart.CancelFirst();
		cart.CancelSecond();
		cart.CancelThird();
		cart.CancelFive();

		int remainingItems = cart.getCartCount();
		System.out.println("Items remaining in cart: " + remainingItems);

		extentTest.info("Items remaining in cart: " + remainingItems);
		Assert.assertEquals(remainingItems, 2, "Cart should have 2 items!");
		cart.checkOut();
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Not on checkout page!");
		System.out.println("Navigated to Checkout page!");
		extentTest.info("Navigated to Checkout page!");

		
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.enterusername("Ruchith");
		checkout.enterlastname("Kumar");
		checkout.entercode("560001");
		System.out.println("Entered checkout details!");
		extentTest.info("Entered: Ruchith Kumar 560001");

		
		checkout.ClickContinue();
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Not on order summary page!");
		System.out.println("Navigated to Order Summary!");
		extentTest.info("Navigated to Order Summary!");

	
		checkout.ClickFinish();
		System.out.println("Clicked Finish!");
		extentTest.info("Clicked Finish!");

	
		Assert.assertTrue(checkout.isOrderConfirmed(), "Order not confirmed!");
		System.out.println("Order Confirmed! Thank you message shown!");
		extentTest.info("Order Confirmed Successfully!");

	
		checkout.clickBackToProducts();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Not back on products page!");
		System.out.println("Back to Products page!");
		extentTest.info("Back to Products page!");

	}
}

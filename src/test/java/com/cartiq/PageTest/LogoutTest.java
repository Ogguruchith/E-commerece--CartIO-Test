package com.cartiq.PageTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.cartiq.base.BaseClass;
import com.cartiq.pages.LoginClass;
import com.cartiq.pages.LogoutPage;

public class LogoutTest extends BaseClass {

	@Test
	public void logoutTest() {
		driver.get("https://www.saucedemo.com/");
		LoginClass login = new LoginClass(driver);
		login.enterusername("standard_user");
		login.enterpassword("secret_sauce");
		login.clicksubmit();

		Assert.assertTrue(login.isLoginSuccessful(), "Login is Failed!");
		System.out.println("Login Successful!");
		extentTest.info("Login Successful!");

		LogoutPage logout = new LogoutPage(driver);
		logout.ClickMenu();
		logout.Clicklogin();

		Assert.assertTrue(logout.isLoggedOut(), "Logout Failed");
		System.out.println("Logout Successful!");
		extentTest.info("Logout Successful! Back on Login page!");
	}

}

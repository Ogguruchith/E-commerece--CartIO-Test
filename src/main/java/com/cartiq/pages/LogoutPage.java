package com.cartiq.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {

	WebDriver driver;
	WebDriverWait wait;
	public LogoutPage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	By menubutton=By.id("react-burger-menu-btn");
	By loginButton=By.id("logout_sidebar_link");
	
	public void ClickMenu()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(menubutton)).click();
	}
	public void Clicklogin()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
	}
	public boolean isLoggedOut() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/");
	}
}

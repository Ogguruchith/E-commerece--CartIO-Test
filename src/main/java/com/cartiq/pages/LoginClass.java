package com.cartiq.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginClass {

	WebDriver driver;
	WebDriverWait wait;
	public LoginClass(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	By user_name=By.id("user-name");
	By password=By.id("password");
	By login_button=By.id("login-button");
	  By errorMessage  = By.cssSelector("[data-test='error']");
	public void enterusername(String username)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(user_name)).sendKeys(username);
	}
	public void enterpassword(String pass)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);

	}
	
	public void clicksubmit()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(login_button)).click();;
	}
	
	public boolean isLoginSuccessful() {
	    return driver.getCurrentUrl()
	           .contains("inventory");
	}
	public String getErrorMessage() {
        return wait.until(ExpectedConditions
            .visibilityOfElementLocated(errorMessage))
            .getText();
	}
}

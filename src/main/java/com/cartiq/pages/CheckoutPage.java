package com.cartiq.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	WebDriver driver;
	WebDriverWait wait;
	public CheckoutPage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	By firstname=By.xpath("//input[@placeholder='First Name']");
	By lastname=By.xpath("//input[@placeholder='Last Name']");
	By zipcode=By.xpath("//input[@placeholder='Zip/Postal Code']");
	By continueclick=By.id("continue");
	By heading=By.xpath("//span[@data-test='title']");
	By finished=By.id("finish");
	
	By display=By.xpath("//h2[text()='Thank you for your order!']");
	By lastclick=By.id("back-to-products");
	public void enterusername(String username)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstname)).sendKeys(username);
	}
	
	public void enterlastname(String pass)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastname)).sendKeys(pass);
	}
	public void entercode(String Zip)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(zipcode)).sendKeys(Zip);
	}
	public void ClickContinue()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(continueclick)).click();;
	}
	public boolean isTitleDisplayed() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(heading)).isDisplayed();
	}
	public void ClickFinish()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(finished)).click();;
	}
	
	 public boolean isOrderConfirmed() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(display)).isDisplayed();
	    }
	 public void clickBackToProducts() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(lastclick)).click();
	    }
}

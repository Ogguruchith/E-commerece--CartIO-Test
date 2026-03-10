package com.cartiq.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	WebDriver driver;
	WebDriverWait wait;
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	// By Shopping_Click=By.xpath("//a[@class='shopping_cart_link']");
	 By First_Remove=By.id("remove-sauce-labs-backpack");
	 By Third_Remove=By.id("remove-sauce-labs-bolt-t-shirt");
	 By Five_Remove=By.id("remove-sauce-labs-onesie");
	 By Second_Remove=By.id("remove-sauce-labs-bike-light");
	 
	 public void CancelFirst() 
	 {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(First_Remove)).click();
		 
	 }
	 
	 public void CancelThird()
	 {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(Third_Remove)).click();
	 }
	 
	 public void CancelFive()
	 {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(Five_Remove)).click();
	 }
	 
	 public void CancelSecond()
	 {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(Second_Remove)).click();
	 }
	 
	 public void checkOut()
	 {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout"))).click();
	 }
	 
	 public int getCartCount() {
	        try {
	            return Integer.parseInt(
	                driver.findElement( By.className("shopping_cart_badge")) .getText()
	            );
	        } catch (Exception e) {
	            return 0;
	        }
	    }

}

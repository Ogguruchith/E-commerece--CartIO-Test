package com.cartiq.pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	By title=By.xpath("//div[@class='app_logo']");
	By First_Product=By.xpath("//div[text()='Sauce Labs Backpack']");
	By Second_Product=By.xpath("//div[text()='Sauce Labs Bike Light']");
	By Third_Product=By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']");
	By Fourth_Product=By.xpath("//div[text()='Sauce Labs Fleece Jacket']");
	By Fiveth_Product=By.xpath("//div[text()='Sauce Labs Onesie']");
	By Sixth_Product=By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public void Titles()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(title)).isDisplayed();
	}
	public void First_Click()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(First_Product)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart"))).click();
		driver.navigate().back();
	}
	public void Second_Click()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(Second_Product)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart"))).click();
		driver.navigate().back();
	}
	public void Third_Click()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(Third_Product)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart"))).click();
		driver.navigate().back();
	}
	public void Fourth_Click()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(Fourth_Product)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart"))).click();
		driver.navigate().back();
	}
	public void Five_Click()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(Fiveth_Product)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart"))).click();
		driver.navigate().back();
	}
	public void Six_Click()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(Sixth_Product)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart"))).click();
		driver.navigate().back();
	}
	public int getCartCount() {
        try {
            return Integer.parseInt(
                driver.findElement(By.className("shopping_cart_badge"))
                    .getText()
            );
        } catch (Exception e) {
            return 0;
        }
    }

 //going  to cart
    public void clickCartIcon() {
        wait.until(ExpectedConditions
            .elementToBeClickable(By.className("shopping_cart_link")))
            .click();
    }
}

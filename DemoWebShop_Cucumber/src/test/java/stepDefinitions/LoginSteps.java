package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/login");	
	}
	
	@When("user enters valid credential")
	public void user_enters_valid_credential() {
		driver.findElement(By.id("Email")).sendKeys("bahareh.r2m2@gmail.com");
		driver.findElement(By.cssSelector(".password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value = \"Log in\"]")).click();
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ico-logout")));
	}
	
	@Then("user is directed to the homepage")
	public void user_is_directed_to_the_homepage() {
		
		try {
		boolean isLogoutDisplayed = driver.findElement(By.cssSelector(".ico-logout")).isDisplayed();
		Assert.assertTrue("Logout button should be visible after login",isLogoutDisplayed);
		
		boolean isEmailDisplayed = driver.findElement(By.cssSelector(".header-links .account")).isDisplayed();
		Assert.assertTrue("Email should be visible after login",isEmailDisplayed);
		
//		Assert.assertEquals("User is not directed to the new page","https://demowebshop.tricentis.com/",driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("demowebshop.tricentis.com"));
		
//		if (isLogoutDisplayed) {
//			System.out.println("Login successful, user is on homepage");
//		}
//		else {
//			System.out.println("Login failed");
//		}
		}
		catch (Exception e) {
			System.out.println("something went wrong:   "+ e.getMessage());
		}
		finally {
		driver.quit();
		}
	}
	@When("user enters invalid credential")
	public void user_enters_invalid_credential() {
		driver.findElement(By.id("Email")).sendKeys("ivalid.email@gmail.com");
		driver.findElement(By.cssSelector(".password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value = \"Log in\"]")).click();
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation-summary-errors")));
	}
	@Then("user sees the error message")
	public void user_sees_the_error_message() {
		try{
			boolean isErrorMessageDisplayed = driver.findElement(By.cssSelector(".validation-summary-errors")).isDisplayed();
			Assert.assertTrue("Error message should be displayed",isErrorMessageDisplayed);
		}
		catch(Exception e) {
			System.out.println("Error Message:  "+ e.getMessage());
		}
		finally {
			driver.quit();
		}	
	}
	@When ("user enters empty credential")
	public void user_enters_empty_credential() {
		driver.findElement(By.xpath("//input[@value = \"Log in\"]")).click();
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation-summary-errors")));

	}
	@Then ("user sees the error message for empty credential")
	public void user_sees_the_error_message_for_empty_credential() {
		try {
//			boolean isErrorMessageDisplayed = driver.findElement(By.cssSelector(".validation-summary-errors")).isDisplayed();
			WebElement errorEmptyCredentials = driver.findElement(By.xpath("//li[normalize-space()='No customer account found']"));
			String errorText = errorEmptyCredentials.getText();
			Assert.assertTrue("error message should contain :No customer account found",errorText.contains("No customer account found"));
		}
		catch(Exception e) {
			System.out.println("Error Message:  "+ e.getMessage());
		}
		finally {
			driver.quit();
		}
		
		
		
	}
}

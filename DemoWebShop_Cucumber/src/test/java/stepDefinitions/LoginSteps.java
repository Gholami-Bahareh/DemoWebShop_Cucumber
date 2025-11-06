package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	WebDriver driver;
	
	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/login");	
	}
	
	@When("user enters valid credential")
	public void user_enters_valid_credential() {
		driver.findElement(By.id("Email")).sendKeys("bahareh.r2m2@gmail.com");
		driver.findElement(By.cssSelector(".password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value = \"Log in\"]")).click();
	}
	 
	@Then("user is directed to the homepage")
	public void user_is_directed_to_the_homepage() {
		boolean isLogoutDisplayed = driver.findElement(By.cssSelector(".ico-logout")).isDisplayed();
		Assert.assertTrue("Logout button should be visible after login",isLogoutDisplayed);
		
		if (isLogoutDisplayed) {
			System.out.println("Login successful, user is on homepage");
		}
		else {
			System.out.println("Login failed");
		}
		driver.quit();
		System.out.println("user is directed to the homepage");
	}

}

package com.stepdef;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AdactinStep {
	WebDriver driver;
	@Given("User is on Adactin Hotel page")
	public void userIsOnAdactinHotelPage() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://adactinhotelapp.com/");
	}

	@When("User should login {string}, {string}")
	public void userShouldLogin(String name, String pass) {
		WebElement userName =driver.findElement(By.id("username"));
		userName.sendKeys(name);
		WebElement password =driver.findElement(By.id("password"));
		password.sendKeys(pass);
		WebElement tbnLogin = driver.findElement(By.id("login"));
		tbnLogin.click();
	}

	@When("User should verify {string} after login")
	public void userShouldVerifyAfterLogin(String welcomeNote) {
		WebElement showUserName = driver.findElement(By.id("username_show"));
		String loginVerificationCode = showUserName.getAttribute("value");
		Assert.assertEquals("verify login", welcomeNote, loginVerificationCode);
	}

	@When("User should search hotel {string},{string},{string},{string},{string}, {string}, {string} and {string}")
	public void userShouldSearchHotelAnd(String location, String hotels, String roomTYpe, String onOfRooms, String checkInDate, String checkOutDate, String noOfAdult, String noOfChild) {
		WebElement searchLOcation =driver.findElement(By.id("location"));
		searchLOcation.sendKeys(location);
		WebElement searchHotels = driver.findElement(By.id("hotels"));
		searchHotels.sendKeys(hotels);
		WebElement searchRoomType = driver.findElement(By.id("room_type"));
		searchRoomType.sendKeys(roomTYpe);
		WebElement searchNoOfRooms = driver.findElement(By.id("room_nos"));
		searchNoOfRooms.sendKeys(onOfRooms);
		WebElement searchCheckIn = driver.findElement(By.id("datepick_in"));
		searchCheckIn.sendKeys(checkInDate);
		WebElement searchCheckOut = driver.findElement(By.id("datepick_out"));
		searchCheckOut.sendKeys(checkOutDate);
		WebElement searchAdult = driver.findElement(By.id("adult_room"));
		searchAdult.sendKeys(noOfAdult);
		WebElement searchChild = driver.findElement(By.id("child_room"));
		searchChild.sendKeys(noOfChild);	
		WebElement clickSubmit = driver.findElement(By.id("Submit"));
		clickSubmit.click();
	}

	@When("User should verify the {string} after searching Hotel")
	public void userShouldVerifyTheAfterSearchingHotel(String containsSearchVerification) {
		String selectHotel = driver.getTitle();
		boolean containsSearchVerification1 = selectHotel.contains("Select Hotel");
		System.out.println(selectHotel);
		Assert.assertTrue("verify Hotel", containsSearchVerification1);
	}

	@When("User should Continue by selecting hotel")
	public void userShouldContinueBySelectingHotel() {
		WebElement btnSelect = driver.findElement(By.id("radiobutton_0"));
		btnSelect.click();
		WebElement btnContinue = driver.findElement(By.id("continue"));
		btnContinue.click();
	}

	@When("User should verify {string} after selecting hotel")
	public void userShouldVerifyAfterSelectingHotel(String string) {
		String bookHotel = driver.getTitle();
		boolean containsBookHotel = bookHotel.contains("Book A Hotel");
		System.out.println(containsBookHotel);
		Assert.assertTrue("verify Hotel", containsBookHotel);
	}

	@When("User should book a hotel {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
	public void userShouldBookAHotelAnd(String firstName, String lastName, String address, String creditCardNo, String cardType, String expiryMonth, String expiryYear, String cCVNo) {
		WebElement giveFirstName = driver.findElement(By.id("first_name"));
		giveFirstName.sendKeys(firstName);	
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebElement givelastName = driver.findElement(By.name("last_name"));
		givelastName.sendKeys(lastName);	
		WebElement giveAddress = driver.findElement(By.id("address"));
		giveAddress.sendKeys(address);	
		WebElement giveCreditCardNo = driver.findElement(By.id("cc_num"));
		giveCreditCardNo.sendKeys(creditCardNo);	
		WebElement giveCardType = driver.findElement(By.id("cc_type"));
		giveCardType.sendKeys(cardType);	
		WebElement giveExpiryMonth = driver.findElement(By.id("cc_exp_month"));
		giveExpiryMonth.sendKeys(expiryMonth);	
		WebElement giveExpiryYear = driver.findElement(By.id("cc_exp_year"));
		giveExpiryYear.sendKeys(expiryYear);	
		WebElement givecCVNo = driver.findElement(By.id("cc_cvv"));
		givecCVNo.sendKeys(cCVNo);	
		WebElement btnContinue = driver.findElement(By.id("book_now"));
		btnContinue.click();
	}

	@Then("User should verify {string} after generating Order id")
	public void userShouldVerifyAfterGeneratingOrderId(String bookingPageConfirmation) {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebElement getOrderNo = driver.findElement(By.name("order_no"));
		String attribute = getOrderNo.getAttribute("value");
		System.out.println("\n\norder id:"+attribute);
		WebElement bookingConfirmation = driver.findElement(By.xpath("//td[text()='Booking Confirmation ']"));
		String confirmBooking = bookingConfirmation.getText();
		System.out.println(confirmBooking);
		Assert.assertEquals("verify booking", bookingPageConfirmation, confirmBooking);
		driver.quit();
	}
}

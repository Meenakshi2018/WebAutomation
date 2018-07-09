package com.hellofresh.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//Selenium Imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	WebDriver driver;
    WebDriverWait wait;

    //Object Repository - Page Factory - Register functionality
    @FindBy(className = "login") WebElement signInButton;
    @FindBy(id ="email_create") WebElement newEmailTextBox;
    @FindBy(id = "SubmitCreate") WebElement createAccountButton;
    @FindBy(id = "id_gender2") WebElement genderSelection;
    @FindBy(id = "customer_firstname") WebElement firstName;
    @FindBy(id = "customer_lastname") WebElement lastName;
    @FindBy(id = "passwd") WebElement password;
    @FindBy(id = "days") WebElement dayDoB;
    @FindBy(id = "months") WebElement monthDoB;
    @FindBy(id = "years") WebElement yearDoB;
    @FindBy(id = "company") WebElement companyName;
    @FindBy(id = "address1") WebElement addressOne;
    @FindBy(id = "address2") WebElement addressTwo;
    @FindBy(id = "city") WebElement cityName;
    @FindBy(id = "id_state") WebElement stateName;
    @FindBy(id = "postcode") WebElement postCode;
    @FindBy(id = "other") WebElement additionalInfo;
    @FindBy(id = "phone") WebElement homePhone;
    @FindBy(id = "phone_mobile") WebElement mobilePhone;
    @FindBy(id = "alias") WebElement assignAddressAs;
    @FindBy(id = "submitAccount") WebElement registerButton;
    @FindBy(css = "h1") WebElement headingDetails;
    @FindBy(className = "account") WebElement verifyAccount;
    @FindBy(className = "info-account") WebElement welcomeText;
    @FindBy(className = "logout") WebElement logoutButton;
    
    //PageFactory Login
    @FindBy(id = "email") WebElement loginEmail;
    @FindBy(id = "passwd") WebElement loginPassword;
    @FindBy(id = "SubmitLogin") WebElement loginButton;
   
    //PageFactory Purchase
    @FindBy(linkText = "Women") WebElement womenLink;
    @FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']") WebElement fadedTShirtLink;
    @FindBy(name = "Submit") WebElement addToKart;
    @FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']") WebElement proceedToCheckout1;
    @FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']") WebElement proceedToCheckout2;
    @FindBy(name = "processAddress") WebElement proceedToCheckoutAddress;
    @FindBy(id = "uniform-cgv") WebElement iAgreeCheckbox;
    @FindBy(name = "processCarrier") WebElement proceedToCourier;
    @FindBy(className = "bankwire") WebElement payByWire;
    @FindBy(xpath = "//*[@id='cart_navigation']/button") WebElement confirmOrderButton;
    @FindBy(xpath = "//li[@class='step_done step_done_last four']") WebElement shippingLink;
    @FindBy(xpath = "//li[@id='step_end' and @class='step_current last']") WebElement paymentLink;
    @FindBy(xpath = "//*[@class='cheque-indent']/strong") WebElement orderCompleteMsg;
    
    
    //Constructor
	public PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10, 50);
	}

	
	/*
	 * Getter Setter methods
	 */
	
	//Open URL
	public void openUrl(String strUrl) {
		driver.get(strUrl);
	}
	
	//Click Sign-in Button
	public void clickSignIn() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
	}
	
	//Set New Email ID - New user page
	public void setNewEmail(String email) {
		newEmailTextBox.sendKeys(email);
	}
	
	//Click Create account - to get the new form
	public void createAccount() {
		createAccountButton.click();
	}
	
	//set Gender on new user form
	public void setGender() {
		wait.until(ExpectedConditions.visibilityOf(genderSelection)).click();
	}
	
	//set first name on new user form
	public void setFirstName(String name) {
		firstName.sendKeys(name);
	}
	
	//set lastname on new user form
	public void setLastName(String lastname) {
		lastName.sendKeys(lastname);
	}
	
	//set password on new user form
	public void setPassword (String pass) {
		password.sendKeys(pass);
	}
	
	//set Day of Birth
	public void setDay(String day) {
		Select select = new Select(dayDoB);
		select.selectByValue(day);
	}
	
	public void setMonth(String month) {
		Select select = new Select(monthDoB);
		select.selectByValue(month);
	}
	
	public void setYear(String year) {
		Select select = new Select(yearDoB);
		select.selectByValue(year);
	}
	
	public void setCompany (String company) {
		companyName.sendKeys(company);
	}
	
	public void setAddress1 (String addressone) {
		addressOne.sendKeys(addressone);
	}
	
	public void setAddress2 (String addresstwo) {
		addressTwo.sendKeys(addresstwo);
	}
	
	public void setCity(String city) {
		cityName.sendKeys(city);
	}
	
	public void setState(String state) {
		Select select = new Select(stateName);
		select.selectByVisibleText(state);
	}
	
	public void setPostcode (String code) {
		postCode.sendKeys(code);
	}
	
	public void moreInfo(String info) {
		additionalInfo.sendKeys(info);
	}
	
	public void setPhone(String homephone) {
		homePhone.sendKeys(homephone);
	}
	
	public void setMobile(String mobile) {
		mobilePhone.sendKeys(mobile);
	}
	
	public void setAssignAddressAs (String assignme) {
		assignAddressAs.sendKeys(assignme);
	}
	
	public void clickRegister() {
		registerButton.click();
	}
	
	public String getHeading() {
		wait.until(ExpectedConditions.visibilityOf(headingDetails));
		return headingDetails.getText();
	}
	
	public String getVerifyAccount() {
		return verifyAccount.getText();
	}
	
	public String getWelcomeText() {
		return welcomeText.getText();
	}
	
	public boolean verifyLogoutIsPresent() {
		return logoutButton.isDisplayed();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public void setLoginEmail (String email) {
		loginEmail.sendKeys(email);
	}
	
	public void setLoginPassword (String password) {
		loginPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	
	public void clickWomens() {
		wait.until(ExpectedConditions.visibilityOf(womenLink)).click();
	}
	
	public void clickFadedTShirtLink() {
		wait.until(ExpectedConditions.visibilityOf(fadedTShirtLink)).click();
	}
	
	public void clickAddToKart() {
		wait.until(ExpectedConditions.visibilityOf(addToKart)).click();
	}
	
	public void clickProceedToCheckout1(){
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout1)).click();
	}
	
	public void clickProceedToCheckout2(){
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout2)).click();
	}
	
	public void clickProceedToCheckoutAddress(){
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutAddress)).click();
	}
	
	public void clickIAgreeBox(){
		wait.until(ExpectedConditions.visibilityOf(iAgreeCheckbox)).click();
	}
	
	public void clickProceedToCourier(){
		proceedToCourier.click();
	}
	
	public void clickPayByWire() {
		wait.until(ExpectedConditions.visibilityOf(payByWire)).click();
	}
	
	public void clickConfirmOrderButton() {
		wait.until(ExpectedConditions.visibilityOf(confirmOrderButton)).click();
	}
	
	public boolean checkShippingLink() {
		return shippingLink.isDisplayed();
	}
	
	public boolean checkPaymentLink() {
		return paymentLink.isDisplayed();
	}
	
	public String getOrderCompleteText() {
		return orderCompleteMsg.getText();
	}
	
	public void takeScreenshot(String fileName) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("test-output/screenshots/"+fileName));
	}
}

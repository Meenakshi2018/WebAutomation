package com.hellofresh.steps;

//TestNG imports
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

//Selenium imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//Custom classes imports
import com.hellofresh.pages.PageObject;
import com.hellofresh.utilities.Config;
import com.hellofresh.utilities.TestData;

//Cucumber imports
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//WebDriver import - needed for creation of ChromeDriver / Firefox without setting property
import io.github.bonigarcia.wdm.WebDriverManager;

//Import our Logger - Small size and need not create an object
import org.pmw.tinylog.Logger;

/*
 * This class defines the steps given in Scenarios.feature file - Cucumber
 */
public class FeatureDefinition {
	WebDriver driver;
    WebDriverWait wait;
    //Define the variables for the Custom classes
    PageObject pageObject;
    Config config;
    TestData testData;
    
    /*
     * Constructor Create Objects for the Custom classes being used
     */
    public FeatureDefinition() {
    	config = new Config();
        testData = new TestData();
	}
    
    /*
     * Cucumber hook, gets executed before each scenario
     */
    @Before
	public void setup() {
    	//Decide the browser based on Config.properties
        if(config.getConfigProperty("Browser").equals("Firefox")) {
    		WebDriverManager.firefoxdriver().setup();
    		driver = new FirefoxDriver();
    	}else {
    		WebDriverManager.chromedriver().setup();
    		driver = new ChromeDriver();
    	}
		
        wait = new WebDriverWait(driver, 10, 50);
        pageObject = new PageObject(driver);
	}
	
    /*
     * Cucumber hook for the teardown
     */
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
		driver=null;
	}
	
	/*
	 * Starting Cucumber Step Definitions from the section below till the end of the Class
	 */
	@Given("^New User Opens Home page$")
	public void new_User_Opens_Home_page() throws Throwable {
		Logger.info("New User opens Home Page");
        pageObject.openUrl(config.getConfigProperty("Url"));
	}

	@When("^Uses the first email textbox with new email ID$")
	public void uses_the_first_email_textbox_with_new_email_ID() throws Throwable {
		Logger.info("Click Sing-in and enter email ID");
		pageObject.clickSignIn();
		pageObject.setNewEmail(testData.getNewEmail());
	}

	@When("^Clicks to Create account$")
	public void clicks_to_Create_account() throws Throwable {
		Logger.info("Clicking Create Account Button");
		pageObject.createAccount();
	}

	@When("^On the new user form, fills all fields with correct data$")
	public void on_the_new_user_form_fills_all_fields_with_correct_data() throws Throwable {
		Logger.info("Adding all the details of the user");
		pageObject.setGender();
        pageObject.setFirstName(testData.getProperty("name"));
        pageObject.setLastName(testData.getProperty("surname"));
        pageObject.setPassword(testData.getProperty("password"));
        
        pageObject.setDay(testData.getProperty("dayOfBirth"));
        pageObject.setMonth(testData.getProperty("monthOfBirth"));
        pageObject.setYear(testData.getProperty("yearOfBirth"));
        
        pageObject.setCompany(testData.getProperty("companyName"));
        pageObject.setAddress1(testData.getProperty("address1"));
        pageObject.setAddress2(testData.getProperty("address2"));
        pageObject.setCity(testData.getProperty("city"));
        pageObject.setState(testData.getProperty("state"));
        pageObject.setPostcode(testData.getProperty("pincode"));
        pageObject.moreInfo(testData.getProperty("moreInfo"));
        
        pageObject.setPhone(testData.getProperty("homephone"));
        pageObject.setMobile(testData.getProperty("mobilephone"));
        
        pageObject.setAssignAddressAs(testData.getProperty("storeAddressAs"));
	}

	@When("^Clicks Register button$")
	public void clicks_Register_button() throws Throwable {
		Logger.info("Done with Entering data, Now clicking Register");
		pageObject.clickRegister();
	}

	@Then("^Account is Successfully created$")
	public void account_is_Successfully_created() throws Throwable {
		Logger.info("Verification Stage for newly created account");
		
		//Confirm: Heading is MY ACCOUNTS
		assertEquals(pageObject.getHeading(), testData.getProperty("headingAccountText"));
		
		//Check the account details provided are correctly reflected
		assertEquals(pageObject.getVerifyAccount(), testData.getProperty("name") + " " + testData.getProperty("surname"));
        //Confirm There is a welcome message
		assertTrue(pageObject.getWelcomeText().contains(testData.getProperty("welcomeText")));
        //Confirm Logout button is present
		assertTrue(pageObject.verifyLogoutIsPresent());
        //Confirm URL contains my-account
		assertTrue(pageObject.getUrl().contains("controller=my-account"));
		pageObject.takeScreenshot("NewAccount.png");
	}

	@Given("^Existing customer Opens Home Page$")
	public void existing_customer_Opens_Home_Page() throws Throwable {
		Logger.info("Existing User Opens Home");
		pageObject.openUrl(config.getConfigProperty("Url"));
	}

	@Given("^Clicks Sign in button \\(in the header\\)$")
	public void clicks_Sign_in_button_in_the_header() throws Throwable {
		Logger.info("User Clicks Sign-in on Home page to go to Sign-in page");
		pageObject.clickSignIn();
	}

	@Given("^Fills existing Email address and Password$")
	public void fills_existing_Email_address_and_Password() throws Throwable {
		Logger.info("User Inputs existing user name and password");
		pageObject.setLoginEmail(testData.getProperty("existingUserEmail"));
		pageObject.setLoginPassword(testData.getProperty("existingUserPassword"));
	}

	@Given("^Click Sign in$")
	public void click_Sign_in() throws Throwable {
		Logger.info("Click Sign-in after inputting user name and password");
		pageObject.clickLogin();
	}

	@Then("^User is logged on successfully$")
	public void user_is_logged_on_successfully() throws Throwable {
		Logger.info("Verification if user is successfully logged in");
        //Confirm the heading is MY ACCOUNT
		assertEquals(pageObject.getHeading(), testData.getProperty("headingAccountText"));
		//Confirm fullname matches
        assertEquals(testData.getProperty("fullName"), pageObject.getVerifyAccount());
        //COnfirm there is a welcome message
        assertTrue(pageObject.getWelcomeText().contains(testData.getProperty("welcomeText")));
        //Confirm Logout button is present
        assertTrue(pageObject.verifyLogoutIsPresent());
        //Confirm URL contains my-account
        assertTrue(pageObject.getUrl().contains("controller=my-account"));   
		pageObject.takeScreenshot("SuccessfulLogin.png");

	}
	
	@Given("^Existing customer Logs in to Purchase$")
	public void existing_customer_Logs_in_to_Purchase() throws Throwable {
		Logger.info("Starting Scenario - User Logins in");
        pageObject.openUrl(config.getConfigProperty("Url"));
		pageObject.clickSignIn();
		pageObject.setLoginEmail(testData.getProperty("existingUserEmail"));
		pageObject.setLoginPassword(testData.getProperty("existingUserPassword"));
        pageObject.clickLogin();
	}

	@When("^Clicks Women button in the header$")
	public void clicks_Women_button_in_the_header() throws Throwable {
		Logger.info("Clicks Women link");
		pageObject.clickWomens();
	}

	@When("^Clicks the product with name Faded Short Sleeve T-shirts$")
	public void clicks_the_product_with_name_Faded_Short_Sleeve_T_shirts() throws Throwable {
		Logger.info("User click Faded short sleeve T-shirt");
		pageObject.clickFadedTShirtLink();	
	}

	@When("^Continue all the steps of checkout$")
	public void continue_all_the_steps_of_checkout() throws Throwable {
		Logger.info("Finishing the Checkout process");
        pageObject.clickAddToKart();
        pageObject.clickProceedToCheckout1();
        pageObject.clickProceedToCheckout2();
        pageObject.clickProceedToCheckoutAddress();
        pageObject.clickIAgreeBox();
        pageObject.clickProceedToCourier();
        pageObject.clickPayByWire();
        pageObject.clickConfirmOrderButton();
	}

	@Then("^Order is Successful$")
	public void order_is_Successful() throws Throwable {
		Logger.info("Verify the Order is successful");
		//Confirm message as :ORDER CONFIRMATION
		assertEquals(pageObject.getHeading(), testData.getProperty("orderHeading"));
		//Shipping link is Displayed
	    assertTrue(pageObject.checkShippingLink());
		//payment link is Displayed
	    assertTrue(pageObject.checkPaymentLink());
		//Order is complete with Bold
	    assertTrue(pageObject.getOrderCompleteText().contains(testData.getProperty("completedOrderText")));
	    //Confirm URL contains order-confirmation
	    assertTrue(pageObject.getUrl().contains("controller=order-confirmation"));
		pageObject.takeScreenshot("SuccessfulOrder.png");

	}
}
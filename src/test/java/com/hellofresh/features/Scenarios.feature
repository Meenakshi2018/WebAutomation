Feature: Challenge

Scenario: Sign up
	Given New User Opens Home page
	When Uses the first email textbox with new email ID 
	And Clicks to Create account
	And On the new user form, fills all fields with correct data
	And Clicks Register button
	Then Account is Successfully created
	
Scenario: Log in
	Given Existing customer Opens Home Page
	And Clicks Sign in button (in the header)
	And Fills existing Email address and Password 
	And Click Sign in
	Then  User is logged on successfully
	
Scenario: Purchase Order
	Given Existing customer Logs in to Purchase
	When Clicks Women button in the header
	And Clicks the product with name Faded Short Sleeve T-shirts
	And Continue all the steps of checkout
	Then Order is Successful
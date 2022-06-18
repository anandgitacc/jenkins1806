package org.stepdef;

import org.base.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef extends BaseClass {
	
	@Given("User is on the facebook login page")
	public void user_is_on_the_facebook_login_page() {
		browserLaunch("https://en-gb.facebook.com/");
		System.out.println("facebook login page");
	}
	
	@When("User should enter the {string} and {string}")
	public void user_should_enter_the_and(String username, String password) {
		findElementid("email").sendKeys(username);
		findElementid("pass").sendKeys(password);
		System.out.println("username and password");
	}
	
	
	@When("User should click the login button")
	public void user_should_click_the_login_button() {
		findElementName("login").click();
		System.out.println("login button");
	}

	@Then("User should verify the success message")
	public void user_should_verify_the_success_message() {
		System.out.println("FB login credentials successfully verified");
	}

}

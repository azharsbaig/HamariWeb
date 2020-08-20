package com.step.def;

import org.openqa.selenium.WebDriver;

import com.cucumber.code.BaseCode;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionsNew<myList> {
	WebDriver driver;
	
	@Given("User is on Hamari Web Site")
	public void user_is_on_Hamari_Web_Site() throws Throwable {
		driver = BaseCode.setupBrowser("chrome");
	}

	@When("User click on business link")
	public void user_click_on_business_link() throws Throwable {
		BaseCode.clickBusinessLink(driver);
	}

	@When("user click on Finance link")
	public void user_click_on_Finance_link() throws Throwable {
		BaseCode.clickFinanceLink(driver);	    
	}	
	
	@Then("User click on prize bond results")
	public void user_click_on_prize_bond_results(Integer bondValue) throws Throwable {
	    BaseCode.testBondResult(driver, bondValue);
	}

	@Then("User click on {int} bond")
	public void user_click_on_bond(Integer int1) throws Throwable {
	    BaseCode.testBondResult(driver, int1);
	}

//	@When("User enter bond number")
//	public void user_enter_bond_number(myList bondNumber) {
//	    BaseCode.testData(driver, myList);
//	}

	@Then("User get result")
	public void user_get_result() {
	    
	}


}

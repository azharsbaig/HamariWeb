package com.step.def;

import org.openqa.selenium.WebDriver;

import com.cucumber.code.BaseCode;
import com.cucumber.code.SetBrowser;
import com.page.model.BondListPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
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

	@Then("User click on {int} prize bond results")
	public void user_click_on_prize_bond_results(Integer bondVal) throws Throwable {
		BaseCode.clickBondResult(driver, bondVal);
	}

	@When("User enter bond num {int}")
	public void user_enter_bond_num(Integer bondValue) throws Throwable {
	     BaseCode.testBondResult(driver, bondValue);
	}
		
	@Then("User is on result page")
	public void user_is_on_result_page() {
	   
	}

}

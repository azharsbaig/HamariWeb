#Author: azharbaig@gmail.com
#Keywords Feature file prize bonds result :
Feature: Prize Bonds

	 
Background: Open browser
Given User is on Hamari Web Site
When  User click on business link
And   user click on Finance link
Then  User click on prize bond results

Scenario: check 1500 bond result
And   User click on 1500 bond
Then  User is on result page
When  User enter bond number
Then  User get result

Scenario: check 25000 bond result
And   User click on 25000 bond
Then  User is on result page
When  User enter bond number
Then  User get result
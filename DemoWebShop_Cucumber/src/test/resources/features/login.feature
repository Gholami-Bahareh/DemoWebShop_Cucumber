Feature: Login Functionality

Scenario: Successful login with valid credentials
  Given user is on the login page
  When user enters valid credential
  Then user is directed to the homepage
  
Scenario: Unsuccessful login with invalid credentials
  Given user is on the login page
  When user enters invalid credential
  Then user sees the error message
  
 Scenario: Login attempt with empty credentials
  Given user is on the login page
  When user enters empty credential
  Then user sees the error message for empty credentials
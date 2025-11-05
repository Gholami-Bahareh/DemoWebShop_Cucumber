Feature: Login Functionality

Scenario: Successful login with valid credential
  Given user is on the login page
  When user enter correct credential
  Then user is directed to the homepage
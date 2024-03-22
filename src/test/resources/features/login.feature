@wip
Feature: Users should be able to login

  Background: User is already in the login page
    Given user is on "Login" page


  Scenario Outline: Login with parameterization
    Given the user logged in with "positive" datatype as "<user>"
    Then user is on "Dashboard" page
    Examples:
    |user|
    |driver|
    |salesmanager|
    |storemanager|
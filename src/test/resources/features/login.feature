@wip
Feature: Users should be able to login
         Also user able to logout AF

  Background: User is already in the login page
    Given user is on "Login" page

  @login
  Scenario Outline: Login with parameterization
    Given the user logged in with "positive" datatype as "<user>"
    Then user is on "Dashboard" page
    Examples:
    |user|
    |driver|
    |salesmanager|
    |storemanager|


Scenario Outline: User able to Logout
  Given the user logged in with "positive" datatype as "<user>"
  Then user is on "Dashboard" page
  Then user  click on  "Logout" button
  And  user is on "Login" page
  Examples:
  |user|
  |driver|
  |storemanager|
  |salesmanager|


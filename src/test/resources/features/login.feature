@wip
Feature: Users should be able to login
         Also user able to logout AF

  Background: User is already in the login page
    Given the user is on the login page


  Scenario Outline: Login with parameterization
    Given the user logged in with "positive" datatype as "<user>"
    Then user is on "Dashboard" page
    Examples:
    |user|
    |driver|
    |salesmanager|
    |storemanager|


Scenario Outline: User able to Logout AF
  Given the user logged in with "positive" datatype as "<user>"
  Then user is on "Dashboard" page
  Then user  click on  "logout" button under user's name is displayed in the top right corner AF
  And  user back to "login" page AF
  Examples:
  |user|
  |driver|
  |storemanager|
  |salesmanager|


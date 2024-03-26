@wip
Feature: Users should be able to login
         Also user able to logout AF

  Background: User is already in the login page
    Given user is on "Login" page

  @login @aut_awzal
  Scenario Outline: Login with parameterization
    Given the user logged in with "positive" datatype as "<user>"
    Then user is on "Dashboard" page
    Examples:
    |user|
    |driver|
    |salesmanager|
    |storemanager|

  @logout @aut_flora
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

  @aut_halzat @remember_me
  Scenario: remember me functionality on login page
    When user click on remember me button
    Then then the checkbox should be checked

  @aut_awzal @forgot_password
  Scenario: "Forgot your password" functionality on login page (positive)
    When user click on 'Forgot your password?' button
    And user enter "positive" Username or Email
    Then user should get success message


  @aut_awzal @forgot_password
  Scenario: "Forgot your password" functionality on login page (Negative)
    When user click on 'Forgot your password?' button
    And user enter "negative" Username or Email
    Then user should get error message

  @login @aut_flora
  Scenario Outline: unable Login with parameterization
    Given the user logged in with "negative" datatype as "<user>"
    Then users  should get error message
    Examples:
      |user|
      |driver|
      |salesmanager|
      |storemanager|
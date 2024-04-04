@wip
Feature: As a tuck driver I should be able to access Vehicle under fleet module

  @aut_halzat  @odometer-info
  Scenario:truck driver can see general information by clicking any car
    Given user logged in with "positive" datatype as "driver"
    Then user is on "Dashboard" page
    Given the user clicks "Vehicle Odometer" on "Fleet"
    Then user is on "Odometer" page
    When user sets view per page to "10"
    Then user should see correct number of vehicles

@aut-flora
Scenario Outline: User with restricted access cannot access the odometer page
  Given user logged in with "positive" datatype as "<user>"
  Then user is on "Dashboard" page
  When the user clicks "Vehicle Odometer" on "Fleet"
  Then user should get"You do not have permission to perform this action." message

  Examples:
    | user   |
    | salesmanager  |
    | storemanager  |





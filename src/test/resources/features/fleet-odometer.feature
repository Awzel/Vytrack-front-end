@wip
Feature: As a tuck driver I should be able to access Vehicle under fleet module
  Background: User logged in as truck driver
    Given user logged in with "positive" datatype as "driver"
    Then user is on "Dashboard" page

  @aut_halzat  @odometer-info
  Scenario: truck driver can see general information by clicking any car
    Given the user clicks "Vehicle Odometer" on "Fleet"
    Then user is on "Odometer" page
    When user sets view per page to "10"
    Then user should see correct number of vehicles
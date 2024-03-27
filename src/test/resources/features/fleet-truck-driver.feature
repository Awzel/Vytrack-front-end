@wip
Feature: As a tuck driver I should be able to access Vehicle under fleet module
  Background: User logged in as truck driver
    Given user logged in with "positive" datatype as "driver"
    Then user is on "Dashboard" page

    @aut_halzat @vehicles
   Scenario: driver is able to set view per page number.
     Given the user clicks "Vehicles" on "Fleet"
     Then user is on "Vehicles" page
     When user sets view per page to "10"
     Then user should see correct number of vehicles


@aut_flora  @vehicles @flora
  Scenario: driver is able to resets all the pages
  Given the user clicks "Vehicles" on "Fleet"
  Then user is on "Vehicles" page
  And user able to click reset button
  Then user sets view per page to "25"
  Then user should see correct number of vehicles


Feature: SEAT Test

  Scenario: Seat read from file
    Given opens main page

    Then introduce username and pass
    And press login button
    And inserts token
    And sign in seat
    And go to booking
    And open workplaces
    And select ZTower
    And select Kitchen
    And select date
    And select place

    Then debug step

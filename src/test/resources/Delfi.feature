Feature: Testing Delfi page
  Background: Prepare driver
    Given Initialize web driver

  Scenario: Comparing comments of the article
    Given Open Delfi desktop home
    And Look at comment count of article Kalnozols rosina Saeimas deputātiem automātiski piešķirt pielaidi valsts noslēpumam on desktop
    And Open Delfi mobile home
    And Look at comment count of article Kalnozols rosina Saeimas deputātiem automātiski piešķirt pielaidi valsts noslēpumam on mobile
    Then Comment counts should be same

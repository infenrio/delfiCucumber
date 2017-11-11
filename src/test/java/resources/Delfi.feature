Feature: Testing Delfi page
  Background: Prepare driver
    Given Initialize web driver

  Scenario: Comparing comments of the article
    Given Open Delfi desktop home
    And Look at comment count of article Šlesera iztaujāšana 'oligarhu lietas' komisijā: Prokuratūra sāk pārbaudi on desktop
    And Open Delfi mobile home
    And Look at comment count of article Šlesera iztaujāšana 'oligarhu lietas' komisijā: Prokuratūra sāk pārbaudi on mobile
    Then Comment counts should be same

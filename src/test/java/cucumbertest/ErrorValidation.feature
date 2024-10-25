
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Login with invalid credential
    Given I launch the application
    When Logged into application using <username> and <password>
    Then "Incorrect email or password." message is displayed in LoginPage

    Examples: 
      | username  | password |
      | ramesharavind96@gmail.com | Test@1234 |
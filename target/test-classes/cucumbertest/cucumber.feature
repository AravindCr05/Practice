
@tag
Feature: Placing the order in E-commerce website
  I want to use this template for my feature file

Background:
Given I launch the application

  @OrderPlacing
  Scenario Outline: Placing the order
    Given Logged into application using <username> and <password>
    When I add the product<ProductName> to the cart
    And I checkout and submit the order
    Then "Thankyou for the order." message is displayed

    Examples: 
      | username  | password | ProductName  |
      | ramesharavind96@gmail.com | Test@123 | IPHONE 13 PRO |
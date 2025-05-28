Feature: Amazon User Journey

  Scenario: Register on Amazon
    Given I am on the Amazon registration page
    When I fill out the registration form
    Then I should be redirected to the OTP verification page

  Scenario: Sign in to Amazon
    Given I am on the Amazon home page
    When I sign in with valid credentials
    Then I should be logged in

  Scenario: Search product and buy
    Given I am on the Amazon home page
    When I search for "Laptop"
    And I add the first product to the cart
    And I click Buy Now
    Then I should see the payment or address page

@Smoke
Feature: Login check on Sauce demo

  Background:
    Given User is on the source demo website

    #E2E Shopping Checkout for standard_user
  Scenario Outline: Verify end to end shopping checkout process for standard users
    When User enter the username as "<userName>"
    And  User enter the password as "<password>"
    When User click on login button
    And  User verify land on shopping page
    And  User select product, click on add to cart
    And  User click on basket to do checkout
    When User click on checkout button
    And  User enter "<firstName>","<lastName>" and "<zipCode>"
    When User click on continue button, verify selected product
    Then User click on finish button, verify it
    Examples:
      | userName      | password     | firstName | lastName | zipCode |
      | standard_user | secret_sauce | David     | Wilson   | AL1 1LA |
      | standard_user | secret_sauce | Richard   | Dia      | HA1 4AU |
      | standard_user | secret_sauce | Harry     | Potter   | HA7 3BQ |

     #Error Scenarios
     #Scenario for problem_user
  Scenario Outline: Verify last name error message displayed for problem users
    When User enter the username as "<userName>"
    And  User enter the password as "<password>"
    When User click on login button
    And  User verify land on shopping page
    And  User select product, click on add to cart
    And  User click on basket to do checkout
    When User click on checkout button
    And  User enter "<firstName>","<lastName>" and "<zipCode>"
    Then User verify the error message for last name
    Examples:
      | userName     | password     | firstName | lastName | zipCode |
      | problem_user | secret_sauce | Nazeem    | Naji     | HA9 6HG |
      | problem_user | secret_sauce | Gulshan   | App      | HA9 6HG |

     #Scenario for error_user
  Scenario Outline: Verify finish button not clickable for error users
    When User enter the username as "<userName>"
    And  User enter the password as "<password>"
    When User click on login button
    And  User verify land on shopping page
    And  User select product, click on add to cart
    And  User click on basket to do checkout
    When User click on checkout button
    And  User enter "<firstName>","<lastName>" and "<zipCode>"
    When User click on continue button, verify selected product
    Then User verify finish button not work as expected
    Examples:
      | userName   | password     | firstName | lastName | zipCode |
      | error_user | secret_sauce | Pramukh   | Mahant   | HA2 1HP |
      | error_user | secret_sauce | Nelson    | Mah      | UB9 6AF |

     #Scenario for lock out user
  Scenario: Verify lock out error message for locked out users
    When User enter the username as "locked_out_user"
    And  User enter the password as "secret_sauce"
    When User click on login button
    And  User verify lock out errorMessage when enter invalid username and password

     #Sceanrio for performance_glitch_user
  Scenario: Verify login button take time to log in for performance_glitch_users
    When User enter the username as "performance_glitch_user"
    And  User enter the password as "secret_sauce"
    When User click on login button
    Then User verify the login time performance

     #Scenario for Visual_user
  Scenario: Verify checkout button visual on up for Visual users
    When User enter the username as "visual_user"
    And  User enter the password as "secret_sauce"
    When User click on login button
    When User click on basket to do checkout
    Then User verify checkout button visual on top page

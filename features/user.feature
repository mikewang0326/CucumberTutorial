Feature:
  Background:
    Given I am connected to the WOF database

  Scenario: Register an account by a new email
    Given The WOF database is reseted
    Given These information contains firstname "Jack" lastname "Ma" email "hi@gmail.com" and password "123456" are all valid
    And The email does not exists in the database
    When I Register an account by these information
    Then The account should be registered successfully, the information should as same as what I registered


  Scenario: Register an account by a exist email
    Given These information contains firstname "Jack" lastname "Ma" email "hi@gmail.com" and password "123456" are all valid
    And The email exists in the database
    When I Register an account by these information with exist email
    Then Screen will display error information
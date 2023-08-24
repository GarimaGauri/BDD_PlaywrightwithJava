Feature: Login
 Scenario Outline:Login to Application with Valid credentials
   Given User Access Douglas Application
   When user navigate to the login page
   And User enter username "<username>" and password "<password>"
   And User enables the Remember me checkbox "<rememberMe>"
   And User click on Login button
   Then Validate my account page
    And logout from the application
   Examples:
     | username | password | rememberMe|
    | gnarang31@gmail.com | Test12345 |true|

   Scenario Outline: Resetting Password with Invalid Email
      Given User Access Douglas Application
      When user navigate to the login page
      And User click on Forgot Password link on the login page
      And User provide my registered email address "<emailaddress>"
     And User click on the Submit button
      Then validate the error message if email address is invalid
     Examples:
      |emailaddress|
      |gnarang31gmail.com|

  Scenario Outline: Login Attempt with invalid credentials
    Given User Access Douglas Application
    When user navigate to the login page
    And User click on Login button
    Then validate an error message for blank email and password
    And User enter username "<username>" and password "<password>"
    And User click on Login button
    Then Validate an error message indicating that the login credentials are invalid
    Examples:
      | username | password |
      | gnarang33@gmail.com | Test12345 |

    Scenario Outline: Reset Password with valid email
      Given User Access Douglas Application
      When user navigate to the login page
      And User click on Forgot Password link on the login page
      And User provide my registered email address "<emailaddress>"
      And User click on the Submit button
      Then validate the confirmation dialog contains email "<emailaddress>"
      And close the Email sent confirmation dialog
      Examples:
        | emailaddress |
        | gnarang31@gmail.com |










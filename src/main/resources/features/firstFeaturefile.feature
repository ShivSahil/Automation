@parentTag
Feature: parabank bank testing

  # nothing in Background
  @customerCreation
  Scenario: US98765|User_Validating_New_Customer_Creation
    And COMMENT:"CREATING A NEW CUSTOMER"
    ### see how I am passing a @randomLastName which generates randomLastName
    When user entering details on signing up is easy page
      | First Name | Last Name       | Address                | City       | State      | Zip Code | Phone      | SSN  | Username         | Password | Confirm |
      | Shiv Sahil | @randomLastName | E-24 panjab University | Chandigarh | Chandigarh |   160014 | 8209060559 | 1099 | shivsahilguleri |     1234 |    1234 |
    And user clicks on "Register" button on "signing up is easy" page
    And COMMENT:"NEW CUSTOMER CREATED"
    And COMMENT:"VALIDATING THAT USER IS ABLE TO LOGIN"
    Then user validating userid "shivsahilguleri" is present on login page
    And COMMENT:"VALIDATED THAT USER IS ABLE TO LOGIN"

  @ExistingCustomer
  Scenario: US12345|User_Validating_Existing_Customer_Creation
    And COMMENT:"EXISTING CUSTOMER LOGIN"
    And user entering credentails on login page
      | username        | password |
      | shivsahilguleri |     1234 |
    And user clicks on "Log In" button on "login" page
    And COMMENT:"EXISTING CUSTOMER LOGIN COMPLETED"
    ### see how I am using variable in feature file, var_TotalOnAccountOverview
    And user retrieve "Total" field on "Account Overview" page and save in dynamic variable "var_TotalOnAccountOverview"
    And user clicks on "13677" link on "Account Overview" page
    And COMMENT:"VALIDATING ACCOUNT DETAILS PAGE"
    Then user validating detais on account details page
      | Account Type | Balance                    | Available                  |
      | CHECKING     | var_TotalOnAccountOverview | var_TotalOnAccountOverview |
    And COMMENT:"VALIDATED ACCOUNT DETAILS PAGE"

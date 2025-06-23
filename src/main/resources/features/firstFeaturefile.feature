@parentTag
Feature: Title of  feature file

  @individualTag1
  Scenario: US98153|User_Creating_New_Customer_And_Validating_Account_Details_Page
    And COMMENT:"CREATING A NEW CUSTOMER"
    And user entering details on signing up is easy page
      | searchBar         | otherField |
      | shiv sahil guleri | xyz        |
    And COMMENT:"NEW CUSTOMER CREATED"
    
    #And user retrieve "Total" field on "Account Overview" page and save in dynamic variable "var_TotalOnAccountOverview"
    #And user retrieve "Account Type:" field on "Account Details" page and save in dynamic variable "var_AccountTypeOnAccountDetails"
    #Then user validating detais on account details page
      #| Account Type                    | Balance                    | Available                  |
      #| var_AccountTypeOnAccountDetails | var_TotalOnAccountOverview | var_TotalOnAccountOverview |

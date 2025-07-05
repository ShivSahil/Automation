@parentTag
Feature: parabank bank testing

  @customerCreation
  Scenario: US98765|User_Validating_New_Customer_Creation
    And COMMENT:"CREATING A NEW CUSTOMER"
    ### see how I am passing a @randomLastName which generates randomLastName and randomNumber100000till999999
    When user entering details on signing up is easy page
      | First Name | Last Name       | Address                | City       | State      | Zip Code                      | Phone      | SSN  | Username        | Password | Confirm |
      | Shiv Sahil | @randomLastName | E-24 panjab University | Chandigarh | Chandigarh | @randomNumber100000till999999 | 8209060559 | 1099 | shivsahilguleri11 |     1234 |    1234 |
    And user clicks on "Register" button on "signing up is easy" page
    And COMMENT:"NEW CUSTOMER CREATED"
    And COMMENT:"VALIDATING THAT USER IS ABLE TO LOGIN"
    Then user validating userid "shivsahilguleri11" is present on login page
    And COMMENT:"VALIDATED THAT USER IS ABLE TO LOGIN"

  @ExistingCustomer
  Scenario: US12345|User_Validating_Existing_Customer_Creation
    And COMMENT:"EXISTING CUSTOMER LOGIN"
    And user entering credentails on login page
      | username        | password |
      | shivsahilguleri |     1234 |
    And user clicks on "Log In" button on "login" page
    And COMMENT:"EXISTING CUSTOMER LOGIN COMPLETED"
    ### see how I am using dynamic variable in feature file, var_TotalOnAccountOverview
    And user retrieve "Total" field on "Account Overview" page and save in dynamic variable "var_TotalOnAccountOverview"
    And user clicks on "14676" link on "Account Overview" page
    And COMMENT:"VALIDATING ACCOUNT DETAILS PAGE"
    Then user validating detais on account details page
      | Account Type | Balance                    | Available                  |
      | CHECKING     | var_TotalOnAccountOverview | var_TotalOnAccountOverview |
    And COMMENT:"VALIDATED ACCOUNT DETAILS PAGE"
 
# My innovation; 
# We have created many dataInputters like @randomNumber1till99 @todayDateInddMM @yesterdayDateInMM/dd/YYYY @randomDateFrom15/01/2020TillTodayInMMddYYY @randomDateFrom01/12/2020Till15/01/2029Indd-MM-YYYY @randomFirstName @randomLastName 
#  @clearOut @currentUser. using these dataInputters I was able to decease script development time

# above you might have seen that we can retrieve the textBox field inputted values/dropdown value /checkbox status/amount in Dollar in a dynamic variable 
# we can then perform addition/subtraction/division/multipliciation on these captued dynamic variables
#
# we can even capture these generated values in dynamic variables
#
# user entering details in xyz section on abc page
# |fieldName1| fieldName2 | fieldName3| fieldName4                   | 
# |shiv      | @randomName| @sqlQuery | var_fieldAmount=@randomAmount|

# user verify xyz section details on abc page
#  |field1 |field2          | 
#  |$100.23|var_fieldAmount |

# user verify xyz section details on abc page
#  |field1 |field2                       | 
#  |$100.23| var_Principal+ var_Interest |

# And user retrieve "Total" field on "Account Overview" page and save in dynamic variable "var_TotalOnAccountOverview"





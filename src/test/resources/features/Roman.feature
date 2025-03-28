Feature: Roman Scenario

  @VerifUser
  Scenario: Practice clicking buttons on different pages as a client
    Given the user is on the Docuport page
    When the user inserts "b1g1_client@gmail.com" into the "username" field on the "Login" page
    And the user inserts "Group1" into the "password" field on the "Login" page
    And the user clicks the "login" button on the "Login" page
    Then the user should be logged in successfully



#Feature: Roman Scenario
#
#  @VerifUser
#  Scenario: Practice click buttons on different pages as a client
#  Given user is on Docuport page
#  When user inserts "b1g1_client@gmail.com" to "username" field on "Login" page
#  When user inserts "Group1" to "password" field on "Login" page
#  And user clicks "login" button on "Login" page
#  Then User should login successfully

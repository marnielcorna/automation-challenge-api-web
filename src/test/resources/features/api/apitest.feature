@api_test
Feature: API testing

  @get_request
  Scenario:	Get available pets
    Given the 'PathForFindPetsByStatus' is called to 'GET' pets by status 'available'
    Then the response should be 200
    And the response body should contain the following attributes for each pet:
      | status   | available   |

  @post_request
  Scenario: Post new pet with status of available
    Given the 'PathForPostPet' is called to 'POST' a pet
    Then the response should be 200
    And the response body should contain the status 'available'


  @update_request
  Scenario:	Update pet status to sold
    Given the 'PathForUpdateAPet' is called to 'UPDATE' a pet
    Then the response should be 200
    And the response body should contain the status 'sold'


  @delete_request
  Scenario: Delete pet
    Given last updated pet
    * the 'PathToDeletePet' is called to 'DELETE' a pet
    Then the response should be 200
    And the response body should contain the following attributes:
      | code	  | 200      |
      | type      | unknown  |
      | message   | id       |
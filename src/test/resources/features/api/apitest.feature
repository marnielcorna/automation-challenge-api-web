@api_test
Feature: API testing

  @get_request
  Scenario:	Get available pets
    Given a pet status of 'available'
    When the 'https://petstore.swagger.io/v2/pet/findByStatus?status=available' is called to find pets by status
    Then the response should be 200
    And the response body should contain the following attributes for each pet:
      | attribute| value     |
      | status   | available |

  @post_request
  Scenario: Post new pet with status of available
    Given a pet with the following data:
    When the 'https://petstore.swagger.io/v2/pet' is called to 'post' a pet with the following data:
      | attribute| value       |
      | name	 | Thor        |
      | status   | available   |
#    Then the response should be 200
#    And the response body should contain the following attributes:
#      | "name"	 | "Thor"      |
#      | "status" | "available" |

#
#  @update_request @dos
#  Scenario:	Update pet status to 'sold'
#    Given a pet status of 'available'
#    When the api is called to 'update' a pet with the following data:
##      | "status" | "sold"      |
#    Then the response should be 200
#    And the response body should contain the following attributes:
##      | "status" | "sold"      |
##
#  @delete_request @dos
#  Scenario: Delete pet
#    Given a pet of id 'id pet'
#    When the api is called to 'delete' a pet with the following data:
##      | "Id"      |	| "Id Pet" |
#    Then the response should be 200
#    And the response body should contain the following attributes:
##      | "code" 	  | "200"      |
##      | "type"    | "unknown"  |
##      | "message" | "id"       |
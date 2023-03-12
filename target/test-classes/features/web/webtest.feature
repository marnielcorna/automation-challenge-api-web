@web_test
Feature: UI Test
  Background:
    Given access to the 'url' of the application on the 'test' environment

  @add_product_to_cart @workflow_one @regression
  Scenario Outline: Add product to cart
    Given the 'Home' page
    When Navigate to <category>
    * Navigate to <product>
    * click on 'Add to cart'
    Then The product is added the purchase cart

    Examples:
      | category | product        |
      | "Laptop" | "Sony vaio i5" |
      | "Laptop" | "Dell i7 8gb"  |

  @place_order @workflow_one @regression
  Scenario:	Place order
    Given the 'Cart' page
    When click on 'place order'
    * click on 'OK'
    Then the system performs the purchase and displays a confirmation message
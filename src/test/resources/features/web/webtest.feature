@web_test
Feature: UI Test
  Background:
    Given access to the 'BlazeMeterBaseURI' the application

  @navigation
  Scenario: Add product to cart
    Given the Home page
    When Navigate through products in Categories
      | Phones     |
      | Laptops    |
      | Monitors   |
    Then we log out

  @add_product_to_cart
  Scenario Outline: Add products to Cart
    Given the Home page
     When Navigate to '<Menu>'
        * Select product '<Product>', add to 'cart'
        * 'Accept' pop up Alert
    Then we log out

    Examples:
      | Menu       | Product      |
      | Laptops    | Sony vaio i5 |
      | Laptops    | Dell i7 8gb  |


  @delete_product
  Scenario: Delete Product
    Given Navigate to 'Home' page
     When Navigate to 'Cart'
        * Delete product 'Dell i7 8gb'
    Then the product 'Dell i7 8gb' is not present
        * we log out

  @place_order
  Scenario:	Place order
    Given Navigate to 'Home' page
     When Navigate to 'Cart'
        * click on 'Place Order' button
        * fill the form fields with the following data
          | name   | country | city     | card        | month  | year  |
          | Marcos | Spain   | Zaragoza | 12345678910 | July   | 2023  |
        * click on 'Purchase' button
     Then validate the corresponding amount of the product in the displayed modal
        * We exit the application

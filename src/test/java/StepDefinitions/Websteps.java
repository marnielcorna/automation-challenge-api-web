package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Websteps {

    @Given("access to the {string} of the application on the {string} environment")
    public void accessToTheUrlOfTheApplicationOnTheTestEnvironment(String url, String environment) {
        System.out.println("Access the application..");
    }

    @Given("the {string} page")
    public void the_page(String page) {
        System.out.println("given the: " + page + "Page");
    }

    @When("Navigate to {string}")
    public void navigateToCategory(String page) {
        System.out.println("Navigate to: " + page + "Page");
    }

    @When("click on {string}")
    public void click_on(String button) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On: " + button + "button");
    }
    @Then("the system performs the purchase and displays a confirmation message")
    public void the_system_performs_the_purchase_and_displays_a_confirmation_message() {
        System.out.println("The system..");
    }

    @Then("The product is added the purchase cart")
    public void theProductIsAddedThePurchaseCart() {
        System.out.println("The product...");
    }

}

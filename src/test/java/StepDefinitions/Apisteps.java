package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Apisteps {

    @Given("a pet status of {string}")
    public void aPetStatusOfAvailable(String status) {
        System.out.println("Status: " + status);
    }

    @Given("a pet object")
    public void aPetObjectWithTheFollowingAttributes() {
        System.out.println("object");
    }

    @Given("a pet of id {string}")
    public void aPetOfIdIdPet(String id) {
        System.out.println("Id: " + id);
    }

    @When("the api is called to {string} pets by status")
    public void theApiIsCalledToFindPetsByStatus(String request) {
        System.out.println("Type of request: " + request);
    }

    @When("the api is called to {string} a pet with the following data:")
    public void theApiIsCalledToAddAPetWithTheTheFollowingData(String request) {
        System.out.println("Type of request: " + request);
    }

    @And("the response body should contain the following attributes for each pet:")
    public void theResponseBodyShouldContainTheFollowingAttributesForEachPet() {
        System.out.println("Contains");
    }


    @And("the response body should contain the following attributes:")
    public void theResponseBodyShouldContainTheFollowingAttributes() {
        System.out.println("Contains");
    }

    @Then("the response should be {int} OK")
    public void theResponseShouldBeOK(int status) {
        System.out.println("Contains: " + status);
    }


    @Given("hello")
    public void hello() {
        System.out.println("Hola");
    }
}

package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageobjects.Api;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Apisteps {

    private Response response;
    private RequestSpecification request;


    @Given("a pet status of {string}")
    public void givenAPetStatus(String status) {
        request = RestAssured.given().param("status", status);
    }

    @Given("a pet object")
    public void givenAPetObject() {
        System.out.println("object");
    }

    @Given("a pet of id {string}")
    public void aPetOfIdIdPet(String id) {
        System.out.println("Id: " + id);
    }

    @When("the {string} is called to find pets by status")
    public void theApiIsCalledToFindPetsByStatus(String api, String action) {
        response = request.when().get(api);
    }

    @When("the api is called to {string} a pet with the following data:")
    public void theApiIsCalledToAddAPetWithTheTheFollowingData(String request) {
        System.out.println("Type of request: " + request);
    }

    @And("the response body should contain the following attributes for each pet:")
    public void responseBodyContainsAttributes(DataTable attributes) {
        List<Map<String, String>> rows = attributes.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String attribute = row.get("attribute");
            String value = row.get("value");
            // Aquí puedes verificar que el cuerpo de la respuesta contiene los atributos y valores esperados para cada pet
        }
    }


    @And("the response body should contain the following attributes:")
    public void theResponseBodyShouldContainTheFollowingAttributes() {
        System.out.println("Contains");
    }

    @Then("the response should be {int}")
    public void theResponseShouldBeOK(int status) {
        assertEquals(response.getStatusCode(), 200);
    }


    @Given("hello")
    public void hello() {
        System.out.println("Hola");
    }


}

package StepDefinitions;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static pageobjects.Api.*;


public class Apisteps {

    private Response response;
    private RequestSpecification request;


    @Given("a pet status of {string}")
    public void givenAPetStatus(String status) {
        request = RestAssured.given().param("status", status);

    }

    @Given("a pet with the following data:")
    public void givenAPetWithJsonData() {

    }

    @Given("a pet of id {string}")
    public void aPetOfIdIdPet(String id) {
        System.out.println("Id: " + id);
    }

    @When("the {string} is called to find pets by status")
    public void theApiIsCalledToFindPetsByStatus(String api) {
        response = request.when().get(api);
    }

    @When("the {string} is called to {string} a pet with the following data:")
    public void apiCalledToAddPetWithData(String api, String request, DataTable table) {
//        request = RestAssured.given().param("status", status);

        System.out.println("api: " + api);
        System.out.println("Type of request: " + request);
        System.out.println("Table: " + table);

    }

    @And("the response body should contain the following attributes for each pet:")
    public void responseBodyContainsAttributes(DataTable table) {
        Map<String, String> result = getKeyValuesFromTable(table);
        String attribute = result.get("attribute");
        String value = result.get("value");
        String jsonStr = response.getBody().asString();
        assertKeyValuesFromResponse(jsonStr, attribute, value);
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

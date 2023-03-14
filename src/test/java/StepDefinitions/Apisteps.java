package StepDefinitions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import javax.swing.*;
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
    public void givenAPetWithJsonData(DataTable table) {
        Map<String, String> result = table.asMap(String.class, String.class);
        String value = result.get("object");
        this.request = RestAssured.given().contentType("application/json").body(value);
    }

    @Given("a pet of id {string}")
    public void aPetOfIdIdPet(String id) {
        System.out.println("Id: " + id);
    }

    @When("the {string} is called to find pets by status")
    public void theApiIsCalledToFindPetsByStatus(String api) {
        response = request.when().get(api);
    }

    @When("the {string} is called to {string} a pet")
    public void apiCalledToPostPetWithData(String api, String request) {
        this.response = this.request.when().post(api);
    }

    @And("the response body should contain the following attributes for each pet:")
    public void responseBodyContainsAttributes(DataTable table) {
        Map<String, String> expectedAttributes = table.asMap(String.class, String.class);
        String expectedStatus = expectedAttributes.get("status");
        String jsonStr = response.getBody().asString();
        assertKeyValueFromResponse(jsonStr, "status", expectedStatus);
    }

    @And("the response body should contain the following attributes:")
    public void reponseShouldContainThoseAttributes(DataTable tableTwo) {
        Map<String, String> expectedAttributes = tableTwo.asMap(String.class, String.class);
        String expectedStatus = expectedAttributes.get("status");
        String expectedName = expectedAttributes.get("name");

        String jsonStr = response.getBody().asString();
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();

        String jsonStatus = jsonObject.get("status").getAsString();
        String jsonName = jsonObject.get("name").getAsString();

        assertEquals(expectedStatus, jsonStatus);
        assertEquals(expectedName, jsonName);
    }

    @When("the {string} is called to {string} a pet with the following data:")
    public void requestUpdateToApiWithABody(int arg0) {

    }

    @Then("the response should be {int}")
    public void theResponseShouldBeOK(int status) {
        assertEquals(this.response.getStatusCode(), 200);
    }

}

package StepDefinitions;
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
        System.out.println("TABLA DEL ATTRIBUTE1"+ table);
        Map<String, String> result = getKeyValuesFromTable(table, "attribute1", "value1");
        String value = result.get("value1");

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
        System.out.println("TABLA DEL ATTRIBUTE"+ table);
        Map<String, String> result = getKeyValuesFromTable(table, "attribute", "value");
        String attribute = result.get("attribute");
        String value = result.get("value");
        String jsonStr = response.getBody().asString();
        assertKeyValuesFromResponse(jsonStr, attribute, value);
    }

    @And("the response body should contain the following attributes:")
    public void reponseShouldContainThoseAttributes(DataTable tableTwo) {
        System.out.println("TABLA DEL ATTRIBUTE2"+ tableTwo);
        Map<String, String> tablePost = getKeyValuesFromTable(tableTwo, "attribute2", "value2");
        System.out.println("EL TABLE POST ES:------"+tablePost);
        String attribute = tablePost.get("attribute2");
        String value = tablePost.get("value2");
        String jsonStr = response.getBody().asString();

//        assertKeyValuesFromResponse(jsonStr, attribute, value);
    }

    @Then("the response should be {int}")
    public void theResponseShouldBeOK(int status) {
        assertEquals(this.response.getStatusCode(), 200);
    }


    @Given("hello")
    public void hello() {
        System.out.println("Hola");
    }


}

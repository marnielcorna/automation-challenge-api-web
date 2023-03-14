package StepDefinitions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apiguardian.api.API;
import pageobjects.Api;

import javax.swing.*;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static pageobjects.Api.*;


public class Apisteps {

    private Response response;
    private RequestSpecification request;
    public static String idLastPostPet;

    @Given("a pet status of {string}")
    public void givenAPetStatus(String status) {
        request = RestAssured.given().param("status", status);

    }

    @Given("a pet with the following data:")
    public void givenAPetWithJsonData(DataTable table) {
        Map<String, String> result = table.asMap(String.class, String.class);
        String body = result.get("body");
        request = RestAssured.given().contentType("application/json").body(body);
    }

    @Given("the last pet we prepare the new object")
    public void givenTheLastPetObjectWePrepareNewObject() {
        JsonObject pet = new JsonObject();
        pet.addProperty("id", idLastPostPet);
        pet.addProperty("status", "sold");
        request = RestAssured.given().contentType("application/json").body(pet.toString());
        System.out.println("NUEVO PET PARA ACTUALIZAR"+ request);
    }

    @When("the {string} is called to find pets by status")
    public void theApiIsCalledToFindPetsByStatus(String api) {
        response = request.when().get(api);
    }

    @When("the {string} is called to {string} a pet")
    public void apiCalledToPostPetWithData(String api, String request) {
        response = this.request.when().post(api);
        System.out.println("EL NUEVO NOMBRE DE LA RESPUESTAS ES::::::" + response.getBody().asString());
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

        System.out.println("RESPONSE BODY::::::::::"+ jsonStr);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();

        String jsonStatus = jsonObject.get("status").getAsString();
        String jsonName = jsonObject.get("name").getAsString();
        Apisteps.idLastPostPet = jsonObject.get("id").getAsString();
        System.out.println("ESTE ES EL AND DEL ID:::::::::::::"+Apisteps.idLastPostPet);

        assertEquals(expectedStatus, jsonStatus);
        assertEquals(expectedName, jsonName);
    }

    @When("the {string} is called to {string} a pet with the following data:")
    public void requestUpdateToApiWithABody(String api, String request) {
        JsonObject pet = new JsonObject();
        pet.addProperty("id", idLastPostPet);
        pet.addProperty("status", "sold");

        switch (request){
            case "update":

                response = this.request.when().post(api);
                break;
            case "delete":
                break;
        }
    }


    @Then("the response should be {int}")
    public void theResponseShouldBeOK(int status) {
        assertEquals(response.getStatusCode(), 200);
    }


}

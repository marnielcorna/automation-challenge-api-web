package StepDefinitions;

import Utils.Operations;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.SortedMap;

import static pageobjects.Api.assertKeyValueFromResponse;


public class Apisteps extends Operations {
    public static Response response;
    public static Integer lastIdPetPosted;

    @Given("the {string} is called to {string} pets by status")
    public void theApiIsCalledToGETPetsByStatus(String api, String request) throws Exception {
        try {
            if (request.equalsIgnoreCase("GET")) {
                response = performGET(api);
            }
        } catch (Exception e) {
            System.out.println("Error during the GET perform:" + e);
        }

    }

    @Given("the {string} is called to {string} a pet")
    public void theApiIsCalledToPerform(String path, String request) throws FileNotFoundException {
        if (request.equalsIgnoreCase("POST")) {
            response = performPOST(path);
        }else if (request.equalsIgnoreCase("UPDATE")) {

            Integer lastIdPet = lastIdPetPosted;
            System.out.println("EL LASTID PET ES ESTE::::::::::::" + lastIdPet);
            response = performPUT(path, lastIdPet);
        }
    }

    @Then("the response should be {int}")
    public void theResponseShouldBe(int response_code){
        System.out.println("Status Code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(), response_code);
    }

    @And("the response body should contain the following attributes for each pet:")
    public void responseShouldContainsFollowingAttributes(DataTable table) {
        Map<String, String> expectedAttributes = table.asMap(String.class, String.class);
        String expectedStatus = expectedAttributes.get("status");
        String jsonStr = response.getBody().asString();
        assertKeyValueFromResponse(jsonStr, "status", expectedStatus);
    }

    @And("the response body should contain the status {string}")
    public void responseShouldContainsAttributes(String status) {
        String responseBody = response.asString();
        JsonPath jsonPath = JsonPath.from(responseBody);
        String actualStatus = jsonPath.getString("status");
        lastIdPetPosted = jsonPath.getInt("id");
        System.out.println("EL ESTATUS DE LA VALIDACION ES------------------" + actualStatus);
        Assert.assertEquals(actualStatus, status);
    }
}

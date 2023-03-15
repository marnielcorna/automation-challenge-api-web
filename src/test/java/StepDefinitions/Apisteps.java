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

import static pageobjects.Api.assertKeyValueFromResponse;


public class Apisteps extends Operations {
    public static Response response;
    public static long lastIdPetPosted;
    public static long lastIdPetToUpdated;

    @Given("the {string} is called to {string} pets by status {string}")
    public void theApiIsCalledToGETPetsByStatus(String api, String request, String status) {
        try {
            if (request.equalsIgnoreCase("GET")) {
                response = performGET(api, status);
            }
        } catch (Exception e) {
            System.out.println("Error during the GET perform:" + e);
        }

    }

    @Given("the {string} is called to {string} a pet")
    public void theApiIsCalledToPerform(String path, String request) throws FileNotFoundException {
        if (request.equalsIgnoreCase("POST")) {
            response = performPOST(path);

        } else if (request.equalsIgnoreCase("UPDATE")) {
            long lastIdPet = lastIdPetPosted;
            response = performPUT(path, lastIdPet);

        } else if (request.equalsIgnoreCase("DELETE")) {
            long lastIdPet = lastIdPetPosted;
            response = performDELETE(path, lastIdPet);
            String body_res = response.getBody().asString();
        }
    }

    @Given("last updated pet")
    public void givenTheLastPet() {
        lastIdPetToUpdated = lastIdPetPosted;

    }

    @Then("the response should be {int}")
    public void theResponseShouldBe(int response_code) {
        System.out.println("The status code is: ------" + response.statusCode() + " -----");
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
        lastIdPetPosted = Long.parseLong(jsonPath.getString("id"));

        Assert.assertEquals(actualStatus, status);
    }

    @And("the response body should contain the following attributes:")
    public void responseForDeletedPetShouldContainAttributes(DataTable table_delete) {
        String responseBody = response.asString();
        JsonPath jsonPath = JsonPath.from(responseBody);
        String actual_id = jsonPath.getString("message");

        Map<String, String> table = table_delete.asMap(String.class, String.class);
        for (String value : table.values()) {
            switch (value) {
                case "200":
                    Assert.assertEquals(jsonPath.getString("code"), value);
                    break;
                case "unknown":
                    Assert.assertEquals(jsonPath.getString("type"), value);

                    break;
                case "id":
                    String expected_id = String.valueOf(lastIdPetToUpdated);
                    Assert.assertEquals(actual_id, expected_id);
                    break;
            }

        }
    }
}

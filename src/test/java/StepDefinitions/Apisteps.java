package StepDefinitions;

import Utils.Operations;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.SortedMap;

import static pageobjects.Api.assertKeyValueFromResponse;


public class Apisteps extends Operations {
    public static Response response;

    @Given("the {string} is called to {string} pets by status")
    public void theApiIsCalledToGETPetsByStatus(String api, String request) throws FileNotFoundException {
        try {
            if (request.equalsIgnoreCase("GET")) {
                response = performGET(api);
            }
        } catch (Exception e) {
            System.out.println("CATCH......" + e);
        }

    }

    @Given("the {string} is called to {string} a pet")
    public void theApiIsCalledToPOSTAPet(String api, String request) throws FileNotFoundException {
        if (request.equalsIgnoreCase("POST")) {
            response = performPOST(api);
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
}

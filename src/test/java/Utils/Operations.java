package Utils;

import Resources.APIResources;
import Resources.BodyPets;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;

public class Operations {
    static Response response;
    RequestSpecification reqspec;

    public Response performGET(String api, String status) throws FileNotFoundException {
        APIResources resourceAPI= APIResources.valueOf(api);
        reqspec = new BaseBuilder().placeSpecBuilder();

        if(api.equalsIgnoreCase("PathForFindPetsByStatus")) {
            reqspec = RestAssured.given().spec(reqspec).params("status",status);
        }
        response = reqspec.get(resourceAPI.getResource());
        return response;
    }

    public Response performPOST(String api) throws FileNotFoundException {
        APIResources resourceAPI= APIResources.valueOf(api);

        reqspec = new BaseBuilder().placeSpecBuilder();

        if(api.equalsIgnoreCase("PathForPostPet")) {
            reqspec = RestAssured.given().spec(reqspec).body(BodyPets.addBodyPet());
        }
        response = reqspec.post(resourceAPI.getResource()).then().extract().response();
        return response;
    }

    public Response performPUT(String api, long lastIdPet) throws FileNotFoundException {
        APIResources resourceAPI= APIResources.valueOf(api);

        reqspec = new BaseBuilder().placeSpecBuilder();

        if(api.equalsIgnoreCase("PathForUpdateAPet")) {
            reqspec = RestAssured.given().spec(reqspec).body(BodyPets.updateAPet(lastIdPet));
        }
        response = reqspec.put(resourceAPI.getResource()).then().extract().response();
        return response;
    }

    public Response performDELETE(String api, long lastIdPet) throws FileNotFoundException {
        APIResources resourceAPI= APIResources.valueOf(api);

        reqspec = new BaseBuilder().placeSpecBuilder();

        if(api.equalsIgnoreCase("PathToDeletePet")) {
            reqspec = RestAssured.given().spec(reqspec);
        }
        response = reqspec.delete(resourceAPI.getResource()+lastIdPet).then().extract().response();

        return response;
    }




}


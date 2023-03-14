package Utils;

import io.restassured.response.Response;

import java.io.FileNotFoundException;

public class Operations {
    static Response response;

    public Response performPOSTcall(String apiName) throws FileNotFoundException {
        APIResources resourceAPI= APIResources.valueOf(apiName);
    }
}


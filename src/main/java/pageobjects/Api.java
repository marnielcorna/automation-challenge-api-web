package pageobjects;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Api {

    public static void assertKeyValueFromResponse(String json, String key, String value) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.has(key)) {
                String actualStatus = jsonObject.get(key).getAsString();
                assertEquals(actualStatus, value);
            } else {
                String id = jsonObject.get("id").getAsString();
                fail("The'" + key + "' doesn't exist in json object.");
            }
        }
    }

}

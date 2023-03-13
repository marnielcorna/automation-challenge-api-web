package pageobjects;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Api {

    public static void assertKeyValuesFromResponse(String json, String key, String value) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.has(key)) {
                String actualStatus = jsonObject.get("status").getAsString();
                assertEquals(actualStatus, value);
            } else {
                String id = jsonObject.get("id").getAsString();
                fail("The'" + key + "' doesn't exist in json object.");
            }
        }
    }

    public static Map<String, String> getKeyValuesFromTable(DataTable attributes) {
        List<Map<String, String>> rows = attributes.asMaps(String.class, String.class);
        String value = null;
        String attribute = null;
        for (Map<String, String> row : rows) {
            attribute = row.get("attribute");
            value = row.get("value");
        }
        Map<String, String> result = new HashMap<>();
        result.put("attribute", attribute);
        result.put("value", value);
        return result;
    }

}

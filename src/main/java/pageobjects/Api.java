package pageobjects;
import com.google.gson.*;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Api {

    public static void assertStatusFromJson(String json, String key, String value) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            String statusStr = jsonObject.get(key).getAsString();
            assertEquals(value, statusStr);
        }
    }
//    public void responseBodyContainsAttributes(DataTable attributes) {
//        List<Map<String, String>> rows = attributes.asMaps(String.class, String.class);
//        for (Map<String, String> row : rows) {
//            String attribute = row.get("attribute");
//            String value = row.get("value");
//            // Aquí puedes verificar que el cuerpo de la respuesta contiene los atributos y valores esperados para cada pet
//        }
//    }

}

package Resources;

import java.util.ArrayList;
import java.util.HashMap;

public class BodyPets {

    public static HashMap<String, Object> addBodyPet() {
        HashMap<String, Object> addpet = new HashMap<String, Object>();
        addpet.put("id", 0);

        HashMap<String, Object> category = new HashMap<String, Object>();
        category.put("id", 0);
        category.put("name", "string");
        addpet.put("category", category);

        addpet.put("name", "doggie");

        ArrayList<String> photoUrls = new ArrayList<String>();
        photoUrls.add("string");
        addpet.put("photoUrls", photoUrls);

        ArrayList<HashMap<String, Object>> tags = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tag = new HashMap<String, Object>();
        tag.put("id", 0);
        tag.put("name", "string");
        tags.add(tag);
        addpet.put("tags", tags);

        addpet.put("status", "available");

        System.out.println("ESTE DEBERIA SER NUESTRO OBJETO:::::::::::::::::::"+ addpet);
        return addpet;


//    public static HashMap<String, Object> deletePayload(String placeId) {
//        HashMap<String, Object> deleteplacemap = new HashMap<String, Object>();
//        deleteplacemap.put("place_id", placeId);
//        return deleteplacemap;
    }
}

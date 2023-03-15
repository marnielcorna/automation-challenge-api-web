package Resources;

import java.util.ArrayList;
import java.util.HashMap;

public class BodyPets {

    public static HashMap<String, Object> addBodyPet() {
        HashMap<String, Object> addpet = new HashMap<String, Object>();
        addpet.put("id", 0);

        HashMap<String, Object> category = new HashMap<String, Object>();
        category.put("id", 0);
        category.put("name", "category");
        addpet.put("category", category);

        addpet.put("name", "LOKI");

        ArrayList<String> photoUrls = new ArrayList<String>();
        photoUrls.add("Photos");
        addpet.put("photoUrls", photoUrls);

        ArrayList<HashMap<String, Object>> tags = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tag = new HashMap<String, Object>();
        tag.put("id", 0);
        tag.put("name", "tag");
        tags.add(tag);
        addpet.put("tags", tags);
        addpet.put("status", "available");

        return addpet;


    }
    public static HashMap<String, Object> updateAPet(long lastIdPet) {
        HashMap<String, Object> bodyRequest = new HashMap<String, Object>();
        bodyRequest.put("id", lastIdPet);

        HashMap<String, Object> category = new HashMap<String, Object>();
        category.put("id", 0);
        category.put("name", "category");
        bodyRequest.put("category", category);

        bodyRequest.put("name", "LOKI");

        ArrayList<String> photoUrls = new ArrayList<String>();
        photoUrls.add("Photos");
        bodyRequest.put("photoUrls", photoUrls);

        ArrayList<HashMap<String, Object>> tags = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tag = new HashMap<String, Object>();
        tag.put("id", 0);
        tag.put("name", "tag");
        tags.add(tag);
        bodyRequest.put("tags", tags);
        bodyRequest.put("status", "sold");

        return bodyRequest;


    }

}

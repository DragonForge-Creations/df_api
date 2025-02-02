package me.quickscythe.dragonforge.api.tests;

import me.quickscythe.dragonforge.api.utils.Metadata;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataTests {

    @Test
    void testMetadata() {
        JSONObject data = new JSONObject();
        data.put("name", "John Doe");
        data.put("age", 25);
        JSONObject nestedData = new JSONObject();
        nestedData.put("city", "New York");
        nestedData.put("country", "USA");
        data.put("address", nestedData);
        Metadata metadata = Metadata.of(data);
        assertTrue(metadata.get("address").value("city", String.class).equals("New York"));
    }
}

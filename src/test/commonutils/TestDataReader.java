package commonutils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TestDataReader {
    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File("src/testdata.json"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data JSON", e);
        }
    }

    public static JsonNode getTestData(String key) {
        return rootNode.get(key);
    }
}
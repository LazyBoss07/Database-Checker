package com.example.ctsapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@NoArgsConstructor
 public class JsonFileWriterTest {

    @Test
    void updateJsonFile_ModifyExistingKey() throws IOException {
        // Arrange
        String filePath = "test.json";
        String existingKey = "existingKey";
        String existingStatus = "existingStatus";
        String existingStatusCode = "existingStatusCode";
        JsonResp jsonResp = new JsonResp(existingKey, existingStatus, existingStatusCode);

        // Create a test JSON file with an existing key
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> existingDatabase = new HashMap<>();
        existingDatabase.put("status", existingStatus);
        existingDatabase.put("statusCode", existingStatusCode);
        jsonMap.put(existingKey, existingDatabase);
        objectMapper.writeValue(new File(filePath), jsonMap);

        // Act
        JsonFileWriter.updateJsonFile(filePath, jsonResp);

        // Assert
        Map<String, Object> updatedJsonMap = objectMapper.readValue(new File(filePath), Map.class);
        assertTrue(updatedJsonMap.containsKey(existingKey));
        Map<String, Object> updatedDatabase = (Map<String, Object>) updatedJsonMap.get(existingKey);
        assertEquals(existingStatus, updatedDatabase.get("statusCode"));
        assertEquals(existingStatusCode, updatedDatabase.get("status"));
    }

    @Test
    void updateJsonFile_AddNewKey() throws IOException {
        // Arrange
        String filePath = "test.json";
        String newKey = "newKey";
        String newStatus = "newStatus";
        String newStatusCode = "newStatusCode";
        JsonResp jsonResp = new JsonResp(newKey, newStatus, newStatusCode);

        // Create a test JSON file without the new key
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        objectMapper.writeValue(new File(filePath), jsonMap);

        // Act
        JsonFileWriter.updateJsonFile(filePath, jsonResp);

        // Assert
        Map<String, Object> updatedJsonMap = objectMapper.readValue(new File(filePath), Map.class);
        assertTrue(updatedJsonMap.containsKey(newKey));
        Map<String, Object> updatedDatabase = (Map<String, Object>) updatedJsonMap.get(newKey);
        assertEquals(newStatus, updatedDatabase.get("statusCode"));
        assertEquals(newStatusCode, updatedDatabase.get("status"));
    }
}

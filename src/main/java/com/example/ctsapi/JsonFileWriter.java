package com.example.ctsapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonFileWriter {
    public static void updateJsonFile(String filePath, JsonResp jsonResp) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // Read the existing JSON file into a Map
            Map<String, Object> jsonMap = objectMapper.readValue(new File(filePath), Map.class);

            // Check if the key exists
            if (jsonMap.containsKey(jsonResp.getName())) {
                // Modify the status and statusCode of the specific key
                Map<String, Object> database = (Map<String, Object>) jsonMap.get(jsonResp.getName());
                database.put("status", jsonResp.getStatus());
                database.put("statusCode", jsonResp.getStatusCode());
            } else {
                // Add a new key-value pair to the Map
                Map<String, Object> newDatabase = new HashMap<>();
                newDatabase.put("status", jsonResp.getStatus());
                newDatabase.put("statusCode", jsonResp.getStatusCode());
                jsonMap.put(jsonResp.getName(), newDatabase);
            }

            // Write the updated Map back to the JSON file
            objectMapper.writeValue(new File(filePath), jsonMap);
            System.out.println("JSON file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

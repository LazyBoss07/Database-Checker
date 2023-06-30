////package com.example.ctsapi;
////
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////import java.util.ArrayList;
////import java.util.Map;
////
////@RestController
////@RequestMapping(path="/demo")
////public class DBControler {
////    private static boolean isPinging=true;
////    @PostMapping("/getter")
////    public Map<String, DB> start(@RequestBody DBALL dbAll){
////        List<DB> dblist = new ArrayList<>();
////        System.out.println("**************************************");
////        System.out.println(dbAll.getDatabases().toString());
////        System.out.println("**************************************");
////        int x=0;
////        String filePath="D:\\Projects Test\\ctsapi\\src\\main\\java\\Response.json";;
////        while (isPinging) {
////
////            for (String key : dbAll.getDatabases().keySet()) {
////                DB db = dbAll.getDatabases().get(key);
////                System.out.println("[-----------------"+key+"------------------]");
////                DatabaseConnection dbcon=new DatabaseConnection(db.url, db.username, db.password,db.type);
////                System.out.println("************************ "+ dbcon.testConnection()+" ********************************");
////                JsonResp jsonResp = new JsonResp(key, dbcon.testConnection());
//////            JsonFileWriter jw=new JsonFileWriter();
////
////
////
////                // Specify the file path where you want to save the JSON file
////
////                JsonFileWriter modifyAttributeIfKeyPresent(jsonResp,key);
////
////                // Write the JSON object to a file
////                JsonFileWriter writeJsonToFile(jsonResp);
////            }
////
////            System.out.println( "----------------- Round "+ (++x) +" -----------------" );
////        }
////
////        return dbAll.getDatabases();
////    }
////
////
////    @GetMapping("/stop-ping")
////    public String stopPing() {
////        if (!isPinging) {
////            return "No active ping request to stop.";
////        }
////
////        // Stop ping request
////        DBControler.isPinging = false;
////        // Implement your logic to stop the ongoing ping request
////
////        return "Ping request stopped.";
////    }
////}
//package com.example.ctsapi;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.http.ResponseEntity;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JsonFileWriter {
//    //    public static void writeToJsonFile(JsonResp jsonResp, String filePath) {
////        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
////
////        try {
////            objectMapper.writeValue(new File(filePath), jsonResp);
////            System.out.println("JSON file created successfully.");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//    public static void updateJsonFile(String filePath,JsonResp jsonResp) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        try {
//            // Read the existing JSON file into a Map
//            Map<String, Object> jsonMap = objectMapper.readValue(new File(filePath), Map.class);
//            String key=jsonResp.name;
//            // Check if the key "name" exists
//            if (!jsonMap.isEmpty() && jsonMap.containsKey("name")) {
//                // Modify the status of the specific key
//                if (jsonMap.get("name").equals(key)) {
//                    jsonMap.put("status", jsonResp.Status);
//                    jsonMap.put("statusCode", jsonResp.statusCode);
//
//                }
//            } else {
//                // Add the key-value pair to the Map
//                jsonMap.put("name", key);
//                jsonMap.put("statusCode", jsonResp.statusCode);
//                jsonMap.put("status", jsonResp.Status);
//
//            }
//
//            // Write the updated Map back to the JSON file
//            objectMapper.writeValue(new File(filePath), jsonMap);
//            System.out.println("JSON file updated successfully.");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}

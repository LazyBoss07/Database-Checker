package com.example.ctsapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(path="/demo")
public class DBController {

    static boolean isPinging;
    @PostMapping("/getter")
    public Map<String, DB> start(@RequestBody DBALL dbAll){
        isPinging=true;

        System.out.println("**************************************");
        System.out.println(dbAll.getDatabases().toString());
        System.out.println("**************************************");
        AtomicInteger x= new AtomicInteger();
        String filePath="D:\\Projects Test\\ctsapi\\src\\main\\java\\Response.json";
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {

        for (String key : dbAll.getDatabases().keySet()) {
            DB db = dbAll.getDatabases().get(key);
            System.out.println("[-----------------"+key+"------------------]");
            DatabaseConnection dbcon=new DatabaseConnection(db.url, db.username, db.password,db.type);
            ResponseEntity<String> resp= dbcon.testConnection();
            System.out.println("************************ "+resp+" ********************************");
            String statusCode = resp.getStatusCode().toString();
            String statusBody = resp.getBody();
            JsonResp jsonResp = new JsonResp(key,statusCode,statusBody);
            JsonFileWriter.updateJsonFile(filePath,jsonResp);

        }

            System.out.println( "----------------- Round "+ (x.incrementAndGet()) +" -----------------" );
        }, 0, 2, TimeUnit.MINUTES);

        return dbAll.getDatabases();
    }


    @GetMapping("/stop-ping")
    public String stopPing() {
        if (!isPinging) {
            return "No active ping request to stop.";
        }

        // Stop ping request
        DBController.isPinging = false;
        // Implement your logic to stop the ongoing ping request

        return "Ping request stopped.";
    }
}

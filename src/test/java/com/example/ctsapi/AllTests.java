package com.example.ctsapi;

import com.example.ctsapi.CtsapiApplicationTests;
import com.example.ctsapi.DBControllerTest;
import com.example.ctsapi.DatabaseConnectionTest;
import com.example.ctsapi.JsonFileWriterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        DBControllerTest.class,
        DatabaseConnectionTest.class,
        JsonFileWriterTest.class,


})
public class AllTests {
    // This class can be empty
}
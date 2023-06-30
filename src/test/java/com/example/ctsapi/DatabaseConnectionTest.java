package com.example.ctsapi;

import com.example.ctsapi.DatabaseConnection;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@NoArgsConstructor
public class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @BeforeEach
    public void setup() {
        // Set up the DatabaseConnection object with test configuration
        String url = "jdbc:mysql://localhost:3306/api";
        String username = "root";
        String password = "root";
        String type = "mysql";

        databaseConnection = new DatabaseConnection(url, username, password, type);
    }

    @Test
    public void testConnection_Successful() {
        // Act
        ResponseEntity<String> response = databaseConnection.testConnection();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Successful", response.getBody());
    }

    @Test
    public void testConnection_ConnectionFailed() {
        // Arrange
        databaseConnection = new DatabaseConnection("invalidurl", "invaliduser", "invalidpass", "mysql");

        // Act
        ResponseEntity<String> response = databaseConnection.testConnection();

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Connection failed!", response.getBody());
    }

    @Test
    public void testConnection_JdbcDriverNotFound() {
        // Arrange
        databaseConnection = new DatabaseConnection("jdbc:sqlserver://localhost:1433;databaseName=mydatabase", "root", "root", "ms_sql");

        // Act
        ResponseEntity<String> response = databaseConnection.testConnection();

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("JDBC driver not found!", response.getBody());
    }
}

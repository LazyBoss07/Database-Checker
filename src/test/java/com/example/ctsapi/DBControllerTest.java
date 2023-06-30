package com.example.ctsapi;

import com.example.ctsapi.DBController;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DBControllerTest {

    @Test
    public void stopPing_NoActivePingRequest() {
        // Arrange
        DBController dbController = new DBController();

        // Act
        String result = dbController.stopPing();

        // Assert
        Assertions.assertEquals("No active ping request to stop.", result);
    }

    @Test
    public void stopPing_ActivePingRequest() {
        // Arrange
        DBController dbController = new DBController();
        DBController.isPinging = true;

        // Act
        String result = dbController.stopPing();

        // Assert
        Assertions.assertEquals("Ping request stopped.", result);
        Assertions.assertFalse(DBController.isPinging);
    }
}

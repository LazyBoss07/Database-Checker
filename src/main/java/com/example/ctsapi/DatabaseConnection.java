package com.example.ctsapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class DatabaseConnection {
    private final String type;
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConnection(String url, String username, String password,String type) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.type=type;
    }

    public ResponseEntity<String> testConnection() {
        String data;
        try {
            // Register the JDBC driver
            if(type.equalsIgnoreCase("postgre")){
                Class.forName("org.postgresql.Driver");
            }else if(type.equalsIgnoreCase("mysql")){
                Class.forName("com.mysql.cj.jdbc.Driver");
            }else if(type.equalsIgnoreCase("oracle")){
                Class.forName("oracle.jdbc.OracleDriver");
            }else if(type.equalsIgnoreCase("sqLite")){
                Class.forName("org.sqlite.JDBC");
            }
            else if(type.equalsIgnoreCase("ms_sql")){
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }

            // Open a connection
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url, username, password);
            data ="Successful";
            // Close the connection
            conn.close();
            System.out.println("Connection closed.");
            return ResponseEntity.ok(data);
             // Connection successful
        } catch (SQLException e) {
            System.out.println("Connection failed! Error message: " + e.getMessage());
            data ="Connection failed!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            data = "JDBC driver not found!"; // Connection failed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }

    }
//
//    public static void main(String[] args) {
////        String url = "jdbc:mysql://localhost:3306/api";
//        String url = "jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs";
//        String username = "reader";
//        String password = "NWDMCE5xdipIjRrp";
//        String type = "postgre";
//         /*MySQL:
//        Driver Class Name: "com.mysql.jdbc.Driver"
//        JDBC URL: "jdbc:mysql://hostname:port/database"
//
//        PostgreSQL:
//        Driver Class Name: "org.postgresql.Driver"
//        JDBC URL: "jdbc:postgresql://hostname:port/database"
//        JDBC URL: "jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs"
//        JDBC URL: "jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs"
//
//
//        Oracle Database:
//        Driver Class Name: "oracle.jdbc.OracleDriver"
//        JDBC URL: "jdbc:oracle:thin:@hostname:port:database"
//
//        Microsoft SQL Server:
//        Driver Class Name: "com.microsoft.sqlserver.jdbc.SQLServerDriver"
//        JDBC URL: "jdbc:sqlserver://hostname:port;databaseName=database"
//
//        SQLite:
//        Driver Class Name: "org.sqlite.JDBC"
//        JDBC URL: "jdbc:sqlite:/path/to/database.db"*/
//
//        DatabaseConnection connectionTester = new DatabaseConnection(url, username, password,type);
//        ResponseEntity<String> response = connectionTester.testConnection();
//        System.out.println("HTTP status code: " + response.getStatusCode()+"\n"+"Response body: " + response.getBody());
//    }
}

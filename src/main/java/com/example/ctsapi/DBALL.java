package com.example.ctsapi;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter

public class DBALL{

    private List<Endpoint> endpoints;
    private Map<String,DB> databases;

    public DBALL(Map<String, DB> dbMap) {
        this.setDatabases(dbMap);
    }
//
//    public List<Endpoint> getEndpoints() {
//        return endpoints;
//    }
//
//    public void setEndpoints(List<Endpoint> endpoints) {
//        this.endpoints = endpoints;
//    }
//
//    public Map<String, DB> getDatabases() {
//        return databases;
//    }
//
//    public void setDatabases(Map<String, DB> databases) {
//        this.databases = databases;
//    }

}



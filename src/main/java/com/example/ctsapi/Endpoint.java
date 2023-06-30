package com.example.ctsapi;

import jakarta.persistence.Entity;


class Endpoint {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

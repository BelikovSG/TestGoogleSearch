package com.example.testgooglesearch.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "app_database")
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String queryParam;
    private String head;
    private String uri;
    private String description;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Data(String queryParam, String head, String uri, String description){
        this.queryParam = queryParam;
        this.head = head;
        this.uri = uri;
        this.description = description;
    }

}

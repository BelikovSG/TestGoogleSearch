package com.example.testgooglesearch.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    void insertAll(List<Data> dataList);

    @Query("SELECT * FROM app_database WHERE queryParam = :query")
    List<Data> getAllDataWithQuery(String query);

}
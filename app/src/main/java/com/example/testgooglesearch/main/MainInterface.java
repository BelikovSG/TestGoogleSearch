package com.example.testgooglesearch.main;

import com.example.testgooglesearch.model.room.Data;

import java.util.List;

public interface MainInterface {
    interface view {

        void showResult(List<Data> dataList);

        void setSearchField(String lastQuery);
    }

    interface presenter {

        void search(String q);

        void getFromDB(String lastQuery);

        void setView(MainInterface.view view);
    }


}

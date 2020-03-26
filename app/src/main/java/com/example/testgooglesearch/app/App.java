package com.example.testgooglesearch.app;

import android.app.Application;

import androidx.room.Room;

import com.example.testgooglesearch.model.SearchApi;
import com.example.testgooglesearch.model.room.AppDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static SearchApi searchApi;
    private Retrofit retrofit;
    private static AppDatabase appDatabase;
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        searchApi = retrofit.create(SearchApi.class); //Создаем объект, при помощи которого будем выполнять запросы


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database").build();
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static SearchApi getApi() {
        return searchApi;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static AppComponent getComponent() {
        return component;
    }
}

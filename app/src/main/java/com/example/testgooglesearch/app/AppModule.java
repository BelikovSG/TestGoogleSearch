package com.example.testgooglesearch.app;

import android.app.Application;

import com.example.testgooglesearch.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

}

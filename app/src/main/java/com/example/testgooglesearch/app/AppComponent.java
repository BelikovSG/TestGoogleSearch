package com.example.testgooglesearch.app;

import com.example.testgooglesearch.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MainPresenter getMainPresenter();
}

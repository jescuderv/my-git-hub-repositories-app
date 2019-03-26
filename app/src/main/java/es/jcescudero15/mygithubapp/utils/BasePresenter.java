package es.jcescudero15.mygithubapp.utils;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();
}
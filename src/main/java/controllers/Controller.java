package controllers;

import app.App;

public abstract class Controller {

    protected final App app = App.getInstance();

    public Controller() {}
}

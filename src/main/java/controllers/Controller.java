package controllers;

public abstract class Controller {

    protected AppController appController;

    public void injectAppController(AppController ctrl) {
        this.appController = ctrl;
    }

}

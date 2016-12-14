package controllers;

import models.User;

public abstract class Controller {

    protected AppController appController;
    protected User user;


    public void injectAppController(AppController ctrl) {
        this.appController = ctrl;
    }

    public void injectUser(User user) {
        this.user = user;
    }

}

package app;

import controllers.AppController;
import models.User;

/**
 * The big boss class
 */
public class App {

    private final User u = new User();
    private final AppController appController = new AppController(u);

    private final static App app = new App();

    private App() {}

    public static App getInstance() {
        return App.app;
    }

    public User getUser() {
        return this.u;
    }

    public AppController getAppController() {
        return this.appController;
    }

}

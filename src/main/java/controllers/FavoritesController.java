package controllers;


import models.Favorite;

import java.util.List;

public class FavoritesController extends Controller {

    private List<Favorite> favorites;

    

    public FavoritesController(List<Favorite> f) {
        this.favorites = f;
    }


}

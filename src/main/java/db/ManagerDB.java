package db;

import models.Identifiable;

import java.util.List;

/**
 * CRUD is awesome!
 */
public interface ManagerDB{

    public void create();
    

    public List all();

    public void update();

    public void delete(Identifiable i);

}

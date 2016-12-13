package db;

import java.util.List;

/**
 * CRUD is awesome!
 */
public interface CRUD<T> {

    public void create();

    public List<T> all();

    public void update();

    public void delete();
}

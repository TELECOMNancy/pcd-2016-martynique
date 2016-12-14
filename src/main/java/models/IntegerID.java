package models;

/**
 *
 */
public class IntegerID extends ID {

    private int ID;

    public IntegerID(int id) {
        this.ID = id;
    }
    public IntegerID() {}

    @Override
    public String getValue() {
        return Integer.toString(this.ID);
    }
}

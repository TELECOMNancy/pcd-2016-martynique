package models;


public class VarcharID extends ID {

    private String ID;

    public VarcharID(String id) {
        this.ID = id;
    }

    @Override
    public String getValue() {
        return this.ID;
    }
}

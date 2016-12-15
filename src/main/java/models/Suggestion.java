package models;

public class Suggestion implements Identifiable {

    private ID id;
    private String code;
    private int length;

    public Suggestion(String code, int length){
        this.id = new IntegerID();
        this.code = code;
        this.length = length;
    }

    @Override
    public String getID() {
        return this.id.getValue();
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public void setID(ID id) {
        this.id = id;
    }

    public int getLength() {
        return this.length;
    }
}

package models;

public class Suggestion implements Identifiable {

    private int id;
    private String code;
    private int length;

    public Suggestion(String code, int length){
        this.code = code;
        this.length = length;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public int getLength() {
        return this.length;
    }
}

package models;

public class Suggestion implements Identifiable {

    private ID id;
    private int length;

    public Suggestion(String code, int length){
        this.id = new VarcharID(code);
        this.length = length;
    }

    @Override
    public String getID() {
        return this.id.getValue();
    }

    @Override
    public void setID(ID id) {
        this.id = id;
    }

    public int getLength() {
        return this.length;
    }
}

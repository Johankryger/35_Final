package Entity;

public abstract class Property extends Square {
    protected int price;
    protected int rent;
    protected String owner = "bank";

    public Property(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName);
        this.price = price;
        this.rent = rent;
    }


}

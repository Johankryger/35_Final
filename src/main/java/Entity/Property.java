package Entity;

public abstract class Property extends Square {
    private int price;
    private int rent;

    public Property(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName);
        this.price = price;
        this.rent = rent;
    }


}

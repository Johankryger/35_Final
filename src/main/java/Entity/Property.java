package Entity;

public abstract class Property extends Square {
    protected int price;
    protected int rent;
    protected String owner = "bank";
    protected boolean isMortgaged;

    public Property(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName);
        this.price = price;
        this.rent = rent;
    }
    public void setMortgaged(boolean mortgaged){
        this.isMortgaged = mortgaged;
    }
    public boolean getMortgaged(){
        return isMortgaged;
    }

    public void setOwner(String name, int position){}
    public String getOwner(){return owner;}

}

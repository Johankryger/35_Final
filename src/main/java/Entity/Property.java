package Entity;

public abstract class Property extends Square {
    protected int price;
    protected int rent;
    protected int defaultRent;
    protected String owner = "bank";
    protected boolean isMortgaged;
    protected boolean isPaired = false;

    public Property(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName);
        this.price = price;
        this.rent = rent;
        this.defaultRent = rent;
    }
    public void setMortgaged(boolean mortgaged){
        if (mortgaged && !this.isMortgaged) {
            this.rent = 0;
        } else if (!mortgaged && this.isMortgaged) {
            this.rent = this.defaultRent;
        }
        this.isMortgaged = mortgaged;
    }
    public boolean getMortgaged(){
        return isMortgaged;
    }

    public int getRent() {
        return rent;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPaired() {
        return isPaired;
    }

    public void setOwner(String name){
        this.owner = name;
    }
    public String getOwner(){return owner;}

}

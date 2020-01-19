package Entity.square;


public class Ship extends Property{

    public Ship(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName, price, rent);

    }

    public void setShipCount(int ships) {
        switch (ships) {
            case 1:
                this.rent = 500;
                break;
            case 2:
                this.rent = 1000;
                break;
            case 3:
                this.rent = 2000;
                break;
            case 4:
                this.rent = 4000;
                break;
        }
    }
}

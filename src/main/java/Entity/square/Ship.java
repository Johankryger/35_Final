package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import message.Message;

public class Ship extends Property{
    int twoShipCoRent, threeShipCoRent, fourShipCoRent;

    public Ship(int fieldPosition, String fieldName, int price, int rent, int twoShipCoRent, int threeShipCoRent, int fourShipCoRent) {
        super(fieldPosition, fieldName, price, rent);
        this.twoShipCoRent = twoShipCoRent;
        this.threeShipCoRent = threeShipCoRent;
        this.fourShipCoRent = fourShipCoRent;
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
        System.out.println(ships + " owner: " + owner);
    }
}

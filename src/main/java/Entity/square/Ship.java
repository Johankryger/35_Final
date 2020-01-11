package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;

public class Ship extends Property{
    int twoShipCoRent, threeShipCoRent, fourShipCoRent;

    public Ship(int fieldPosition, String fieldName, int price, int rent, int twoShipCoRent, int threeShipCoRent, int fourShipCoRent) {
        super(fieldPosition, fieldName, price, rent);
        this.twoShipCoRent = twoShipCoRent;
        this.threeShipCoRent = threeShipCoRent;
        this.fourShipCoRent = fourShipCoRent;
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (owner.equals("bank")) {
            String option = gui.button("Want to buy " + fieldName + "?", "Buy", "Auction");
            if (option.equals("Buy")) {
                playerList.getPlayer().getBalance().pay(price);
                gui.buyStreet(playerList.getPlayer().getName(), fieldPosition);
                setOwner(playerList.getPlayer().getName());
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            }
            else {
                //setAuction(True, fieldPosition);
                //gui.auction(fieldPosition);
            }
        }
        else if (!owner.equals(playerList.getPlayer().getName())){
            playerList.transfer(rent,playerList.getPlayer().getName(),owner);
        }
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

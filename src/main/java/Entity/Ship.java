package Entity;

import Controller.GUIController;

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



}

package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;
import message.Message;
import staticclasses.ArrayMethods;

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
            String option = gui.button((Message.getMessage("General",1)) + fieldName + (Message.getMessage("General",2)), (Message.getMessage("General",3)), (Message.getMessage("General",4)));
            if (option.equals((Message.getMessage("General",3)))) {
                playerList.getPlayer().getBalance().pay(price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            } else {
                //setAuction(True, fieldPosition);
                //gui.auction(fieldPosition);
            }
        }
        else if (!owner.equals(playerList.getPlayer().getName())){
            gui.button((Message.getMessage("General",5)) + rent + (Message.getMessage("General",6)) + owner, (Message.getMessage("General",7)));
            playerList.transfer(rent,playerList.getPlayer().getName(),owner);
            gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
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

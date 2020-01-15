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
    public boolean squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (owner.equals("bank")) {
            if (playerList.getPlayer().isHasLiquidated()) {
                playerList.getPlayer().getBalance().pay(price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                playerList.getPlayer().setHasLiquidated(false);
                return false;
            }

            String option;
            if (playerList.getPlayer().getLiqudationValue() >= price)
                option = gui.button((Message.getMessage("General",1))  +fieldName + (Message.getMessage("General",2)), (Message.getMessage("General",3)), (Message.getMessage("General",4)));
            else
                option = gui.button((Message.getMessage("General",8)),  (Message.getMessage("General",4)));
            if (option.equals((Message.getMessage("General",3)))) {
                if (price > playerList.getPlayer().getBalance().getAmount()) {
                    playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount()-price);
                    return true;
                }
                playerList.getPlayer().getBalance().pay(price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
//            String option = gui.button((Message.getMessage("General",1)) + fieldName + (Message.getMessage("General",2)), (Message.getMessage("General",3)), (Message.getMessage("General",4)));
//            if (option.equals((Message.getMessage("General",3)))) {
//                playerList.getPlayer().getBalance().pay(price);
//                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
//                owner = playerList.getPlayer().getName();
//                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
//            } else {
                //setAuction(True, fieldPosition);
                //gui.auction(fieldPosition);
            }
        } else if (!owner.equals(playerList.getPlayer().getName())) {
            if (!playerList.searchPlayer(owner).isInJail()) {
                if (playerList.getPlayer().getLiqudationValue() >= rent && playerList.getPlayer().getBalance().getAmount() < rent){
                    playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount()-rent);
                    return true;
                }
                else if (playerList.getPlayer().getBalance().getAmount() < rent){
                    playerList.getPlayer().setAboutToLose(true);
                    return false;
                }
                gui.button((Message.getMessage("General",5)) + " "  + rent + " " + (Message.getMessage("General",6))+ owner, (Message.getMessage("General",7)));
                playerList.transfer(rent, playerList.getPlayer().getName(), owner);
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
        else if (!owner.equals(playerList.getPlayer().getName())){
            gui.button((Message.getMessage("General",5)) + rent + (Message.getMessage("General",6)) + owner, (Message.getMessage("General",7)));
            playerList.transfer(rent,playerList.getPlayer().getName(),owner);
            gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
        }
        return false;
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

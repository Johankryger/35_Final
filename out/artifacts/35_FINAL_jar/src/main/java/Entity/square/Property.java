package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Controller.SquareController;
import Entity.PlayerList;
import message.Message;

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

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum) {
        if (owner.equals("bank")) {
            String option = "";
            if (playerList.getPlayer().getLiquidationValue() < price) {
                option = gui.button(Message.getMessage("General", 1) + " " + fieldName + "?", Message.getMessage("General", 4));
            } else {
                option = gui.button(Message.getMessage("General", 1) + " " + fieldName + "?", Message.getMessage("General", 3), Message.getMessage("General", 4));
            }

            if (option.equals(Message.getMessage("General",3))) {
                propertyController.payment(playerList, playerList.getPlayer().getName(), null, squareController, gui, price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            }
        } else if (!owner.equals(playerList.getPlayer().getName())) {
            if (!playerList.searchPlayer(owner).isInJail()) {
                propertyController.payment(playerList, playerList.getPlayer().getName(), owner, squareController, gui, rent);
                if (playerList.getPlayer() != null) {
                    gui.button((Message.getMessage("General",5)) + " "  + rent + " " + (Message.getMessage("General",6))+ " " + owner, (Message.getMessage("General",7)));
                } else {
                    gui.button(Message.getMessage("General", 12), Message.getMessage("General", 7));
                }

                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
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

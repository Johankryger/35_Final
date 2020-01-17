package Entity.square;


import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import message.Message;

public class Brewery extends Property {

    public Brewery(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName, price, rent);
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
                propertyController.payment(playerList, playerList.getPlayer().getName(), owner, squareController, gui, rent * diceSum);
                if (playerList.getPlayer() != null) {
                    gui.button((Message.getMessage("General",5)) + " "  + rent * diceSum + " " + (Message.getMessage("General",6))+ " " + owner, (Message.getMessage("General",7)));
                } else {
                    gui.button(Message.getMessage("General", 12), Message.getMessage("General", 7));
                }

                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
    }

    public void setPaired(boolean isPaired) {
        // only when paired is changed
        if (this.isPaired && !isPaired) {
            this.rent = this.rent / 2;
        } else if (!this.isPaired && isPaired) {
            this.rent = this.rent * 2;
        }

        this.isPaired = isPaired;
    }
}

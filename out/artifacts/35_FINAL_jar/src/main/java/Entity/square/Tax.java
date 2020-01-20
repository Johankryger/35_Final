package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Controller.SquareController;
import Entity.PlayerList;
import message.Message;

public class Tax extends Square {
    private int taxAmount = 0;

    public Tax(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum) {
        if (fieldPosition == 4) {
            taxAmount = 4000;
        } else {
            taxAmount = 2000;
        }
        propertyController.payment(playerList, playerList.getPlayer().getName(), null, squareController, gui, taxAmount);
        gui.button((Message.getMessage("General", 5)) + " " + taxAmount + " " + (Message.getMessage("General", 10)), (Message.getMessage("General", 7)));
        gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
    }
}


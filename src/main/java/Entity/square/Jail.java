package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import message.Message;

public class Jail extends Square {

    public Jail(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum) {
        gui.button(Message.getMessage("In Jail", 1), Message.getMessage("General", 7));
        int startPos = playerList.getPlayer().getFieldPos();
        playerList.getPlayer().setFieldPos(10);
        gui.movePlayerFast(playerList.getPlayer().getName(), startPos, 10);
        playerList.getPlayer().setInJail(true);
        //Tjek om nedenstående er nødvendig.
        playerList.getPlayer().extraTurn(false);
    }
}
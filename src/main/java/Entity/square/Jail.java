package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;

public class Jail extends Square {

    public Jail(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        gui.button("You've been jailed!", "OK");
        int startPos = playerList.getPlayer().getFieldPos();
        playerList.getPlayer().setFieldPos(10);
        gui.movePlayerFast(playerList.getPlayer().getName(), startPos, 10);
        playerList.getPlayer().setInJail(true);
        //Tjek om nedenstående er nødvendig.
        playerList.getPlayer().extraTurn(false);
    }
}
package Entity;

import Controller.GUIController;

public class Jail extends Square {

    public Jail(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        int startPos = playerList.getPlayer().getFieldPos();
        playerList.getPlayer().setFieldPos(10);
        gui.movePlayerInJail(playerList.getPlayer().getName(),startPos);
        playerList.getPlayer().setInJail(true);
    }
}
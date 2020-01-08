package Entity;

import Controller.GUIController;

public class Jail extends Square {

    public Jail(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        int startPos = playerList.getPlayer().getFieldPos();
        playerList.getPlayer().setFieldPos(30);
        gui.movePlayer(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount(),startPos, 30);
        playerList.getPlayer().setInJail(true);
    }
}
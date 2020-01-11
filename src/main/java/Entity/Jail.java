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
        gui.movePlayer(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount(),startPos, 10);
        playerList.getPlayer().setInJail(true);
        gui.button("You've been jailed!", "OK :(");
    }
}
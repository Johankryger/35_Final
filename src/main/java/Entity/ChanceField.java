package Entity;

import Controller.GUIController;

public class ChanceField extends Square{


    public ChanceField(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        playerList.getPlayer().setGotChanceCard(true);
    }
}
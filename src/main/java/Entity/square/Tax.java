package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;
import logic.GameLogic;

public class Tax extends Square {
    private int taxAmount=0;

    public Tax(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if(fieldPosition==4) {
            taxAmount = 4000;
        }
        else {
            taxAmount=2000;
        }
        playerList.getPlayer().getBalance().pay(taxAmount);
        gui.button("You pay " + taxAmount + " in taxes", "Ok");
        gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
    }
}
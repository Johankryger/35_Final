package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;

public class Tax extends Square {
    private int taxAmount = 0;

    public Tax(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public boolean squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (playerList.getPlayer().isHasLiquidated()) {
            if (fieldPosition == 4) {
                taxAmount = 4000;
            } else {
                taxAmount = 2000;
            }
            playerList.getPlayer().getBalance().pay(taxAmount);
            gui.button("You pay " + taxAmount + " in taxes", "Ok");
            gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
        }


        if (fieldPosition == 4) {
            if (playerList.getPlayer().getBalance().getAmount() < 4000 && playerList.getPlayer().getLiqudationValue() >= 4000) {
                playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount() - 4000);
                return true;
            } else if (playerList.getPlayer().getBalance().getAmount() < 4000) {
                playerList.getPlayer().setAboutToLose(true);
                return false;
            }
            taxAmount = 4000;

        } else {
            if (playerList.getPlayer().getBalance().getAmount() < 2000 && playerList.getPlayer().getLiqudationValue() >= 2000) {
                playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount() - 2000);
                return true;
            } else if (playerList.getPlayer().getBalance().getAmount() < 2000) {
                playerList.getPlayer().setAboutToLose(true);
                return false;
            }
            taxAmount = 2000;
        }
        playerList.getPlayer().getBalance().pay(taxAmount);
        gui.button("You pay " + taxAmount + " in taxes", "Ok");
        gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
        return false;
    }
}


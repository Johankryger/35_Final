package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

// Money Card. Get a value add to your balance or taken away
public class CardType1 extends ChanceCard {
    private int amountAdded;

    public CardType1(String msg, int amountAdded) {
        super(msg);
        this.amountAdded = amountAdded;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        if (amountAdded < 0) {
            propertyController.payment(playerList, playerList.getPlayer().getName(), null, squareController, guiController, amountAdded * (-1));
        }

        playerList.getPlayer().getBalance().add(amountAdded);
        guiController.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
    }
}

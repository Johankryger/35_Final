package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;

public class CardType1 extends ChanceCard {
    private int amountAdded;

    public CardType1(String msg, int amountAdded) {
        super(msg);
        this.amountAdded = amountAdded;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        playerList.getPlayer().getBalance().add(amountAdded);
    }
}

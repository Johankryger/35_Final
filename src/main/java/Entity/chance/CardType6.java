package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;

public class CardType6 extends ChanceCard {
    private int amount;
    public CardType6(String msg, int amount) {
        super(msg);
        this.amount = amount;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        String[] names = playerList.getPlayerNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i] != playerList.getPlayer().getName()) {
                playerList.transfer(amount, names[i], playerList.getPlayer().getName());
            }
        }
    }
}

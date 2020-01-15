package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareController;

public class CardType5 extends ChanceCard {
//Jail Free Card.
    public CardType5(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        playerList.getPlayer().setGotFreeJailCard(true);
    }
}

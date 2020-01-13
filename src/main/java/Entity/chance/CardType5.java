package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;
// metode til at tilføje et get out of jail free card til en spiller
public class CardType5 extends ChanceCard {

    public CardType5(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        playerList.getPlayer().setGotFreeJailCard(true);
    }
}

package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;

public class CardType7 extends ChanceCard {
    public CardType7(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        guiController.movePlayerFast(playerList.getPlayer().getName(), playerList.getPlayer().getFieldPos(), 10);
        playerList.getPlayer().setFieldPos(10);
        playerList.getPlayer().setInJail(true);
        playerList.getPlayer().extraTurn(false);
    }
}

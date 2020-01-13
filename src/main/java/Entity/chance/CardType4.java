package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;

public class CardType4 extends ChanceCard {
// metode til at rykke spillerens brik 3 felter tilbage
    public CardType4(String msg) {
        super(msg);
    }

    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        int currentPos = playerList.getPlayer().getFieldPos();
        int afterPos = (currentPos - 3 + 40) % 40;

        playerList.getPlayer().setFieldPos(afterPos);
        guiController.moveBackwards(playerList.getPlayer().getName(), currentPos, afterPos);

        squareList.getSquare(afterPos).squareAction(playerList, guiController, 1337);
    }
}

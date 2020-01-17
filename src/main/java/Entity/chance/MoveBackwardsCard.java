package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

public class MoveBackwardsCard extends ChanceCard {
// Move back Card. metode til at rykke spillerens brik 3 felter tilbage
    public MoveBackwardsCard(String msg) {
        super(msg);
    }

    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        int currentPos = playerList.getPlayer().getFieldPos();
        int afterPos = (currentPos - 3 + 40) % 40;

        playerList.getPlayer().setFieldPos(afterPos);
        guiController.moveBackwards(playerList.getPlayer().getName(), currentPos, afterPos);

        squareController.getSquare(afterPos).squareAction(playerList, guiController, propertyController, squareController, 1337);
    }
}

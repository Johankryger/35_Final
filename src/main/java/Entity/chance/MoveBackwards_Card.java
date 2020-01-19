package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

/**
 * Moves player 3 fields backwards
 */
public class MoveBackwards_Card extends ChanceCard {

    public MoveBackwards_Card(String msg) {
        super(msg);
    }

    /**
     * Moves player 3 steps backwards
     * @param playerList - to find player
     * @param squareController - to find field
     * @param guiController - to show the action on board
     * @param propertyController - to pay if necessary
     */
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        int currentPos = playerList.getPlayer().getFieldPos();
        int afterPos = (currentPos - 3 + 40) % 40;

        playerList.getPlayer().setFieldPos(afterPos);
        guiController.moveBackwards(playerList.getPlayer().getName(), currentPos, afterPos);

        squareController.getSquare(afterPos).squareAction(playerList, guiController, propertyController, squareController, 1337);
    }
}

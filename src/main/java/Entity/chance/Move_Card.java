package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

/**
 * Moves a player to a specific square
 */
public class Move_Card extends ChanceCard {
    private int position;

    /**
     * The constructor also needs position to know where the player will be moved to
     * @param msg
     * @param position
     */
    public Move_Card(String msg, int position) {
        super(msg);
        this.position = position;
    }

    /**
     * Method to find out how many steps the gui_car to a player should be moved.
     * @param playerList - to find player's current position (which chance field he landed on)
     * @param squareController - to find field
     * @param guiController - to show the action on board
     * @param propertyController - to pay if necessary
     */
    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        int beforePos = playerList.getPlayer().getFieldPos();
        int steps = 0;
        if (position > beforePos) {
            steps = position - beforePos;
        } else if (beforePos > position) {
            steps= 40 - beforePos + position;
        }
        playerList.getPlayer().move(steps,true);
        guiController.movePlayerFast(playerList.getPlayer().getName(), beforePos, position);
        squareController.getSquare(position).squareAction(playerList, guiController, propertyController, squareController, 1337);
    }
}

package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

/**
 * Gives a Free-card to escape from jail, when it is your turn again
 */
public class JailFreeCard extends ChanceCard {

    public JailFreeCard(String msg) {
        super(msg);
    }

    /**
     * Method sets the boolean GotFreeJailCard to true
     * @param playerList - to find player
     * @param squareController - to find field
     * @param guiController - to show the action on board
     * @param propertyController - to pay if necessary
     */
    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        playerList.getPlayer().setGotFreeJailCard(true);
    }
}

package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

    /**
    * Moves a player to jail
    */
public class GoToJailCard extends ChanceCard {

    public GoToJailCard(String msg) {
        super(msg);
    }

    /**
     * Moves the player to jail, and puts his extraTurn to false, even if the dice are a double.
     */
    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        guiController.movePlayerFast(playerList.getPlayer().getName(), playerList.getPlayer().getFieldPos(), 10);
        playerList.getPlayer().setFieldPos(10);
        playerList.getPlayer().setInJail(true);
        playerList.getPlayer().extraTurn(false);
    }
}

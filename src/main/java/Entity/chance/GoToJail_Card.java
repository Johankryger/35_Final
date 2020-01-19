package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Controller.SquareController;

    /**
    * Moves a player to jail
    */
public class GoToJail_Card extends ChanceCard {

    public GoToJail_Card(String msg) {
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

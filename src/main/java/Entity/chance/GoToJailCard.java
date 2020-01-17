package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

public class GoToJailCard extends ChanceCard {
    //Go to Jail Card
    public GoToJailCard(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        guiController.movePlayerFast(playerList.getPlayer().getName(), playerList.getPlayer().getFieldPos(), 10);
        playerList.getPlayer().setFieldPos(10);
        playerList.getPlayer().setInJail(true);
        playerList.getPlayer().extraTurn(false);
    }
}

package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

public class JailFreeCard extends ChanceCard {
//Jail Free Card.
    public JailFreeCard(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        playerList.getPlayer().setGotFreeJailCard(true);
    }
}

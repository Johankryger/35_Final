package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

public abstract class ChanceCard {
    protected String msg;

    public ChanceCard(String msg) {
        this.msg = msg;
    }

    public abstract void chanceAction(PlayerList playerList, SquareController squareList, GUIController guiController, PropertyController propertyController);
}

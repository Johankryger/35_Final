package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;

public abstract class ChanceCard {
    protected String msg;

    public ChanceCard(String msg) {
        this.msg = msg;
    }

    public abstract void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController);
}

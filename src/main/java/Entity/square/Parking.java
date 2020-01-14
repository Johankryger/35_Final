package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;

public class Parking extends Square {


    public Parking(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public boolean squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        return false;
    }


}

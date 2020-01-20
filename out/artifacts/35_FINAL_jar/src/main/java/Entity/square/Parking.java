package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Controller.SquareController;
import Entity.PlayerList;

public class Parking extends Square {


    public Parking(int fieldPosition, String fieldName) {
        super(fieldPosition, fieldName);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum) {

    }


}

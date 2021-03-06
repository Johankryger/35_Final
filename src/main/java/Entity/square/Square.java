package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Controller.SquareController;
import Entity.PlayerList;

public abstract class Square {
    protected int fieldPosition;
    protected String fieldName;


    public Square(int fieldPosition, String fieldName) {
        this.fieldName = fieldName;
        this.fieldPosition = fieldPosition;
    }
    public int getFieldPosition(){
        return fieldPosition;
    }

    public String getFieldName() {
        return fieldName;
    }

    public abstract void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum);

}


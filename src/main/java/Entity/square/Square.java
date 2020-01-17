package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;

/**
 * Abstract class for all squares.
 */
public abstract class Square {
    /**
     * Position of the field on the board.
     */
    protected int fieldPosition;
    /**
     * Name of the field.
     */
    protected String fieldName;

    /**
     * Square constructor, which contains name and position of the field, which is common for all squares.
     * @param fieldPosition Position of the field on the board.
     * @param fieldName Name of the field.
     */
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

    /**
     * Abstract method of the action the square has.
     * @param playerList object of PlayerList
     * @param gui object of GUIController
     * @param propertyController object of PropertyController
     * @param squareController object of SquareController
     * @param diceSum integer of dicesum
     */
    public abstract void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum);

}


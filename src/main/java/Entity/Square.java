package Entity;

import Controller.GUIController;

public abstract class Square {
    protected int fieldPosition;
    protected String fieldName;


    public Square(int fieldPosition, String fieldName) {
        this.fieldPosition = fieldPosition;
    }

    public abstract void squareAction(PlayerList playerList, GUIController gui, int diceSum);

}


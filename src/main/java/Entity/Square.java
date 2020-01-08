package Entity;

public abstract class Square {
    private int fieldPosition;

    private String fieldName;


    public Square(int fieldPosition, String fieldName) {
        this.fieldPosition = fieldPosition;
    }

    public abstract void squareAction();

}


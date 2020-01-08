package Entity;

public class SquareList {
    private Square[] square = new Square[28];

    public Square getSquare(int fieldNr) {
        Square therightSquare = null;
        for (Square s : square) {
            if (s.fieldPosition == fieldNr)
                therightSquare = s;
        }
        return therightSquare;
    }
}

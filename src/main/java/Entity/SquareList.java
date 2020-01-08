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

    public ShipList(){
        this.ships = new Ship[]{
                new Ship(5,"Helsingør-Helsingborg",4000,500,1000,2000,4000),
                new Ship(15,"Mols-Linien",4000,500,1000,2000,4000),
                new Ship(25, "Gedser-Rostock",4000,500,1000,2000,4000),
                new Ship(35,"Rødby-Puttgarden",4000,500,1000,2000,4000)
        };
    }
}

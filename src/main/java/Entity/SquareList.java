package Entity;

public class SquareList {
    private Street[] streets;
    private Ship[] ships;
    private Tax[] taxes;

    public SquareList() {
        this.streets = new Street[]{
                new Street(1, "Rødovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000),
                new Street(4, "Hvidovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000),
                new Street(6,"Roskildevej", 2000,100,1000,600,1800,5400,8000,11000),
                new Street(8,"Valby langgade", 2000,100,1000,600,1800,5400,8000,11000),
                new Street(9,"Allégade", 2400,150,1000,800,2000,6000,9000,12000),
                new Street(11, "Frederiksberg Allé", 2800,200,2000,1000,3000,9000,12500,15000),
                new Street(13, "Bülowsvej", 2800,200,2000,1000,3000,9000,12500,15000),
                new Street(14,"Gl. Kongevej", 3200,250,2000,1250,3750,10000,14000,18000),
                new Street(16,"Bernstorffsvej",3600,300,2000,1400,4000,11000,15000,19000),
                new Street(18,"Hellerupvej", 3600,300,2000,1400,4000,11000,15000,19000),
                new Street(19, "Strandvejen", 4000,350,2000,1600,4400,12000,16000,20000),
                new Street(21, "Trianglen", 4400,350,3000,1800,5000,14000,17500,21000),
                new Street(23, "Østerbrogade", 4400,350,3000,18000,5000,14000,17500,21000),
                new Street(24, "Grønningen", 4800,400,3000,2000,6000,15000,18500,22000),
                new Street(26, "Bredgade", 5200,450,3000,2200,6600,16000,19500,23000),
                new Street(27, "Kgs. Nytorv", 5200,450,3000,2200,6600,16000,19500,23000),
                new Street(29, "Østergade", 5600,500,3000,2400,7200,17000,20500,24000),
                new Street(31,"Amagertorv", 6000,550,4000,2600,7800,18000,22000,25000),
                new Street(32, "Vimmelskaftet", 6000,550,4000,2600,7800,18000,22000,25000),
                new Street(34, "Nygade", 6400,600,4000,3000,9000,20000,24000,28000),
                new Street(37, "Frederiksbergsgade", 7000,700,4000,3500,10000,22000,26000,30000),
                new Street(39,"Rådhuspladsen", 7000,1000,4000,4000,12000,28000,34000,40000)
        };

        this.ships = new Ship[]{
                new Ship(5,"Helsingør-Helsingborg",4000,500,1000,2000,4000),
                new Ship(15,"Mols-Linien",4000,500,1000,2000,4000),
                new Ship(25, "Gedser-Rostock",4000,500,1000,2000,4000),
                new Ship(35,"Rødby-Puttgarden",4000,500,1000,2000,4000)
        };

        this.taxes = new Tax[]{
                new Tax(4,"Indkomstskat"),
                new Tax(38,"Statsskat")
        };



    }

    public Square getSquare(int fieldNr) {
        Square therightSquare = null;
        for (Street s : streets) {
            if (s.fieldPosition == fieldNr)
                therightSquare = s;
        }

        for (Ship s : ships){
            if (s.fieldPosition == fieldNr)
                therightSquare = s;
        }
        return therightSquare;
    }


}

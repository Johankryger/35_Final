package Entity;

public class SquareList {
    private Street[] streets;
    private Ship[] ships;
    private Tax[] taxes;
    private Brewery[] breweries;
    private ChanceField[] chanceFields;
    private Parking[] parkings;
    private Square[][] squares;
    private Property[][] properties;

    public SquareList() {
        this.streets = new Street[]{
                new Street(1, "Rødovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000,"Blue"),
                new Street(4, "Hvidovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000, "Blue"),
                new Street(6,"Roskildevej", 2000,100,1000,600,1800,5400,8000,11000, "Orange"),
                new Street(8,"Valby langgade", 2000,100,1000,600,1800,5400,8000,11000,"Orange"),
                new Street(9,"Allégade", 2400,150,1000,800,2000,6000,9000,12000, "Orange"),
                new Street(11, "Frederiksberg Allé", 2800,200,2000,1000,3000,9000,12500,15000, "Green"),
                new Street(13, "Bülowsvej", 2800,200,2000,1000,3000,9000,12500,15000, "Green"),
                new Street(14,"Gl. Kongevej", 3200,250,2000,1250,3750,10000,14000,18000, "Green"),
                new Street(16,"Bernstorffsvej",3600,300,2000,1400,4000,11000,15000,19000,"Grey"),
                new Street(18,"Hellerupvej", 3600,300,2000,1400,4000,11000,15000,19000,"Grey"),
                new Street(19, "Strandvejen", 4000,350,2000,1600,4400,12000,16000,20000,"Grey"),
                new Street(21, "Trianglen", 4400,350,3000,1800,5000,14000,17500,21000,"Red"),
                new Street(23, "Østerbrogade", 4400,350,3000,18000,5000,14000,17500,21000,"Red"),
                new Street(24, "Grønningen", 4800,400,3000,2000,6000,15000,18500,22000,"Red"),
                new Street(26, "Bredgade", 5200,450,3000,2200,6600,16000,19500,23000,"White"),
                new Street(27, "Kgs. Nytorv", 5200,450,3000,2200,6600,16000,19500,23000,"White"),
                new Street(29, "Østergade", 5600,500,3000,2400,7200,17000,20500,24000,"White"),
                new Street(31,"Amagertorv", 6000,550,4000,2600,7800,18000,22000,25000,"Yellow"),
                new Street(32, "Vimmelskaftet", 6000,550,4000,2600,7800,18000,22000,25000,"Yellow"),
                new Street(34, "Nygade", 6400,600,4000,3000,9000,20000,24000,28000,"Yellow"),
                new Street(37, "Frederiksbergsgade", 7000,700,4000,3500,10000,22000,26000,30000,"Purple"),
                new Street(39,"Rådhuspladsen", 7000,1000,4000,4000,12000,28000,34000,40000,"Purple")
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

        this.breweries = new Brewery[]{
                new Brewery(28,"Coca-Cola", 3000,100),
                new Brewery(12,"TUBORG SQUASH",3000,100)
        };

        this.chanceFields = new ChanceField[] {
                new ChanceField(2, "Chance"),
                new ChanceField(7, "Chance"),
                new ChanceField(17, "Chance"),
                new ChanceField(22, "Chance"),
                new ChanceField(33, "Chance"),
                new ChanceField(36, "Chance")
        };

        this.parkings = new Parking[] {
                new Parking(0, "Start"),
                new Parking(10, "visit"),
                new Parking(20, "parking")
        };

        this.squares = new Square[][]{ships, streets, taxes, breweries, chanceFields, parkings};
        this.properties = new Property[][] {ships, streets, breweries};

    }
    

    public Property[] ownedProperty(String playerName) {
        int counter = 0;

        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                if (properties[i][j].getOwner().equals(playerName))
                    counter++;
            }
        }

        Property[] ownedProperties = new Property[counter];
        counter = 0;

        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                if (properties[i][j].getOwner().equals(playerName))
                    ownedProperties[counter] = properties[i][j];
                counter++;
            }
        }

        return ownedProperties;
    }


    public String[] canMortgageArray(String name){
        Property[] properties = ownedProperty(name);
        int counter2 = 0;

        for(Property p : properties) {
            if (p instanceof Street) {
                for (Street s : streets) {
                    if (s.getColor().equals(((Street) p).getColor()) && s.getNumberOfHouses() == 0 && !p.getMortgaged()){
                        counter2++;
                    }
                }
            } else {
                if(p.getOwner().equals(name) && !p.getMortgaged())
                    counter2++;
            }
        }
        String[] mortgagedProperties = new String[counter2];
        counter2 = 0;

        for(Property p : properties) {
            if (p instanceof Street) {
                for (Street s : streets) {
                    if (s.getColor().equals(((Street) p).getColor()) && s.getNumberOfHouses() == 0 && !p.getMortgaged())
                        mortgagedProperties[counter2] = p.fieldName;

                }
            } else {
                if(p.getOwner().equals(name) && !p.getMortgaged())
                    mortgagedProperties[counter2] = p.fieldName;
            }
        }

        return mortgagedProperties;
    }

    public Square getSquare(int fieldNr) {
        Square therightSquare = null;

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j].getFieldPosition() == fieldNr)
                    therightSquare = squares[i][j];
            }
        }

        return therightSquare;
    }

    public void checkForPairsStreet() {
        for (int i = 0; i < 2; i++) {
            if (streets[i].getOwner().equals(streets[i + 1].getOwner()) && !streets[i].getOwner().equals("")) {
                streets[i].setPaired(true);
                streets[i + 1].setPaired(true);
            }
        }
        for (int i = 2; i < 20; i++) {
            if (streets[i].getOwner().equals(streets[i + 1].getOwner()) && streets[i].getOwner().equals(streets[i + 2].getOwner()) && !streets[i].getOwner().equals("")) {
                streets[i].setPaired(true);
                streets[i + 1].setPaired(true);
                streets[i + 2].setPaired(true);
            }
        }
        for (int i = 20; i < 22; i++) {
            if (streets[i].getOwner().equals(streets[i + 1].getOwner()) && !streets[i].getOwner().equals("")) {
                streets[i].setPaired(true);
                streets[i + 1].setPaired(true);
            }
        }
    }

    public String[] mortgagedProperties(String name) {
        Property[] properties = ownedProperty(name);
        int counter = 0;
        for(Property p : properties) {
            if(p.isMortgaged){
                counter++;
            }
        }
        String[] mortgagedProperties = new String[counter];
        counter = 0;

        for (Property p : properties) {
            if (p.isMortgaged){
                mortgagedProperties[counter] = p.fieldName;
                counter++;
            }
        }
        return mortgagedProperties;
    }
}

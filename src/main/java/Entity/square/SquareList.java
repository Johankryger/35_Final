package Entity.square;

public class SquareList {
    private Street[] streets;
    private Ship[] ships;
    private Tax[] taxes;
    private Brewery[] breweries;
    private ChanceField[] chanceFields;
    private Parking[] parkings;
    private Jail[] jail;
    private Square[][] squares;
    private Property[][] properties;

    public SquareList() {
        this.streets = new Street[]{
                new Street(1, "Rødovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000,"Blue"),
                new Street(3, "Hvidovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000, "Blue"),
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

        this.jail = new Jail[]{
                new Jail(30,"Fængsel")
        };
        this.squares = new Square[][]{ships, streets, taxes, breweries, chanceFields, parkings, jail};
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
                if (properties[i][j].getOwner().equals(playerName)) {
                    ownedProperties[counter] = properties[i][j];
                    counter++;
                }
            }
        }

        return ownedProperties;
    }




    public void checkPairs() {
        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                String owner = properties[i][j].getOwner();
                if (!owner.equals("bank") && !properties[i][j].isMortgaged) {
                    if (properties[i][j] instanceof Street) {
                        String color = ((Street) properties[i][j]).getColor();

                        boolean isPaired = true;
                        for (Street s : streets) {
                            if (s.getColor().equals(color) && !s.getOwner().equals(owner) && !s.isMortgaged) {
                                isPaired = false;
                            }
                        }
                        for (Street s : streets) {
                            if (s.getColor().equals(color)) {
                                s.setPaired(isPaired);
                            }
                        }
                    } else if (properties[i][j] instanceof Brewery) {
                        if (breweries[0].getOwner().equals(owner) && breweries[1].getOwner().equals(owner) && !breweries[0].isMortgaged && !breweries[1].isMortgaged) {
                            breweries[0].setPaired(true);
                            breweries[1].setPaired(true);
                        } else {
                            breweries[0].setPaired(false);
                            breweries[1].setPaired(false);
                        }
                    } else {
                        int counter = 0;
                        for (Ship s : ships) {
                            if (owner.equals(s.getOwner()) && !s.isMortgaged) {
                                counter++;
                            }
                        }
                        for (Ship s : ships) {
                            if (owner.equals(s.getOwner())) {
                                s.setShipCount(counter);
                            }
                        }

                    }
                }



            }
        }


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

    public Ship getShip(int fieldNr) {
        Ship theRightShip = null;

        for (Ship s : ships) {
            if (s.getFieldPosition() == fieldNr)
                theRightShip = s;
        }

        return theRightShip;
    }


    public Street[] getPairedStreets(String playerName) {
        int counter = 0;
        for (Street s : streets){
            if (s.getOwner().equals(playerName) && s.isPaired){
                counter++;
            }
        }
        Street[] pairedStreets = new Street[counter];
        counter = 0;
        for (Street s : streets){
            if (s.getOwner().equals(playerName) && s.isPaired){
                pairedStreets[counter] = s;
                counter++;
            }
        }
        return pairedStreets;
    }

    public String[] getOwnedStreetNames(String playerName) {
        Property[] properties = ownedProperty(playerName);
        int counter = 0;
        for (Property p : properties) {
            if (p instanceof Street)
                counter++;
        }
        String[] pairedStreets = new String[counter];
        counter = 0;
        for (Property p : properties) {
            if (p instanceof Street) {
                pairedStreets[counter] = p.getFieldName();
                counter++;
            }
        }
        return pairedStreets;
    }

    public String[] getOwnedPropertyNames(String playerName) {
        Property[] properties = ownedProperty(playerName);

        String[] pairedStreets = new String[properties.length];
        for (int i = 0; i < properties.length; i++) {
            pairedStreets[i] = properties[i].getFieldName();
        }
        return pairedStreets;
    }

    public Street searchStreet(String fieldName) {
        Street choosen = null;
        for (int i = 0; i < streets.length; i++) {
            if (streets[i].getFieldName().equals(fieldName))
                choosen = streets[i];
        }
        return choosen;
    }

    public Property searchProperty(String fieldName) {
        Property choosen = null;
        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                if (properties[i][j].getFieldName().equals(fieldName))
                    choosen = properties[i][j];
            }
        }
        return choosen;
    }


    public String[] getbuildableStreets(String playerName){
        Street[] pairedStreets = getPairedStreets(playerName);

        for (int i = 0; i < pairedStreets.length; i++) {
            int numberofHouses = pairedStreets[i].getNumberOfHouses();
            boolean canBuild = true;

            if (numberofHouses != 5) {
                for (int j = 0; j < pairedStreets.length; j++) {
                    if (pairedStreets[i].getColor().equals(pairedStreets[j].getColor()) && numberofHouses > pairedStreets[j].getNumberOfHouses()){
                        canBuild = false;
                    }

                }
                if (canBuild) {
                    pairedStreets[i].setCanBuildHouse(true);
                } else {
                    pairedStreets[i].setCanBuildHouse(false);
                }
            }
        }
        int counter = 0;
        for (Street s : streets) {
            if (s.getOwner().equals(playerName) && s.isCanBuildHouse()) {
                counter++;
            }
        }
        String[] buildableStreets = new String[counter];
        counter = 0;
        for (Street s : streets) {
            if (s.getOwner().equals(playerName) && s.isCanBuildHouse()) {
                buildableStreets[counter] = s.getFieldName();
                counter++;
            }
        }
        return buildableStreets;
    }

    //Tjek om nødvendig.
    public void giveLoserProperty(String loserName, String newOwner){
        for (Street street : streets) {
            if (street.getOwner().equals(loserName)) {
                street.setOwner(newOwner);
            }
        }
        for (Brewery brewery : breweries) {
            if (brewery.getOwner().equals(loserName)) {
                brewery.setOwner(newOwner);
            }
        }
        for (Ship ships : ships) {
            if (ships.getOwner().equals(loserName)) {
                ships.setOwner(newOwner);
            }
        }
    }
}

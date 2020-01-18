package Entity;

public class Player {
    private String name;
    private Balance balance = new Balance();
    private int fieldPos = 0;
    private int liquidationValue =30000;

    private boolean inJail;
    private int turnsInJail;

    private int pairCounter = 0;

    private boolean aboutToStartCash;
    private boolean extraTurn = false;
    private boolean gotChanceCard;
    private boolean gotFreeJailCard = false;
    private static int playerCounter=0;

    public Player(String name) {
        if (name.equals("")) {
            this.name = "Player " + ++playerCounter;
        }
        else {this.name = name; playerCounter++;}
    }

    public static void setPlayerCounter(int playerCounter) { Player.playerCounter = playerCounter; }

    public void move(int steps, boolean StartCashPossible) {
        if (!(steps<0)) {
            this.fieldPos = this.fieldPos + steps;

            if (fieldPos > 39) {
                this.fieldPos = fieldPos - 40;
                aboutToStartCash = true;

            }
            if (StartCashPossible && fieldPos > 0 && aboutToStartCash) {
                balance.add(4000);
                aboutToStartCash = false;
            }
        }
        else{this.fieldPos=(fieldPos+steps+40)%40;}
    }


    public void setLiquidationValue(int liquidationValue) {
        this.liquidationValue = liquidationValue;
    }

    public Balance getBalance() {
        return balance;
    }

    public int getFieldPos() {
        return fieldPos;
    }

    public void setFieldPos(int fieldPos) {
        if (!(fieldPos<0)) this.fieldPos = fieldPos%40;
    }

    public String getName() {
        return name;
    }

    public boolean hasGotChanceCard() {
        return gotChanceCard;
    }

    public void setGotChanceCard(boolean gotChanceCard) {
        this.gotChanceCard = gotChanceCard;
    }

    public boolean hasGotFreeJailCard() {
        return gotFreeJailCard;
    }

    public void setGotFreeJailCard(boolean gotFreeJailCard) {
        this.gotFreeJailCard = gotFreeJailCard;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void extraTurn(boolean extraTurn) {
        this.extraTurn = extraTurn;
    }

    public boolean hasExtraTurn() {
        return extraTurn;
    }

    public void addTurnInJail() {
        this.turnsInJail++;
    }

    public void resetTurnsInJail() {
        this.turnsInJail = 0;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getPairCounter(){return pairCounter;}

    public void incrementPairCounter(){pairCounter++;}

    public void resetPairCounter(){pairCounter = 0;}


    public void useBailCard(){
        setGotFreeJailCard(false);
        setInJail(false);
        resetTurnsInJail();
    }


    public int getLiquidationValue() {return liquidationValue;}
}

package Entity;

/**
 * Player class used for storing player info
 */
public class Player {
    /**
     * name of a player
     */
    private String name;
    private Balance balance = new Balance();
    private int fieldPos = 0;
    private int liquidationValue =30000;
    private boolean inJail;
    /**
     * The amount of turns the player has spent in jail.
     */
    private int turnsInJail;
    /**
     * A counter for dice pairs.
     */
    public int pairCounter = 0;


    private boolean aboutToStartCash;
    private boolean extraTurn = false;
    private boolean gotChanceCard;
    private boolean gotFreeJailCard = false;
    private static int playerCounter=0;

    /**
     * This is a constructor to initialize player object.
     * @param name an initial player name
     */
    public Player(String name) {
        if (name.equals("")) {
            this.name = "Player " + ++playerCounter;
        }
        else {this.name = name; playerCounter++;}
    }

    public static void setPlayerCounter(int playerCounter) { Player.playerCounter = playerCounter; }

    /**
     * This is a method of moving the player
     * @param steps for moving the player
     * @param StartCashPossible possibility for the player to receive money when passing start
     */
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

    /**
     * Sets the players value of liquidation.
     *
     * @param liquidationValue the value of all the belongings of the player, if he wishes to sell them.
     */
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

    /**
     * Adds pairs of dice rolls to the counter
     */
    public void incrementPairCounter(){pairCounter++;}

    public void resetPairCounter(){pairCounter = 0;}

    /**
     * Method that works as a bailcard
     * It removes your free jail card
     * Removes you from jail
     * And resets your turns in jail.
     */
    public void useBailCard(){
        setGotFreeJailCard(false);
        setInJail(false);
        resetTurnsInJail();
    }

    /**
     *
     * @return the value of all the belongings of the player, if he wishes to sell them.
     */
    public int getLiquidationValue() {return liquidationValue;}
}

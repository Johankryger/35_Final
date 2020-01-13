package Entity;

public class Player {
    private String name;
    private int ID;
    private int finalScore;
    private Balance balance = new Balance();
    private boolean hasLost = false;
    private int fieldPos = 0;
    private int liqudationValue;

    private boolean inJail;
    private int turnsInJail;

    public int pairCounter = 0;

    private boolean aboutToStartCash;
    private int turnsInARow = 0;
    private boolean extraTurn = false;
    private boolean gotChanceCard;
    private boolean gotFreeJailCard = false;

    public Player(String name) {
        this.name = name;
    }

    public void move(int steps, boolean StartCashPossible) {
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

    public void setLiqudationValue(int liqudationValue) {
        this.liqudationValue = liqudationValue;
    }

    public boolean isHasLost() {
        return hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public Balance getBalance() {
        return balance;
    }

    public int getFieldPos() {
        return fieldPos;
    }

    public void setFieldPos(int fieldPos) {
        this.fieldPos = fieldPos;
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

    public int getTurnsInARow() {
        return turnsInARow;
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

    public boolean payJailBail (int money){
        getBalance().pay(money);
        setInJail(false);
        resetTurnsInJail();
        return true;
    }

    public void useBailCard(){
        setGotFreeJailCard(false);
        setInJail(false);
        resetTurnsInJail();
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}

package Entity;

import Entity.Balance;

public class Player {
    private String name;
    private int ID;
    private Balance balance = new Balance();
    private boolean hasLost = false;
    private int fieldPos = 0;

    private boolean inJail;
    private int turnsInJail;

    private boolean aboutToStartCash;
    private int turnsInARow = 0;
    private boolean extraTurn = false;
    private boolean gotChanceCard;

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

    public boolean isHasLost() {
        return hasLost;
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

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void extraTurn(boolean extraTurn) {
        this.extraTurn = extraTurn;
    }

    public boolean isExtraTurn() {
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
}

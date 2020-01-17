package Entity;

/**
 * Balance class used for adding and paying amounts.
 */
public class Balance {
    /**
     * the value of the player's gamestart balance.
     */
    private int amount = 30000;

    public Balance () {

    }

    public int getAmount() {
        return amount;
    }

    /**
     * Method that pays an amount and redraws it from the current balance amount.
     * @param amount the amount that the player needs to pay.
     */
    public void pay(int amount) {if (!(this.amount<amount) && amount > 0)
        this.amount = this.amount - amount;
    }

    /**
     * Method that adds an amount to the current balance amount.
     * @param amount the amount that is added to the players balance.
     */
    public void add(int amount) {
        if (!(amount<0)) this.amount = this.amount + amount;
    }
}

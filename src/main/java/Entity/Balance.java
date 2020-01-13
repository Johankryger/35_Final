package Entity;

public class Balance {
    private int amount = 30000;

    public Balance () {

    }

    public int getAmount() {
        return amount;
    }

    public void pay(int amount) {
        this.amount = this.amount - amount;
    }

    public void add(int amount) {
        this.amount = this.amount + amount;
    }
}

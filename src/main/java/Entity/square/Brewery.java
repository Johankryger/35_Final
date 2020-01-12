package Entity.square;


import Controller.GUIController;
import Entity.PlayerList;

public class Brewery extends Property {

    public Brewery(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName, price, rent);
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (owner.equals("bank")) {
            String option = gui.button("Want to buy " + fieldName + "?", "Buy", "Auction");
            if (option.equals("Buy")) {
                playerList.getPlayer().getBalance().pay(price);
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
            }
        } else if (!owner.equals(playerList.getPlayer().getName())){
            gui.button("You pay " + rent * diceSum + " to " + owner, "Okay");
            playerList.transfer(rent * diceSum,playerList.getPlayer().getName(),owner);
            gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
        }

    }

    public void setPaired(boolean isPaired) {
        // only when paired is changed
        if (this.isPaired && !isPaired) {
            this.rent = this.rent / 2;
        } else if (!this.isPaired && isPaired) {
            this.rent = this.rent * 2;
        }

        this.isPaired = isPaired;
    }
}

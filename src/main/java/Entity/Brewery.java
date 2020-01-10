package Entity;


import Controller.GUIController;

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
                gui.buyStreet(playerList.getPlayer().getName(), fieldPosition);
            }
        } else if (!owner.equals(playerList.getPlayer().getName())) {
            if (!playerList.searchPlayer(owner).isInJail()) {
                playerList.transfer(rent * diceSum, playerList.getPlayer().getName(), owner);
                gui.updateBalance(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(playerList.searchPlayer(owner).getName(),playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
    }
}

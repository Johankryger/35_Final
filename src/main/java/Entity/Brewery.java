package Entity;


import Controller.GUIController;

public class Brewery extends Property {
    int twoBreweriesRent;
    public Brewery(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName, price, rent);
        this.twoBreweriesRent = twoBreweriesRent;
    }

    @Override
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (owner.equals("bank")) {
            String option = gui.button("Want to buy " + fieldName + "?", "Buy", "Auction");
            if (option.equals("Buy")) {
                playerList.getPlayer().getBalance().pay(price);
                gui.buyStreet(playerList.getPlayer().getName(), fieldPosition);
            }
        } else if (!owner.equals(playerList.getPlayer().getName())){
            playerList.transfer(rent * diceSum,playerList.getPlayer().getName(),owner);
        }

    }
}

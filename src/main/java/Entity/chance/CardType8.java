package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;

public class CardType8 extends ChanceCard {
    private int houseTax;
    private int hotelTax;

    public CardType8(String msg, int houseTax, int hotelTax) {
        super(msg);
        this.houseTax = houseTax;
        this.hotelTax = hotelTax;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        String playerName = playerList.getPlayer().getName();
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);

        int amountOfHouses = 0;
        int amountOfHotels = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (squareList.searchStreet(ownedStreets[i]).getNumberOfHouses() == 5) {
                amountOfHotels++;
            } else {
                amountOfHouses += squareList.searchStreet(ownedStreets[i]).getNumberOfHouses();
            }
        }
        int totalTax = amountOfHouses * houseTax + amountOfHotels * hotelTax;

        playerList.getPlayer().getBalance().pay(totalTax);
        guiController.updateBalance(playerName, playerList.getPlayer().getBalance().getAmount());
    }
}

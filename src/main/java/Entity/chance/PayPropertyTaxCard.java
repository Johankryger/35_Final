package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;
// Pay Property Tax Card. metode brugt til a opkræve et antal penge for hvert hus eller hotel spilleren ejer
public class PayPropertyTaxCard extends ChanceCard {
    private int houseTax;
    private int hotelTax;

    public PayPropertyTaxCard(String msg, int houseTax, int hotelTax) {
        super(msg);
        this.houseTax = houseTax;
        this.hotelTax = hotelTax;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        String playerName = playerList.getPlayer().getName();
        String[] ownedStreets = squareController.getOwnedStreetNames(playerName);

        int amountOfHouses = 0;
        int amountOfHotels = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (squareController.searchStreet(ownedStreets[i]).getNumberOfHouses() == 5) {
                amountOfHotels++;
            } else {
                amountOfHouses += squareController.searchStreet(ownedStreets[i]).getNumberOfHouses();
            }
        }
        int totalTax = amountOfHouses * houseTax + amountOfHotels * hotelTax;

        propertyController.payment(playerList, playerName, null, squareController, guiController, totalTax);
        guiController.updateBalance(playerName, playerList.getPlayer().getBalance().getAmount());
    }
}

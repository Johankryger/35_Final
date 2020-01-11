package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;

public class CardType9 extends ChanceCard {
    public CardType9(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        String[] ownedStreets = squareList.getOwnedStreetNames(playerList.getPlayer().getName());
        int totalPropertyValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (!squareList.searchStreet(ownedStreets[i]).getMortgaged()) {
                totalPropertyValue += squareList.searchStreet(ownedStreets[i]).getPrice();
            } else {
                totalPropertyValue += squareList.searchStreet(ownedStreets[i]).getPrice() / 2;
            }
        }

        int totalBuildingValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (squareList.searchStreet(ownedStreets[i]).isPaired()) {
                int numberOfHouses= squareList.searchStreet(ownedStreets[i]).getNumberOfHouses();
                int housePrice = squareList.searchStreet(ownedStreets[i]).getHousePrice();
                totalBuildingValue += numberOfHouses * housePrice;
            }
        }

        int cash = playerList.getPlayer().getBalance().getAmount();

        int totalWealth = cash + totalPropertyValue + totalBuildingValue;

        if (totalWealth <= 15000) {
            playerList.getPlayer().getBalance().add(40000);
        }
    }
}

package logic;

import Entity.PlayerList;
import Entity.square.SquareList;

public class GameLogic {

    public static int countTotalValue(PlayerList playerList, SquareList squareList) {
        String[] ownedStreets = squareList.getOwnedStreetNames(playerList.getPlayer().getName());
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerList.getPlayer().getName());

        int totalPropertyValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (!squareList.searchProperty(ownedProperties[i]).getMortgaged()) {
                totalPropertyValue += squareList.searchProperty(ownedProperties[i]).getPrice();
            } else {
                totalPropertyValue += squareList.searchProperty(ownedProperties[i]).getPrice() / 2;
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

        return cash + totalPropertyValue + totalBuildingValue;
    }

    public static boolean getCanAfford(PlayerList playerList, SquareList squareList, int amountToPay) {
        boolean canafford = false;
        int liquidationMoney = playerList.getPlayer().getBalance().getAmount();

        String[] ownedStreets = squareList.getOwnedStreetNames(playerList.getPlayer().getName());
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerList.getPlayer().getName());

        for (int i = 0; i < ownedStreets.length; i++) {
            if (!squareList.searchProperty(ownedProperties[i]).getMortgaged()) {
                liquidationMoney += squareList.searchProperty(ownedProperties[i]).getPrice() / 2;
            }
        }

        for (int i = 0; i < ownedStreets.length; i++) {
            if (squareList.searchStreet(ownedStreets[i]).isPaired()) {
                int numberOfHouses= squareList.searchStreet(ownedStreets[i]).getNumberOfHouses();
                int housePrice = squareList.searchStreet(ownedStreets[i]).getHousePrice();
                liquidationMoney += numberOfHouses * housePrice;
            }
        }

        if (liquidationMoney > amountToPay) {
            canafford = true;
        }
        return  canafford;
    }

}

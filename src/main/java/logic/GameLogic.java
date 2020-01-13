package logic;

import Entity.PlayerList;
import Entity.Property;
import Entity.SquareList;
import Entity.Street;

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

    public static boolean canMortgage(Property property, SquareList squareList) {
        boolean canMortgage = true;
        String fieldName = property.getFieldName();

        // if the property is already mortgaged, the proprty cannot be mortgaged
        if (property.getMortgaged()) {
            canMortgage = false;

        // if the property is not a street
        } else if (squareList.searchStreet(fieldName) == null) {
            canMortgage = true;

        // if the street is not paired
        } else if (!squareList.searchStreet(fieldName).isPaired()) {
            canMortgage = true;

        // if the street is paired and all the streets of same color has 0 houses build
        } else if (property.isPaired() && squareList.searchStreet(fieldName).getNumberOfHouses() == 0) {
            String color = squareList.searchStreet(fieldName).getColor();
            String[] ownedStreets = squareList.getOwnedStreetNames(property.getOwner());
            canMortgage = true;
            for (int i = 0; i < ownedStreets.length; i++) {
                if (squareList.searchStreet(ownedStreets[i]).getColor().equals(color) && squareList.searchStreet(ownedStreets[i]).getNumberOfHouses() != 0) {
                    canMortgage = false;
                }
            }
        } else if (squareList.searchStreet(fieldName).getNumberOfHouses() > 0) {
            canMortgage = false;
        }
        return canMortgage;
    }

    public static boolean canBuildHouse(Street street, SquareList squareList) {
        boolean canBuild = true;
        String owner = street.getOwner();
        int numberOfHouses = street.getNumberOfHouses();
        String color = street.getColor();

        if (!street.isPaired() || numberOfHouses == 5) {
            canBuild = false;
        } else {
            String[] ownedStreets = squareList.getOwnedStreetNames(owner);
            for (int i = 0; i < ownedStreets.length; i++) {
                Street theOtherStreet = squareList.searchStreet(ownedStreets[i]);
                if (theOtherStreet.getColor().equals(color) && numberOfHouses > theOtherStreet.getNumberOfHouses()) {
                    canBuild = false;
                }
            }
        }
        return canBuild;
    }

    public static boolean canSellHouse(Street street, SquareList squareList) {
        boolean canSellHouse = true;
        int numberOfHouses = street.getNumberOfHouses();
        String streetName = street.getFieldName();
        String name = street.getOwner();
        String[] streetsOwned = squareList.getOwnedStreetNames(name);
        String color = street.getColor();

        if (numberOfHouses == 0) {
            canSellHouse = false;
        } else {
            for (int i = 0; i < streetsOwned.length; i++) {
                if (squareList.searchStreet(streetName).getColor().equals(color) && numberOfHouses < squareList.searchStreet(streetsOwned[i]).getNumberOfHouses())
                    canSellHouse = false;
            }
        }

        return canSellHouse;
    }

}

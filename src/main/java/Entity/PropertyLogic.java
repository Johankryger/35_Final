package Entity;

import Entity.square.Property;
import Entity.square.SquareController;
import Entity.square.Street;

public class PropertyLogic {

    public boolean canMortgage(Property property, SquareController squareList) {
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

    public boolean canBuildHouse(Street street, SquareController squareList) {
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

    public boolean canSellHouse(Street street, SquareController squareList) {
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

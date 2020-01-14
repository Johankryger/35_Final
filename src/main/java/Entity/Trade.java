package Entity;

import Entity.square.SquareList;
import staticclasses.ArrayMethods;

public class Trade {

    public String[] trade(String playerName, SquareList squareList){
        String[] owendStreets = squareList.getOwnedStreetNames(playerName);
        String[] ownedShips = squareList.getOwnedShipNames(playerName);
        String[] ownedBreweries = squareList.getOwnedBreweryNames(playerName);
        String[] tradeMenu  = new String[3];
        tradeMenu[0] = "Go back";
        tradeMenu[1] = "Next";
        tradeMenu[2] = "Money offer";

        for (int i = 0; i < owendStreets.length; i++) {
            if (!squareList.checkAnyHouse(squareList.searchStreet(owendStreets[i]),playerName)){
                tradeMenu = ArrayMethods.addToArray(tradeMenu,owendStreets[i]);
            }
        }
        for (int i = 0; i < ownedShips.length; i++) {
            tradeMenu = ArrayMethods.addToArray(tradeMenu,ownedShips[i]);
        }
        for (int i = 0; i < ownedBreweries.length; i++) {
            tradeMenu = ArrayMethods.addToArray(tradeMenu,ownedBreweries[i]);
        }
        return tradeMenu;
    }
}

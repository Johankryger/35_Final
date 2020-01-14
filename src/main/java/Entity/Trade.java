package Entity;

import Controller.GUIController;
import Entity.square.SquareList;
import staticclasses.ArrayMethods;

import java.lang.reflect.Array;

public class Trade {

    public void trade(PlayerList playerList, SquareList squareList, GUIController guiController) {
        String playerName = playerList.getPlayer().getName();
        String[] owendStreets = squareList.getOwnedStreetNames(playerName);
        String[] ownedShips = squareList.getOwnedShipNames(playerName);
        String[] ownedBreweries = squareList.getOwnedBreweryNames(playerName);
        String[] tradeMenuArray = new String[3];
        String[] tradeOfferArray = new String[0];

        tradeMenuArray[0] = "Go back";
        tradeMenuArray[1] = "Next";
        tradeMenuArray[2] = "Money offer";


        for (int i = 0; i < owendStreets.length; i++) {
            if (!squareList.checkAnyHouse(squareList.searchStreet(owendStreets[i]), playerName)) {
                tradeMenuArray = ArrayMethods.addToArray(tradeMenuArray, owendStreets[i]);
            }
        }
        for (int i = 0; i < ownedShips.length; i++) {
            tradeMenuArray = ArrayMethods.addToArray(tradeMenuArray, ownedShips[i]);
        }
        for (int i = 0; i < ownedBreweries.length; i++) {
            tradeMenuArray = ArrayMethods.addToArray(tradeMenuArray, ownedBreweries[i]);
        }


        String playerChoice;
        int offerAmount = 0;

        do {

            playerChoice = guiController.scrollList("Your offer: ", tradeMenuArray);
            if (!playerChoice.equals("Money offer") && !playerChoice.equals("Next") && !playerChoice.equals("Go back")) {
                tradeOfferArray = ArrayMethods.addToArray(tradeOfferArray, playerChoice);
                tradeMenuArray = ArrayMethods.removeFromArray(tradeMenuArray, playerChoice);
            }
            if (playerChoice.equals("Money offer")) {
                offerAmount = guiController.getUserInteger("What amount of money do you want to offer", 0, playerList.getPlayer().getBalance().getAmount());

            }



            } while (!playerChoice.equals("Go back") && !playerChoice.equals("Next"));

        String[] playerNames = {"Go back"};
        String chosenTrader;
        for (int i = 0; i < playerList.getPlayerNames().length; i++) {
            playerNames = ArrayMethods.addToArray(playerNames,playerList.getPlayerNames()[i]);
        }
        playerNames = ArrayMethods.removeFromArray(playerNames,playerList.getPlayer().getName());

        if (playerChoice.equals("Next")) {
            chosenTrader = guiController.scrollList("Who do you want to trade with? ", playerNames);
        }

    }
}

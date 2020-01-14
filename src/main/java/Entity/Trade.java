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
            playerNames = ArrayMethods.addToArray(playerNames, playerList.getPlayerNames()[i]);
        }
        playerNames = ArrayMethods.removeFromArray(playerNames, playerList.getPlayer().getName());

        int receivingAmount = 0;
        String[] receivingOfferArray = new String[0];

        if (playerChoice.equals("Next")) {
            chosenTrader = guiController.scrollList("Who do you want to trade with? ", playerNames);

            if (!chosenTrader.equals("Go back")) {
                String theirOffer;
                String[] theirOfferArray = new String[3];
                theirOfferArray[0] = "Go back";
                theirOfferArray[1] = "End trade";
                theirOfferArray[2] = "Money offer";

                String[] tradersStreets = squareList.getOwnedStreetNames(chosenTrader);
                String[] tradersShips = squareList.getOwnedShipNames(chosenTrader);
                String[] tradersBreweries = squareList.getOwnedBreweryNames(chosenTrader);

                for (int i = 0; i < tradersStreets.length; i++) {
                    if (!squareList.checkAnyHouse(squareList.searchStreet(tradersStreets[i]), chosenTrader)) {
                        theirOfferArray = ArrayMethods.addToArray(theirOfferArray, tradersStreets[i]);
                    }
                }
                for (int i = 0; i < tradersShips.length; i++) {
                    theirOfferArray = ArrayMethods.addToArray(theirOfferArray, tradersShips[i]);
                }
                for (int i = 0; i < tradersBreweries.length; i++) {
                    theirOfferArray = ArrayMethods.addToArray(theirOfferArray, tradersBreweries[i]);
                }
            do {
                theirOffer = guiController.scrollList("What do want in exchange for your offer?", theirOfferArray);

                if (!theirOffer.equals("Money offer") && !theirOffer.equals("End trade") && !theirOffer.equals("Go back")) {
                    receivingOfferArray = ArrayMethods.addToArray(receivingOfferArray, theirOffer);
                    theirOfferArray = ArrayMethods.removeFromArray(theirOfferArray, theirOffer);
                }
                if (theirOffer.equals("Money offer")) {
                    receivingAmount = guiController.getUserInteger("What amount of money do you want to offer", 0, playerList.searchPlayer(chosenTrader).getBalance().getAmount());
                }
            } while (!theirOffer.equals("Go back") && !theirOffer.equals("End trade"));

                if (theirOffer.equals("Go back")) {
                    return;
                }
                if (theirOffer.equals("End trade")) {
                    String offerString = "";
                    for (int i = 0; i < tradeOfferArray.length; i++) {
                        offerString += tradeOfferArray[i] + ", ";
                    }

                    String receivingString = "" ;

                    for (int i = 0; i < receivingOfferArray.length; i++) {
                        receivingString += receivingOfferArray[i] + ", ";
                    }

                    String tradingAnswser = guiController.button(playerName + "'s offer:" + " Amount:  " + offerAmount + ""  + "  Properties: " + offerString + ". " + chosenTrader + "'s offer: " + " Amount: " + receivingAmount + "" + "  Properties: " + receivingString + chosenTrader + "                                     Do you accept the offer?", "Yes", "No");

                    if (tradingAnswser.equals("Yes")) {

                        for (int i = 0; i < tradeOfferArray.length; i++) {
                            squareList.searchStreet(tradeOfferArray[i]).setOwner(chosenTrader);
                            guiController.buyProperty(chosenTrader,squareList.searchProperty(tradeOfferArray[i]).getFieldPosition());
                        }

                        for (int i = 0; i < receivingOfferArray.length; i++) {
                            squareList.searchStreet(receivingOfferArray[i]).setOwner(playerName);
                            guiController.buyProperty(playerName,squareList.searchProperty(receivingOfferArray[i]).getFieldPosition());
                        }

                        playerList.searchPlayer(playerName).getBalance().add(receivingAmount - offerAmount);
                        playerList.searchPlayer(chosenTrader).getBalance().add(offerAmount - receivingAmount);

                        guiController.updateBalance(playerName, playerList.searchPlayer(playerName).getBalance().getAmount());
                        guiController.updateBalance(chosenTrader, playerList.searchPlayer(chosenTrader).getBalance().getAmount());
                    }
                }

            }


        }
    }
}

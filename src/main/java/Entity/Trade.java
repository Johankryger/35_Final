package Entity;

import Controller.GUIController;
import Entity.square.SquareController;
import message.Message;
import staticclasses.ArrayMethods;

public class Trade {

    public void trade(PlayerList playerList, SquareController squareList, GUIController guiController) {
        String playerName = playerList.getPlayer().getName();
        String[] owendStreets = squareList.getOwnedStreetNames(playerName);
        String[] ownedShips = squareList.getOwnedShipNames(playerName);
        String[] ownedBreweries = squareList.getOwnedBreweryNames(playerName);
        String[] tradeMenuArray = new String[3];
        String[] tradeOfferArray = new String[0];

        tradeMenuArray[0] = Message.getMessage("Trading", 1);
        tradeMenuArray[1] = Message.getMessage("Trading", 2);
        tradeMenuArray[2] = Message.getMessage("Trading", 3);


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
        String choosenAmount = " DDK: " + offerAmount;
        String choosenProperty = " ";
        do {

            playerChoice = guiController.scrollList((Message.getMessage("Trading",15)) + choosenAmount + choosenProperty, tradeMenuArray);
            if (!playerChoice.equals(Message.getMessage("Trading", 3)) && !playerChoice.equals(Message.getMessage("Trading", 2)) && !playerChoice.equals(Message.getMessage("Trading", 1))) {
                tradeOfferArray = ArrayMethods.addToArray(tradeOfferArray, playerChoice);
                tradeMenuArray = ArrayMethods.removeFromArray(tradeMenuArray, playerChoice);
                choosenProperty = choosenProperty+ playerChoice + ", ";
            }
            if (playerChoice.equals(Message.getMessage("Trading", 3))) {
                offerAmount = 1;
                while (offerAmount % 50 != 0 || playerList.getPlayer().getBalance().getAmount() < offerAmount) {
                    offerAmount = guiController.getUserInteger(Message.getMessage("Trading", 6), 0, playerList.getPlayer().getBalance().getAmount());
                }
                choosenAmount = " DDK: " + offerAmount;
            }


        } while (!playerChoice.equals(Message.getMessage("Trading", 1)) && !playerChoice.equals(Message.getMessage("Trading" , 2)));

        String[] playerNames = {Message.getMessage("Trading", 1)};
        String chosenTrader;
        for (int i = 0; i < playerList.getPlayerNames().length; i++) {
            playerNames = ArrayMethods.addToArray(playerNames, playerList.getPlayerNames()[i]);
        }
        playerNames = ArrayMethods.removeFromArray(playerNames, playerList.getPlayer().getName());

        int receivingAmount = 0;
        String[] receivingOfferArray = new String[0];

        if (playerChoice.equals(Message.getMessage("Trading", 2))) {
            chosenTrader = guiController.scrollList(Message.getMessage("Trading", 10), playerNames);

            if (!chosenTrader.equals(Message.getMessage("Trading", 1))) {
                String theirOffer;
                String[] theirOfferArray = new String[3];
                theirOfferArray[0] = Message.getMessage("Trading", 1);
                theirOfferArray[1] = Message.getMessage("Trading", 4);
                theirOfferArray[2] = Message.getMessage("Trading", 3);

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
                choosenAmount = " DDK: " + receivingAmount;
                choosenProperty = " ";
            do {
                theirOffer = guiController.scrollList(Message.getMessage("Trading", 5) + choosenAmount + choosenProperty, theirOfferArray);

                if (!theirOffer.equals(Message.getMessage("Trading", 3)) && !theirOffer.equals(Message.getMessage("Trading", 4)) && !theirOffer.equals(Message.getMessage("Trading", 1))) {
                    receivingOfferArray = ArrayMethods.addToArray(receivingOfferArray, theirOffer);
                    theirOfferArray = ArrayMethods.removeFromArray(theirOfferArray, theirOffer);
                    choosenProperty = choosenProperty+ theirOffer + ", ";
                }
                if (theirOffer.equals(Message.getMessage("Trading", 3))) {
                    receivingAmount = 1;
                    while (receivingAmount % 50 != 0 || playerList.searchPlayer(chosenTrader).getBalance().getAmount() < receivingAmount) {
                        receivingAmount = guiController.getUserInteger(Message.getMessage("Trading", 7), 0, playerList.searchPlayer(chosenTrader).getBalance().getAmount());
                    }
                    choosenAmount = " DDK: " + receivingAmount;
                }
            } while (!theirOffer.equals(Message.getMessage("Trading", 1)) && !theirOffer.equals(Message.getMessage("Trading", 4)));

                if (theirOffer.equals(Message.getMessage("Trading", 1))) {
                    return;
                }
                if (theirOffer.equals(Message.getMessage("Trading", 4))) {
                    String offerString = "";
                    for (int i = 0; i < tradeOfferArray.length; i++) {
                        offerString += tradeOfferArray[i] + ", ";
                    }

                    String receivingString = "" ;

                    for (int i = 0; i < receivingOfferArray.length; i++) {
                        receivingString += receivingOfferArray[i] + ", ";
                    }

                    String tradingAnswser = guiController.button(playerName + Message.getMessage("Trading", 11) + ":  " + Message.getMessage("Trading", 12) + ": " + offerAmount + "  "  + Message.getMessage("Trading", 13) + "   " + offerString + " " + chosenTrader + Message.getMessage("Trading", 11) + ":  " + Message.getMessage("Trading", 12) + ":  " + receivingAmount + "  " + Message.getMessage("Trading", 13) + ":  " + receivingString + "            " +chosenTrader + ":  " + Message.getMessage("Trading", 14), Message.getMessage("Trading", 8), Message.getMessage("Trading", 9));

                    if (tradingAnswser.equals(Message.getMessage("Trading", 8))) {

                        for (int i = 0; i < tradeOfferArray.length; i++) {
                            squareList.searchProperty(tradeOfferArray[i]).setOwner(chosenTrader);
                            if (squareList.searchProperty(tradeOfferArray[i]).getMortgaged()) {
                                guiController.mortgageProperty(chosenTrader,squareList.searchProperty(tradeOfferArray[i]).getFieldPosition());
                            } else {
                                guiController.buyProperty(chosenTrader,squareList.searchProperty(tradeOfferArray[i]).getFieldPosition());
                            }
                        }

                        for (int i = 0; i < receivingOfferArray.length; i++) {
                            squareList.searchProperty(receivingOfferArray[i]).setOwner(playerName);
                            if (squareList.searchProperty(receivingOfferArray[i]).getMortgaged()) {
                                guiController.mortgageProperty(playerName,squareList.searchProperty(receivingOfferArray[i]).getFieldPosition());
                            } else {
                                guiController.buyProperty(playerName,squareList.searchProperty(receivingOfferArray[i]).getFieldPosition());
                            }
                        }

                        if (receivingAmount - offerAmount > 0) {
                            playerList.searchPlayer(playerName).getBalance().add(receivingAmount - offerAmount);
                            playerList.searchPlayer(chosenTrader).getBalance().pay(receivingAmount - offerAmount);
                        } else if (receivingAmount - offerAmount < 0) {
                            playerList.searchPlayer(playerName).getBalance().pay(offerAmount - receivingAmount);
                            playerList.searchPlayer(chosenTrader).getBalance().add(offerAmount - receivingAmount);
                        }


                        guiController.updateBalance(playerName, playerList.searchPlayer(playerName).getBalance().getAmount());
                        guiController.updateBalance(chosenTrader, playerList.searchPlayer(chosenTrader).getBalance().getAmount());
                    }
                }
            }
        }
    }
}

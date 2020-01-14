package Entity;

import Controller.GUIController;
import Entity.square.SquareList;
import message.Message;
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

        do {

            playerChoice = guiController.scrollList("Your offer: ", tradeMenuArray);
            if (!playerChoice.equals(Message.getMessage("Trading", 3)) && !playerChoice.equals(Message.getMessage("Trading", 2)) && !playerChoice.equals(Message.getMessage("Trading", 1))) {
                tradeOfferArray = ArrayMethods.addToArray(tradeOfferArray, playerChoice);
                tradeMenuArray = ArrayMethods.removeFromArray(tradeMenuArray, playerChoice);
            }
            if (playerChoice.equals(Message.getMessage("Trading", 3))) {
                offerAmount = guiController.getUserInteger(Message.getMessage("Trading", 6), 0, playerList.getPlayer().getBalance().getAmount());

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
            do {
                theirOffer = guiController.scrollList(Message.getMessage("Trading", 5), theirOfferArray);

                if (!theirOffer.equals(Message.getMessage("Trading", 3)) && !theirOffer.equals(Message.getMessage("Trading", 4)) && !theirOffer.equals(Message.getMessage("Trading", 1))) {
                    receivingOfferArray = ArrayMethods.addToArray(receivingOfferArray, theirOffer);
                    theirOfferArray = ArrayMethods.removeFromArray(theirOfferArray, theirOffer);
                }
                if (theirOffer.equals(Message.getMessage("Trading", 3))) {
                    receivingAmount = guiController.getUserInteger(Message.getMessage("Trading", 7), 0, playerList.searchPlayer(chosenTrader).getBalance().getAmount());
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

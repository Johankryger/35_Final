package Controller;

import Entity.PlayerList;

import Entity.Trade;
import Entity.square.Property;
import Entity.square.SquareController;
import Entity.square.Street;
import message.Message;
import staticclasses.ArrayMethods;

public class PropertyController {

    private Trade trade = new Trade();

    public String[] makeMortgageArray(String playerName, SquareController squareList){
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
        String[] mortgageMenu = new String[1];
        mortgageMenu[0] = Message.getMessage("Manage", 1);

        for (int i = 0; i < ownedProperties.length; i++) {
            if (canMortgage(squareList.searchProperty(ownedProperties[i]), squareList))
                mortgageMenu = ArrayMethods.addToArray(mortgageMenu,ownedProperties[i]);
        }
        return mortgageMenu;
    }

    public String[] unMortgageArray(String playerName, SquareController squareList){
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
        String[] unMortgageMenu = new String[1];
        unMortgageMenu[0] = Message.getMessage("Manage", 1);

        for (int i = 0; i < ownedProperties.length; i++) {
            if (squareList.searchProperty(ownedProperties[i]).getMortgaged())
                unMortgageMenu = ArrayMethods.addToArray(unMortgageMenu,ownedProperties[i]);
        }
        return unMortgageMenu;
    }

    public String[] buyHouseArray(String playerName, SquareController squareList){
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);
        String[] buyHouseMenu = new String[1];
        buyHouseMenu[0] = Message.getMessage("Manage", 1);

        for (int i = 0; i < ownedStreets.length; i++) {
            if (canBuildHouse(squareList.searchStreet(ownedStreets[i]), squareList)){
                buyHouseMenu = ArrayMethods.addToArray(buyHouseMenu,ownedStreets[i]);
            }
        }
        return buyHouseMenu;
    }


    public String[] sellHouseArray(String playerName, SquareController squareList) {
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);
        String[] sellHouseMenu = new String[1];
        sellHouseMenu[0] = Message.getMessage("Manage", 1);

        for (int i = 0; i < ownedStreets.length; i++) {
            if (canSellHouse(squareList.searchStreet(ownedStreets[i]), squareList)) {
                sellHouseMenu = ArrayMethods.addToArray(sellHouseMenu,ownedStreets[i]);
            }
        }
        return sellHouseMenu;
    }


    public void manageMenu(GUIController guiController, PlayerList playerList, SquareController squareList) {
            String option2 = guiController.scrollList(Message.getMessage("Manage", 2), Message.getMessage("Manage", 1), Message.getMessage("Manage", 4), Message.getMessage("Manage", 5), Message.getMessage("Manage", 6), Message.getMessage("Manage", 7), Message.getMessage("Manage", 8));
            String name = playerList.getPlayer().getName();

            switch (option2) {
                case "Go back":
                case "Gå tilbage":
                    break;
                case "Mortgage":
                case "Pantsæt":
                    String mortgageOption;
                    do {
                        mortgageOption = guiController.scrollList(Message.getMessage("Manage", 3), makeMortgageArray(name, squareList));
                        if (!mortgageOption.equals(Message.getMessage("Manage", 1))) {
                            squareList.searchProperty(mortgageOption).setMortgaged(true);
                            int moneyBack = squareList.searchProperty(mortgageOption).getPrice() / 2;
                            playerList.getPlayer().getBalance().add(moneyBack);
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.mortgageProperty(name, squareList.searchProperty(mortgageOption).getFieldPosition());
                        }
                    } while (!mortgageOption.equals(Message.getMessage("Manage", 1)));
                    manageMenu(guiController, playerList, squareList);
                    break;
                case "Unmortgage":
                case "Hæv pantsætning":
                    String unMortgageOption;
                    do {
                        unMortgageOption = guiController.scrollList(Message.getMessage("Manage", 3), unMortgageArray(name, squareList));
                        if (!unMortgageOption.equals(Message.getMessage("Manage", 1))) {
                            double toPay = squareList.searchProperty(unMortgageOption).getPrice() / 2 * 1.1;
                            if (toPay % 100 >= 50) {
                                toPay = toPay - (toPay % 100) + 100;
                            } else {
                                toPay = toPay - (toPay % 100);
                            }
                            int mortgagePrice = (int) toPay;
                            if (toPay <= playerList.getPlayer().getBalance().getAmount()) {
                                squareList.searchProperty(unMortgageOption).setMortgaged(false);
                                playerList.getPlayer().getBalance().pay(mortgagePrice);
                                guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                                guiController.buyProperty(name, squareList.searchProperty(unMortgageOption).getFieldPosition());
                            } else {
                                guiController.button("You can't afford that!", "ok");
                            }
                        }
                    } while (!unMortgageOption.equals(Message.getMessage("Manage", 1)));
                    manageMenu(guiController, playerList, squareList);
                    break;
                case "Build house":
                case "Byg hus":
                    String buildHouseOption;
                    do {
                        buildHouseOption = guiController.scrollList(Message.getMessage("Manage", 3), buyHouseArray(name, squareList));
                        if (!buildHouseOption.equals(Message.getMessage("Manage", 1))) {
                            int housePrice = squareList.searchStreet(buildHouseOption).getHousePrice();
                            if (housePrice <= playerList.getPlayer().getBalance().getAmount()) {
                                playerList.getPlayer().getBalance().pay(housePrice);
                                squareList.searchStreet(buildHouseOption).addHouse();
                                guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                                guiController.setHouses(squareList.searchStreet(buildHouseOption).getNumberOfHouses(), squareList.searchStreet(buildHouseOption).getFieldPosition());
                            } else {
                                guiController.button("You can't afford that!", "ok");
                            }
                        }
                    } while (!buildHouseOption.equals(Message.getMessage("Manage", 1)));
                    manageMenu(guiController, playerList, squareList);
                    break;
                case "Sell house":
                case "Sælg hus":
                    String sellHouseOption;
                    do {
                        sellHouseOption = guiController.scrollList(Message.getMessage("Manage", 3), sellHouseArray(name, squareList));
                        if (!sellHouseOption.equals(Message.getMessage("Manage", 1))) {
                            int housePrice = squareList.searchStreet(sellHouseOption).getHousePrice() / 2;
                            playerList.getPlayer().getBalance().add(housePrice);
                            squareList.searchStreet(sellHouseOption).removeHouse();
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.setHouses(squareList.searchStreet(sellHouseOption).getNumberOfHouses(), squareList.searchStreet(sellHouseOption).getFieldPosition());
                        }
                    } while (!sellHouseOption.equals(Message.getMessage("Manage", 1)));
                    manageMenu(guiController, playerList, squareList);
                    break;
                case "Trade":
                case "Byttehandel":
                   trade.trade(playerList,squareList,guiController);
                        break;

            }


    }

    public void calculateLiquidation(PlayerList playerList, SquareController squareController) {
        String[] playerNames = playerList.getPlayerNames();
        for (int i = 0; i < playerNames.length; i++) {
            int liquidationMoney = playerList.searchPlayer(playerNames[i]).getBalance().getAmount();

            String[] ownedStreets = squareController.getOwnedStreetNames(playerList.searchPlayer(playerNames[i]).getName());
            String[] ownedProperties = squareController.getOwnedPropertyNames(playerList.searchPlayer(playerNames[i]).getName());

            for (int j = 0; j < ownedProperties.length; j++) {
                if (!squareController.searchProperty(ownedProperties[j]).getMortgaged()) {
                    liquidationMoney += squareController.searchProperty(ownedProperties[j]).getPrice() / 2;
                }
            }

            for (int j = 0; j < ownedStreets.length; j++) {
                if (squareController.searchStreet(ownedStreets[j]).isPaired()) {
                    int numberOfHouses= squareController.searchStreet(ownedStreets[j]).getNumberOfHouses();
                    int housePrice = squareController.searchStreet(ownedStreets[j]).getHousePrice();
                    liquidationMoney += numberOfHouses * housePrice;
                }
            }
            playerList.searchPlayer(playerNames[i]).setLiquidationValue(liquidationMoney);
        }
    }

    public void payment(PlayerList playerList, String playerPay, String playerReceiver, SquareController squareController, GUIController guiController, int amountToPay) {
        calculateLiquidation(playerList, squareController);
        // if a player loses
        if (playerList.searchPlayer(playerPay).getLiquidationValue() < amountToPay) {
            // if you have not lost to a player
            if (playerList.searchPlayer(playerReceiver) == null) {
                String[] ownedProperties = squareController.getOwnedPropertyNames(playerList.searchPlayer(playerPay).getName());
                for (String p : ownedProperties) {
                    squareController.searchProperty(p).setMortgaged(false);
                    if (squareController.searchStreet(p) != null) {
                        squareController.searchStreet(p).setNumberOfHouses(0);
                        guiController.setHouses(0,squareController.searchStreet(p).getFieldPosition());
                    }
                    squareController.searchProperty(p).setOwner("bank");
                }
            } else {  // if player loses to a player their houses is sold
                // sell houses and transfer money
                String[] ownedProperties = squareController.getOwnedPropertyNames(playerList.searchPlayer(playerPay).getName());
                for (String s : ownedProperties) {
                    if (squareController.searchStreet(s) != null) {
                        int totalHouseMoney = 0;
                        int houses = squareController.searchStreet(s).getNumberOfHouses();
                        int houseMoney = squareController.searchStreet(s).getHousePrice() / 2;
                        totalHouseMoney += houses * houseMoney;
                        squareController.searchStreet(s).setNumberOfHouses(0);
                        guiController.setHouses(0,squareController.searchStreet(s).getFieldPosition());
                        playerList.searchPlayer(playerReceiver).getBalance().add(totalHouseMoney);
                    }
                    squareController.searchProperty(s).setOwner(playerReceiver); //sets ownership to all properties to the receiver
                    if (squareController.searchProperty(s).getMortgaged()) {
                        guiController.mortgageProperty(playerReceiver, squareController.searchProperty(s).getFieldPosition());
                    } else {
                        guiController.buyProperty(playerReceiver, squareController.searchProperty(s).getFieldPosition());
                    }
                }
                playerList.searchPlayer(playerReceiver).getBalance().add(playerList.searchPlayer(playerPay).getBalance().getAmount());
                guiController.updateBalance(playerReceiver, playerList.searchPlayer(playerReceiver).getBalance().getAmount());
            }

            // removes player from game
            guiController.removeLoser(playerList.searchPlayer(playerPay).getName(), playerList.searchPlayer(playerPay).getFieldPos(), squareController);
            playerList.killPlayer(playerList.searchPlayer(playerPay).getName());
            guiController.button(playerPay + " has lost", "Ok");
            guiController.killPlayer(playerPay, playerList.getPlayerNames().length + 1);

            // if player needs to sell houses or mortgage to afford
        } else if (playerList.searchPlayer(playerPay).getBalance().getAmount() < amountToPay) {
            while (playerList.searchPlayer(playerPay).getBalance().getAmount() < amountToPay) {
                String option = guiController.button(Message.getMessage("Manage", 9) + " " + amountToPay, Message.getMessage("Manage", 4), Message.getMessage("Manage", 7));
                if (option.equals(Message.getMessage("Manage", 4))) {
                    String mortgageOption;
                    do {
                        mortgageOption = guiController.scrollList(Message.getMessage("Manage", 3), makeMortgageArray(playerPay, squareController));
                        if (!mortgageOption.equals(Message.getMessage("Manage", 1))) {
                            squareController.searchProperty(mortgageOption).setMortgaged(true);
                            int moneyBack = squareController.searchProperty(mortgageOption).getPrice() / 2;
                            playerList.searchPlayer(playerPay).getBalance().add(moneyBack);
                            guiController.updateBalance(playerPay, playerList.searchPlayer(playerPay).getBalance().getAmount());
                            guiController.mortgageProperty(playerPay, squareController.searchProperty(mortgageOption).getFieldPosition());
                        }
                    } while (!mortgageOption.equals(Message.getMessage("Manage", 1)));
                    if (playerList.searchPlayer(playerPay).getBalance().getAmount() < amountToPay) {
                        payment(playerList, playerPay, playerReceiver, squareController, guiController, amountToPay);
                    }
                } else if (option.equals(Message.getMessage("Manage", 7))) {
                    String sellHouseOption;
                    do {
                        sellHouseOption = guiController.scrollList(Message.getMessage("Manage", 3), sellHouseArray(playerPay, squareController));
                        if (!sellHouseOption.equals(Message.getMessage("Manage", 1))) {
                            int housePrice = squareController.searchStreet(sellHouseOption).getHousePrice() / 2;
                            playerList.searchPlayer(playerPay).getBalance().add(housePrice);
                            squareController.searchStreet(sellHouseOption).removeHouse();
                            guiController.updateBalance(playerPay, playerList.searchPlayer(playerPay).getBalance().getAmount());
                            guiController.setHouses(squareController.searchStreet(sellHouseOption).getNumberOfHouses(), squareController.searchStreet(sellHouseOption).getFieldPosition());
                        }
                    } while (!sellHouseOption.equals(Message.getMessage("Manage", 1)));
                    if (playerList.searchPlayer(playerPay).getBalance().getAmount() < amountToPay) {
                        payment(playerList, playerPay, playerReceiver, squareController, guiController, amountToPay);
                    }
                }
            }
        // if player afford to pay with cash
        } else if (playerList.searchPlayer(playerPay).getBalance().getAmount() >= amountToPay) {
            playerList.searchPlayer(playerPay).getBalance().pay(amountToPay);
            if (playerReceiver != null){
                playerList.searchPlayer(playerReceiver).getBalance().add(amountToPay);
            }

        }

    }

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

        if (!street.isPaired() || numberOfHouses == 5 || street.getMortgaged()) {
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

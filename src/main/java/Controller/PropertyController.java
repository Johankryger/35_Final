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
                            squareList.searchProperty(unMortgageOption).setMortgaged(false);
                            double toPay = squareList.searchProperty(unMortgageOption).getPrice() / 2 * 1.1;
                            if (toPay % 100 >= 50) {
                                toPay = toPay - (toPay % 100) + 100;
                            } else {
                                toPay = toPay - (toPay % 100);
                            }
                            int mortgagePrice = (int) toPay;
                            playerList.getPlayer().getBalance().pay(mortgagePrice);
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.buyProperty(name, squareList.searchProperty(unMortgageOption).getFieldPosition());
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
                            playerList.getPlayer().getBalance().pay(housePrice);
                            squareList.searchStreet(buildHouseOption).addHouse();
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.setHouses(squareList.searchStreet(buildHouseOption).getNumberOfHouses(), squareList.searchStreet(buildHouseOption).getFieldPosition());
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

    public void liquidateMenu(PlayerList playerList, String playerName, SquareController squareList, GUIController guiController, int amountToPay) {
        String option = guiController.button((Message.getMessage("Manage",2) + " "), Message.getMessage("Manage", 4), Message.getMessage("Manage", 7), Message.getMessage("Manage", 8));

        if (option.equals(Message.getMessage("Manage", 4))) {
            String mortgageOption;
            do {
                mortgageOption = guiController.scrollList(Message.getMessage("Manage", 3), makeMortgageArray(playerName,squareList));
                if (!mortgageOption.equals(Message.getMessage("Manage", 1))) {
                    squareList.searchProperty(mortgageOption).setMortgaged(true);
                    int moneyBack = squareList.searchProperty(mortgageOption).getPrice() / 2;
                    playerList.searchPlayer(playerName).getBalance().add(moneyBack);
                    guiController.updateBalance(playerName, playerList.getPlayer().getBalance().getAmount());
                    guiController.mortgageProperty(playerName, squareList.searchProperty(mortgageOption).getFieldPosition());
                }
            } while (!mortgageOption.equals(Message.getMessage("Manage", 1)));
            if (playerList.searchPlayer(playerName).getBalance().getAmount() < amountToPay) {
                liquidateMenu(playerList, playerName, squareList, guiController, amountToPay);
            }
        } else if (option.equals(Message.getMessage("Manage", 7))) {
            String sellHouseOption;
            do {
                sellHouseOption = guiController.scrollList(Message.getMessage("Manage", 3), sellHouseArray(playerName, squareList));
                if (!sellHouseOption.equals(Message.getMessage("Manage", 1))) {
                    int housePrice = squareList.searchStreet(sellHouseOption).getHousePrice() / 2;
                    playerList.searchPlayer(playerName).getBalance().add(housePrice);
                    squareList.searchStreet(sellHouseOption).removeHouse();
                    guiController.updateBalance(playerName, playerList.getPlayer().getBalance().getAmount());
                    guiController.setHouses(squareList.searchStreet(sellHouseOption).getNumberOfHouses(), squareList.searchStreet(sellHouseOption).getFieldPosition());
                }
            } while (!sellHouseOption.equals(Message.getMessage("Manage", 1)));
            if (playerList.searchPlayer(playerName).getBalance().getAmount() < amountToPay) {
                liquidateMenu(playerList, playerName, squareList, guiController, amountToPay);
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

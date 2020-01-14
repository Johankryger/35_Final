package Controller;

import Entity.PlayerList;
import Entity.PropertyLogic;
import Entity.square.SquareList;
import staticclasses.ArrayMethods;

public class PropertyController {
    private PropertyLogic propertyLogic = new PropertyLogic();

    public String[] makeMortgageArray(String playerName, SquareList squareList){
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
        String[] mortgageMenu = new String[1];
        mortgageMenu[0] = "Go back";

        for (int i = 0; i < ownedProperties.length; i++) {
            if (propertyLogic.canMortgage(squareList.searchProperty(ownedProperties[i]), squareList))
                mortgageMenu = ArrayMethods.addToArray(mortgageMenu,ownedProperties[i]);
        }
        return mortgageMenu;
    }

    public String[] unMortgageArray(String playerName,SquareList squareList){
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
        String[] unMortgageMenu = new String[1];
        unMortgageMenu[0] = "Go back";

        for (int i = 0; i < ownedProperties.length; i++) {
            if (squareList.searchProperty(ownedProperties[i]).getMortgaged())
                unMortgageMenu = ArrayMethods.addToArray(unMortgageMenu,ownedProperties[i]);
        }
        return unMortgageMenu;
    }

    public String[] buyHouseArray(String playerName, SquareList squareList){
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);
        String[] buyHouseMenu = new String[1];
        buyHouseMenu[0] = "Go back";

        for (int i = 0; i < ownedStreets.length; i++) {
            if (propertyLogic.canBuildHouse(squareList.searchStreet(ownedStreets[i]), squareList)){
                buyHouseMenu = ArrayMethods.addToArray(buyHouseMenu,ownedStreets[i]);
            }
        }
        return buyHouseMenu;
    }

    public String[] tradeOptionArray(String playerName, SquareList squareList){
        String[] owendStreets = squareList.getOwnedStreetNames(playerName);
        String[] ownedShips = squareList.getOwnedShipNames(playerName);
        String[] ownedBreweries = squareList.getOwnedBreweryNames(playerName);
        String[] tradeMenu  = new String[3];
        tradeMenu[0] = "Go back";
        tradeMenu[1] = "Choose trader";
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

    public String[] sellHouseArray(String playerName, SquareList squareList) {
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);
        String[] sellHouseMenu = new String[1];
        sellHouseMenu[0] = "Go back";

        for (int i = 0; i < ownedStreets.length; i++) {
            if (propertyLogic.canSellHouse(squareList.searchStreet(ownedStreets[i]), squareList)) {
                sellHouseMenu = ArrayMethods.addToArray(sellHouseMenu,ownedStreets[i]);
            }
        }
        return sellHouseMenu;
    }



    public void manageMenu(GUIController guiController, PlayerList playerList, SquareList squareList) {
            String option2 = guiController.scrollList("Choose option", "Go back", "Mortgage", "Unmortgage", "Buy house", "Sell house", "Trade");
            String name = playerList.getPlayer().getName();

            switch (option2){
                case "Go back":
                    break;
                case "Mortgage":
                    String mortgageOption;
                    do {
                        mortgageOption = guiController.scrollList("Choose property", makeMortgageArray(name,squareList));
                        if (!mortgageOption.equals("Go back")) {
                            squareList.searchProperty(mortgageOption).setMortgaged(true);
                            int moneyBack = squareList.searchProperty(mortgageOption).getPrice() / 2;
                            playerList.getPlayer().getBalance().add(moneyBack);
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.mortgageProperty(name, squareList.searchProperty(mortgageOption).getFieldPosition());
                        }
                    } while (!mortgageOption.equals("Go back"));
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Unmortgage":
                    String unMortgageOption;
                    do {
                        unMortgageOption = guiController.scrollList("Choose property", unMortgageArray(name,squareList));
                        if (!unMortgageOption.equals("Go back")) {
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
                    } while (!unMortgageOption.equals("Go back"));
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Buy house":
                    String buildHouseOption;
                    do {
                        buildHouseOption = guiController.scrollList("Choose property", buyHouseArray(name, squareList));
                        if (!buildHouseOption.equals("Go back")) {
                            int housePrice = squareList.searchStreet(buildHouseOption).getHousePrice();
                            playerList.getPlayer().getBalance().pay(housePrice);
                            squareList.searchStreet(buildHouseOption).addHouse();
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.setHouses(squareList.searchStreet(buildHouseOption).getNumberOfHouses(), squareList.searchStreet(buildHouseOption).getFieldPosition());
                        }
                    } while (!buildHouseOption.equals("Go back"));
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Sell house":
                    String sellHouseOption;
                    do {
                        sellHouseOption = guiController.scrollList("Choose property", sellHouseArray(name, squareList));
                        if (!sellHouseOption.equals("Go back")) {
                            int housePrice = squareList.searchStreet(sellHouseOption).getHousePrice() / 2;
                            playerList.getPlayer().getBalance().add(housePrice);
                            squareList.searchStreet(sellHouseOption).removeHouse();
                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
                            guiController.setHouses(squareList.searchStreet(sellHouseOption).getNumberOfHouses(), squareList.searchStreet(sellHouseOption).getFieldPosition());
                        }
                    } while (!sellHouseOption.equals("Go back"));
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Trade":
                    String tradeOption;
                    do {
                        tradeOption = guiController.scrollList("Choose what to trade", tradeOptionArray(name, squareList));
                        if (!tradeOption.equals("Go back")) {
                            int index = 0;
                            if (!tradeOption.equals("Choose trader") && !tradeOption.equals("Money offer")){
                                String[] chosenProerties = new String[0];
                                chosenProerties = ArrayMethods.addToArray(chosenProerties,tradeOption);
                                ArrayMethods.removeFromArray(tradeOptionArray(name,squareList),tradeOption);
                            }


//                            int housePrice = squareList.searchStreet(sellHouseOption).getHousePrice() / 2;
//                            playerList.getPlayer().getBalance().add(housePrice);
//                            squareList.searchStreet(sellHouseOption).removeHouse();
//                            guiController.updateBalance(name, playerList.getPlayer().getBalance().getAmount());
//                            guiController.setHouses(squareList.searchStreet(sellHouseOption).getNumberOfHouses(), squareList.searchStreet(sellHouseOption).getFieldPosition());
                        }
                    } while (!tradeOption.equals("Go back"));
                    manageMenu(guiController,playerList,squareList);
                    break;
            }


    }

    public void liquidateMenu(PlayerList playerList, String playerName, SquareList squareList, GUIController guiController, int amountToPay) {
        String option = guiController.button("Choose option", "Mortgage", "Sell house", "Trade");

        if (option.equals("Mortgage")) {
            String mortgageOption;
            do {
                mortgageOption = guiController.scrollList("Choose property", makeMortgageArray(playerName,squareList));
                if (!mortgageOption.equals("Go back")) {
                    squareList.searchProperty(mortgageOption).setMortgaged(true);
                    int moneyBack = squareList.searchProperty(mortgageOption).getPrice() / 2;
                    playerList.searchPlayer(playerName).getBalance().add(moneyBack);
                    guiController.updateBalance(playerName, playerList.getPlayer().getBalance().getAmount());
                    guiController.mortgageProperty(playerName, squareList.searchProperty(mortgageOption).getFieldPosition());
                }
            } while (!mortgageOption.equals("Go back"));
            if (playerList.searchPlayer(playerName).getBalance().getAmount() < amountToPay) {
                liquidateMenu(playerList, playerName, squareList, guiController, amountToPay);
            }
        } else if (option.equals("Sell house")) {
            String sellHouseOption;
            do {
                sellHouseOption = guiController.scrollList("Choose property", sellHouseArray(playerName, squareList));
                if (!sellHouseOption.equals("Go back")) {
                    int housePrice = squareList.searchStreet(sellHouseOption).getHousePrice() / 2;
                    playerList.searchPlayer(playerName).getBalance().add(housePrice);
                    squareList.searchStreet(sellHouseOption).removeHouse();
                    guiController.updateBalance(playerName, playerList.getPlayer().getBalance().getAmount());
                    guiController.setHouses(squareList.searchStreet(sellHouseOption).getNumberOfHouses(), squareList.searchStreet(sellHouseOption).getFieldPosition());
                }
            } while (!sellHouseOption.equals("Go back"));
            if (playerList.searchPlayer(playerName).getBalance().getAmount() < amountToPay) {
                liquidateMenu(playerList, playerName, squareList, guiController, amountToPay);
            }
        }
    }







}

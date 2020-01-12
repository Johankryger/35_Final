package Controller;

import Entity.PlayerList;
import Entity.square.SquareList;
import logic.GameLogic;

public class PropertyController {

    public String[] makeMortgageArray(String playerName, SquareList squareList){
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
        int counter = 0;

        for (int i = 0; i < ownedProperties.length; i++) {
            if (GameLogic.canMortgage(squareList.searchProperty(ownedProperties[i]), squareList))
                counter++;
        }

        String[] mortgageMenu = new String[counter + 1];
        mortgageMenu[0] = "Go back";
        counter = 1;

        for (int i = 0; i < ownedProperties.length; i++) {
            if (GameLogic.canMortgage(squareList.searchProperty(ownedProperties[i]), squareList)) {
                mortgageMenu[counter] = ownedProperties[i];
                counter++;
            }

        }

        return mortgageMenu;
    }

    public String[] unMortgageArray(String playerName,SquareList squareList){
        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
        int counter = 0;

        for (int i = 0; i < ownedProperties.length; i++) {
            if (squareList.searchProperty(ownedProperties[i]).getMortgaged())
                counter++;
        }

        String[] unmortgageMenu = new String[counter + 1];
        unmortgageMenu[0] = "Go back";
        counter = 1;

        for (int i = 0; i < ownedProperties.length; i++) {
            if (squareList.searchProperty(ownedProperties[i]).getMortgaged()) {
                unmortgageMenu[counter] = ownedProperties[i];
                counter++;
            }

        }

        return unmortgageMenu;
    }

    public String[] buyHouseArray(String playerName, SquareList squareList){
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);
        int counter = 0;

        for (int i = 0; i < ownedStreets.length; i++) {
            if (GameLogic.canBuildHouse(squareList.searchStreet(ownedStreets[i]), squareList))
                counter++;
        }

        String[] buyHouseMenu = new String[counter + 1];
        buyHouseMenu[0] = "Go back";
        counter = 1;

        for (int i = 0; i < ownedStreets.length; i++) {
            if (GameLogic.canBuildHouse(squareList.searchStreet(ownedStreets[i]), squareList)){
                buyHouseMenu[counter] = ownedStreets[i];
                counter++;
            }
        }

        return buyHouseMenu;
    }

    public String[] sellHouseArray(String playerName, SquareList squareList) {
        String[] ownedStreets = squareList.getOwnedStreetNames(playerName);
        int counter = 0;

        for (int i = 0; i < ownedStreets.length; i++) {
            if (GameLogic.canSellHouse(squareList.searchStreet(ownedStreets[i]), squareList))
                counter++;
        }

        String[] sellHouseMenu = new String[counter + 1];
        sellHouseMenu[0] = "Go back";
        counter = 1;

        for (int i = 0; i < ownedStreets.length; i++) {
            if (GameLogic.canSellHouse(squareList.searchStreet(ownedStreets[i]), squareList)) {
                sellHouseMenu[counter] = ownedStreets[i];
                counter++;
            }

        }

        return sellHouseMenu;
    }



    public void manageMenu(GUIController guiController, PlayerList playerList, SquareList squareList) {

            String option2 = guiController.scrollList("Choose option", "Go back", "Mortgage", "Unmortgage", "Buy house", "Sell house");
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
            }
        }


}

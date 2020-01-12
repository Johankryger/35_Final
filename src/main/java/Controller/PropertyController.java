package Controller;

import Entity.PlayerList;
import Entity.square.SquareList;
import Entity.square.Street;

public class PropertyController {


//    public String[] makeMortgageArray(String playerName, SquareList squareList){
//        String[] ownedProperties = squareList.getOwnedPropertyNames(playerName);
//        for (int i = 0; i < ownedProperties.length; i++) {
//            if (squareList.searchProperty(ownedProperties[i]) instanceof Street) {
//                for (String s : squareList.getOwnedStreetNames(playerName)) {
//                    if (squareList.searchStreet(s).getColor().equals(ownedProperties[i]))
//
//                }
//
//
//
//
//
//            }
//        }
//
//
//
//
//        String[] canMortgageArray = squareList.canMortgageArray(playerName);
//        String[] mortgageMenu = new String[canMortgageArray.length +1];
//        mortgageMenu[0] = "Go back";
//        for (int i = 1; i < mortgageMenu.length; i++) {
//            mortgageMenu[i] = canMortgageArray[i-1];
//        }
//        return mortgageMenu;
//    }

    public String[] unMortgageArray(String playerName,SquareList squareList){
        String[] removableMortgage = squareList.mortgagedProperties(playerName);
        String[] mortgageMenu = new String[removableMortgage.length+1];
        mortgageMenu[0] = "Go back";
        for (int i = 1; i < mortgageMenu.length; i++){
            mortgageMenu[i] = removableMortgage[i-1];
        }
        return mortgageMenu;
    }



    public void manageMenu(GUIController guiController, PlayerList playerList, SquareList squareList) {

            String option2 = guiController.scrollList("Choose option", "Go back", "Mortgage", "Unmortgage", "Buy house", "Sell house");

            switch (option2){
                case "Go back":
                    break;
                case "Mortgage":
                    String mortgageOption = guiController.scrollList("Choose property", makeMortgageArray(playerList.getPlayer().getName(),squareList));
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Unmortgage":
                    String unMortgageOption = guiController.scrollList("Choose property", unMortgageArray(playerList.getPlayer().getName(),squareList));
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Buy house":
                    String[] build = squareList.getbuildableStreets(playerList.getPlayer().getName());
                    String[] buildMenu = new String[build.length+1];
                    buildMenu[0] = "Go back";
                    for (int i = 1; i < buildMenu.length; i++) {
                        buildMenu[i] = build[i-1];
                    }
                    String buildHouseOption = guiController.scrollList("Choose property", buildMenu);
                    manageMenu(guiController,playerList,squareList);
                    break;
                case "Sell house":

                    break;
            }
        }


}

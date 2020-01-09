package Controller;

import Entity.SquareList;

public class PropertyController {


    public String[] makeMortgageArray(String playerName, SquareList squareList){
        String[] onwershipArray = squareList.owenrShipArray(playerName);
        String[] mortgageMenu = new String[onwershipArray.length +1];
        mortgageMenu[0] = "Go Back";
        for (int i = 1; i < mortgageMenu.length; i++) {
            mortgageMenu[i] = onwershipArray[i-1];
        }
        return mortgageMenu;
    }


}

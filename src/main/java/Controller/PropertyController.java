package Controller;

import Entity.PlayerList;
import Entity.SquareList;

public class PropertyController {


    public String[] makeMortgageArray(String playerName, SquareList squareList){
        String[] canMortgageArray = squareList.canMortgageArray(playerName);
        String[] mortgageMenu = new String[canMortgageArray.length +1];
        mortgageMenu[0] = "Go back";
        for (int i = 1; i < mortgageMenu.length; i++) {
            mortgageMenu[i] = canMortgageArray[i-1];
        }
        return mortgageMenu;
    }

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
        String option = guiController.button("Choose", "Manage properties", "Roll dice");
        if (option.equals("Manage properties")) {
            String option2 = guiController.scrollList("Choose option", "Go back", "Mortgage", "Unmortgage", "Buy house", "Sell house");

            switch (option2){
                case "Go back":
                    manageMenu(guiController,playerList,squareList);
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

                    break;
                case "Sell house":

                    break;
            }
        }

    }


}

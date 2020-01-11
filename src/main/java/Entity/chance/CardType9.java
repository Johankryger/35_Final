package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;

public class CardType9 extends ChanceCard {
    public CardType9(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        String[] ownedStreets = squareList.getOwnedStreetNames(playerList.getPlayer().getName());
        int totalPropertyValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (!squareList.searchStreet(ownedStreets[i]).getMortgaged()) {
                totalPropertyValue += squareList.searchStreet(ownedStreets[i]).getPrice();
            } else {
                totalPropertyValue += squareList.searchStreet(ownedStreets[i]).getPrice() / 2;
            }
        }

        int totalBuildingValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (squareList.searchStreet(ownedStreets[i]).isPaired()) {
                int numberOfHouses= squareList.searchStreet(ownedStreets[i]).getNumberOfHouses();
                int housePrice = squareList.searchStreet(ownedStreets[i]).getHousePrice();
                totalBuildingValue += numberOfHouses * housePrice;
            }
        }

        int cash = playerList.getPlayer().getBalance().getAmount();

        int totalWealth = cash + totalPropertyValue + totalBuildingValue;

        if (totalWealth <= 15000) {
            playerList.getPlayer().getBalance().add(40000);
            guiController.button("It's your lucky day! 40.000 kr. was added to your balance", "ok");
        } else {
            guiController.button("Your total value is over 15.000 kr. Too bad.");
        }
    }
}

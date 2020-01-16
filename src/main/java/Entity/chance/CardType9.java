package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;
import message.Message;

public class CardType9 extends ChanceCard {
    //Matadorlegat Card
    public CardType9(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);

        String[] ownedStreets = squareController.getOwnedStreetNames(playerList.getPlayer().getName());
        String[] ownedProperties = squareController.getOwnedPropertyNames(playerList.getPlayer().getName());

        int totalPropertyValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (!squareController.searchProperty(ownedProperties[i]).getMortgaged()) {
                totalPropertyValue += squareController.searchProperty(ownedProperties[i]).getPrice();
            } else {
                totalPropertyValue += squareController.searchProperty(ownedProperties[i]).getPrice() / 2;
            }
        }

        int totalBuildingValue = 0;
        for (int i = 0; i < ownedStreets.length; i++) {
            if (squareController.searchStreet(ownedStreets[i]).isPaired()) {
                int numberOfHouses= squareController.searchStreet(ownedStreets[i]).getNumberOfHouses();
                int housePrice = squareController.searchStreet(ownedStreets[i]).getHousePrice();
                totalBuildingValue += numberOfHouses * housePrice;
            }
        }

        int cash = playerList.getPlayer().getBalance().getAmount();

        int totalWealth = cash + totalPropertyValue + totalBuildingValue;

        if (totalWealth <= 15000) {
            playerList.getPlayer().getBalance().add(40000);
            guiController.button(Message.getMessage("Chancecard", 38), Message.getMessage("General", 7));
        } else {
            guiController.button("Your total value is over 15.000 kr. Too bad.");
        }
    }
}

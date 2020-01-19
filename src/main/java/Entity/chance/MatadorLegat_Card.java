package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;
import message.Message;

/**
 * Gives a matadorlegat card, which gives 40000kr to the player, if he his money <15000kr.
 */
public class MatadorLegat_Card extends ChanceCard {

    public MatadorLegat_Card(String msg) {
        super(msg);
    }

    /**
     * Finds if player have less than 15000kr in totalwealth, if true then he will receive 40000kr.
     * @param playerList - to find player
     * @param squareController - to find field
     * @param guiController - to show the action on board
     * @param propertyController - to pay if necessary
     */
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
            guiController.button(Message.getMessage("Chancecard", 39));
        }
    }
}

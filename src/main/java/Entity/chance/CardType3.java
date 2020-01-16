package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;
// Ship card. metode til at rykke brikken til det nærmeste rederi.
// Multiplier bestemmer om der skal betales dobbelt leje hvis der er ejet.
public class CardType3 extends ChanceCard {
    private int multiplier;
    public CardType3(String msg, int multiplier) {
        super(msg);
        this.multiplier = multiplier;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        int beforePos = playerList.getPlayer().getFieldPos();
        switch (beforePos) {
            case 2:
                playerList.getPlayer().move(3, true);
                break;
            case 36:
                playerList.getPlayer().move(9, true);
                break;
            case 7:
                playerList.getPlayer().move(8, true);
                break;
            case 17:
                playerList.getPlayer().move(8, true);
                break;
            case 22:
                playerList.getPlayer().move(3, true);
                break;
            case 33:
                playerList.getPlayer().move(2, true);
                break;
        }
        int afterPos = playerList.getPlayer().getFieldPos();
        guiController.movePlayerFast(playerList.getPlayer().getName(), beforePos, afterPos);
        String ownedBy = squareController.getShip(afterPos).getOwner();

        if (ownedBy != "bank" && ownedBy != playerList.getPlayer().getName()) {
            guiController.button("You pay " + ownedBy + " " + squareController.getShip(afterPos).getRent() * multiplier + " kr.", "Ok :(");
            propertyController.payment(playerList, playerList.getPlayer().getName(), ownedBy, squareController, guiController, squareController.getShip(afterPos).getRent() * multiplier);
        } else {
            squareController.getSquare(afterPos).squareAction(playerList, guiController, propertyController, squareController, 1337);
        }
    }
}

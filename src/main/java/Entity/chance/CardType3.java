package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;
// metode til at rykke brikken til det nærmeste rederi.
// Multiplier bestemmer om der skal betales dobbelt leje hvis der er ejet.
public class CardType3 extends ChanceCard {
    private int multiplier;
    public CardType3(String msg, int multiplier) {
        super(msg);
        this.multiplier = multiplier;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
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
        guiController.movePlayer(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount(), beforePos, afterPos);
        String ownedBy = squareList.getShip(afterPos).getOwner();

        if (ownedBy != "bank" && ownedBy != playerList.getPlayer().getName()) {
            playerList.transfer(squareList.getShip(afterPos).getRent() * multiplier, playerList.getPlayer().getName(), ownedBy);
            guiController.button("You pay " + ownedBy + " " + squareList.getShip(afterPos).getRent() * multiplier + " kr.", "Ok :(");
        } else {
            squareList.getSquare(afterPos).squareAction(playerList, guiController, 1337);
        }
    }
}

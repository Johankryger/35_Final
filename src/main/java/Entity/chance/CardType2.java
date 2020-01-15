package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareController;
// metode for at rykke til et bestemt felt og modtage penge hvis start er krydset
public class CardType2 extends ChanceCard {
    private int position;

    public CardType2(String msg, int position) {
        super(msg);
        this.position = position;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareList, GUIController guiController) {
        guiController.showChanceCard(msg);
        int beforePos = playerList.getPlayer().getFieldPos();
        int steps = 0;
        if (position > beforePos) {
            steps = position - beforePos;
        } else if (beforePos > position) {
            steps= 40 - beforePos + position;
        }
        playerList.getPlayer().move(steps,true);
        guiController.movePlayerFast(playerList.getPlayer().getName(), beforePos, position);
        squareList.getSquare(position).squareAction(playerList, guiController, 1337);
    }
}

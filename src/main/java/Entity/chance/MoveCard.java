package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;
// Move Card. Moves you to a specefic square.
public class MoveCard extends ChanceCard {
    private int position;

    public MoveCard(String msg, int position) {
        super(msg);
        this.position = position;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
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
        squareController.getSquare(position).squareAction(playerList, guiController, propertyController, squareController, 1337);
    }
}

package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

/**
 * Moves player 3 steps forward
 */
public class MoveForwards_Card extends ChanceCard {

    public MoveForwards_Card(String msg){
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController){
        int position = playerList.getPlayer().getFieldPos();
        int moveto = (position + 3) % 40;
        playerList.getPlayer().move(3,true);
        guiController.movePlayer(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount(),position,moveto);
        squareController.getSquare(playerList.getPlayer().getFieldPos()).squareAction(playerList,guiController,propertyController, squareController, 3);
    }


}

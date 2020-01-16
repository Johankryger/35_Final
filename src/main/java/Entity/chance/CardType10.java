package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

public class CardType10 extends ChanceCard {
    //Move 3 Forward Card
    public CardType10(String msg){
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

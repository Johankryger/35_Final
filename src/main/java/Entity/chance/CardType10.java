package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;

public class CardType10 extends ChanceCard {

    public CardType10(String msg){
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController){
        int position = playerList.getPlayer().getFieldPos();
        int moveto = (position + 3) % 40;
        playerList.getPlayer().move(3,true);
        guiController.movePlayer(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount(),position,moveto);
        squareList.getSquare(playerList.getPlayer().getFieldPos()).squareAction(playerList,guiController,3);
    }


}

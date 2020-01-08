package Controller;

import Entity.DiceCup;
import Entity.PlayerList;
import Entity.SquareList;

public class Controller {
    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareList squareList = new SquareList();

    public void start() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);


        while (true) {
           // Throwing dice process
            guiController.button(playerList.getPlayer().getName() + "'s tur.", "Kast terning");
            diceCup.rollDice();
            int[] values = diceCup.getFaceValueArray();
            guiController.showDice(values);

            // Movement process
            int startPos = playerList.getPlayer().getFieldPos();
            playerList.getPlayer().move(diceCup.getFaceValueSum(), true);
            guiController.movePlayer(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount(), startPos, playerList.getPlayer().getFieldPos());

            // Land on and squarelist
            squareList.getSquare(playerList.getPlayer().getFieldPos()).squareAction(playerList,guiController,diceCup.getFaceValueSum());

//            if(playerList.getPlayer().hasGotChanceCard()){
//                chancelist.drawCard();
            //}
            playerList.nextPlayer();
        }


    }
}

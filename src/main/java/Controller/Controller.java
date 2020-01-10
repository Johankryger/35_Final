package Controller;

import Entity.DiceCup;
import Entity.PlayerList;
import Entity.SquareList;

public class Controller {
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareList squareList = new SquareList();
    private GameLogic gameLogic = new GameLogic();
    private PropertyController propertyController = new PropertyController();

    public void start() {
        gameLogic.turn();
        //Returns a string array of names
//        String[] names = guiController.startMenu();
//        playerList.addPlayers(names, names.length);
//
//
//        while (true) {
//           // Throwing dice process
//            guiController.button(playerList.getPlayer().getName() + "'s tur.", "Kast terning");
//            diceCup.rollDice();
//            guiController.showDice(diceCup.getFaceValueArray());
//
//            // Movement process
//            int startPos = playerList.getPlayer().getFieldPos();
//            playerList.getPlayer().move(diceCup.getFaceValueSum(), true);
//            guiController.movePlayer(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount(), startPos, playerList.getPlayer().getFieldPos());
//
//            // Land on and squarelist
//            squareList.getSquare(playerList.getPlayer().getFieldPos()).squareAction(playerList,guiController,diceCup.getFaceValueSum());
//
////            if(playerList.getPlayer().hasGotChanceCard()){
////                chancelist.drawCard();
//            //}
//            playerList.nextPlayer();
//        }
//
//
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);


        while (true) {
            propertyController.manageMenu(guiController, playerList, squareList);
           // Throwing dice process
            guiController.button(playerList.getPlayer().getName() + "'s tur.", "Kast terning");
            diceCup.rollDice();
            guiController.showDice(diceCup.getFaceValueArray());

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

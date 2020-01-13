package Controller;

import message.Message;

public class Controller {
    private Message message = new Message("Dansk");
    private GameController gameLogic = new GameController();

    public void start() {
        gameLogic.turn();
        while(true) {
            gameLogic.checkForLoser();
            gameLogic.startTurn();
            String option = "manage bygninger";
            while (option.equals("manage bygninger")) {
                gameLogic.updateProperties();
                option = gameLogic.menu();
            }
            gameLogic.logicRollDice();
            gameLogic.getPair();
            gameLogic.searchForJail();
            if (gameLogic.getPlayer().isInJail()){
                gameLogic.nextPlayer();
            }
            else{
                gameLogic.movePlayer();
                gameLogic.landOn();
                if (!gameLogic.getPlayer().hasExtraTurn()){
                    gameLogic.nextPlayer();
                }
            }
        }
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
//        String[] names = guiController.startMenu();
//        playerList.addPlayers(names, names.length);
//
//
//        while (true) {
//            propertyController.manageMenu(guiController, playerList, squareList);
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
    }
}

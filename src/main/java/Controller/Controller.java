package Controller;

public class Controller {
    private GameLogic gameLogic = new GameLogic();


    public void start() {
        gameLogic.turn();
        while(true) {
            String option = "manage bygninger";
            while (option.equals("manage bygninger")) {
                gameLogic.updateProperties();
                option = gameLogic.menu();
            }
            gameLogic.jailLogic(option);
            gameLogic.rollDiceLogic();
            if (gameLogic.getPlayer().isInJail()){
                gameLogic.nextPlayer();
            }
            else{
                gameLogic.movePlayer();
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

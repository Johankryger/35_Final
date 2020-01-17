package Controller;

import message.Message;

public class Controller {
    private Message message = new Message("English");
    private GameController gameController = new GameController();

    public void start() {
        //Creates Start Menu
        gameController.turn();
        while(true) {
//            gameController.checkForLoser();
            if (gameController.checkForEndGame()){break;}
            String option = Message.getMessage("In Jail",2);
            while (option.equals(Message.getMessage("In Jail",2))) {
                gameController.updateProperties();
                option = gameController.menu();
            }
            gameController.rollDiceLogic();
            gameController.jailLogic(option);
//            if (gameController.getPlayer().isAboutToLose()){
//                gameController.checkForLoser();
//            }
            if (gameController.getPlayer().isInJail()){
                gameController.nextPlayer();
            }
            else{
                gameController.movePlayer();
                if (!gameController.getPlayer().hasExtraTurn()){
                    gameController.nextPlayer();
                }
            }
        }
        gameController.endGame();
    }
}

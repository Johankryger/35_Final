package Controller;

import message.Message;

public class Controller {
    private Message message = new Message("Dansk");
    private GameController gameController = new GameController();

    public void start() {
        gameController.turn();
        while(true) {
            gameController.checkForLoser();
            String option = "manage bygninger";
            while (option.equals("manage bygninger")) {
                gameController.updateProperties();
                option = gameController.menu();
            }
            gameController.rollDiceLogic();
            gameController.jailLogic(option);
            if (gameController.getPlayer().isHasLost()){
                gameController.checkForLoser();
            }
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
    }
}

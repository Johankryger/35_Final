package Controller;

import message.Message;

public class Controller {
    private Message message = new Message("English");
    private GameController gameController = new GameController();

    public void start() {
        //Creates Start Menu
        gameController.startUPMenu();
        while (!gameController.checkForEndGame()) {
            gameController.menu();
            gameController.rollDiceLogic();
            if (gameController.getPlayer().isInJail()) {
                gameController.nextPlayer();
            } else {
                //movePlayer moves player, uses square action and possibly chance card action
                gameController.movePlayer();
                if (!gameController.getPlayer().hasExtraTurn()) {
                    gameController.nextPlayer();
                }
            }
        }
        gameController.endGame();
    }
}

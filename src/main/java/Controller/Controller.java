package Controller;

import Entity.DiceCup;
import Entity.PlayerList;

public class Controller {
    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();

    public void start() {
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);

        while (true) {
            diceCup.rollDice();
            int startPos = playerList.getPlayer().getFieldPos();
            playerList.getPlayer().move(diceCup.getFaceValueSum(), true);
            guiController.movePlayer(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount(), startPos, playerList.getPlayer().getFieldPos());

        }
    }
}

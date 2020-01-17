package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

public class CardType6 extends ChanceCard {
    //Recieve money from everyone Card.
    private int amount;
    public CardType6(String msg, int amount) {
        super(msg);
        this.amount = amount;
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        String[] names = playerList.getPlayerNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i] != playerList.getPlayer().getName()) {
                propertyController.payment(playerList, names[i], playerList.getPlayer().getName(), squareController, guiController, amount);
                guiController.updateBalance(playerList.searchPlayer(names[i]).getName(),playerList.searchPlayer(names[i]).getBalance().getAmount());

            }
        }
        guiController.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
    }
}

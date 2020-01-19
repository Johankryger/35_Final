package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;

/**
 * Cardtype that gives a certain amount of cash to a person's balance, dependant on the message on the card. This cardtype can give positive and negative cash (take money from balance) to the balance.
 */
public class Money_Card extends ChanceCard {
    private int amountAdded;

    /**
     * The constructor also needs to know amount, as in how much will be added og subtracted from the player's balance
     * @param msg
     * @param amountAdded to see how much will be added to the player's balance
     */
    public Money_Card(String msg, int amountAdded) {
        super(msg);
        this.amountAdded = amountAdded;
    }

    /**
     * Pays or adds money with/to a player's balance, dependant on amountAdded
     * @param playerList - to find player
     * @param squareController - to find field
     * @param guiController - to show the action on board
     * @param propertyController - to pay if necessary
     */
    @Override
    public void chanceAction(PlayerList playerList, SquareController squareController, GUIController guiController, PropertyController propertyController) {
        guiController.showChanceCard(msg);
        if (amountAdded < 0) {
            propertyController.payment(playerList, playerList.getPlayer().getName(), null, squareController, guiController, amountAdded * (-1));
        }

        playerList.getPlayer().getBalance().add(amountAdded);
        guiController.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
    }
}

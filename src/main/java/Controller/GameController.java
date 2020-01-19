package Controller;

import Entity.*;
import message.Message;


public class GameController {
    private final int JAIL_BAIL_PRICE = 1000;

    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareController squareController = new SquareController();
    private PropertyController propertyController = new PropertyController();
    private ChanceCardController chanceCardController;

    public void startUPMenu() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);
        chanceCardController = new ChanceCardController(); // chanceList object is made after startMenu() because startMenu() sets the language
    }

    public Player getPlayer() {
        return playerList.getPlayer();
    }

    public void menu() {
        // while player has choosen manage properties
        String msg = Message.getMessage("In Jail", 2);
        while (msg.equals(Message.getMessage("In Jail", 2))) {
            squareController.checkPairs(); // checks for pairs of all properties

            if (playerList.getPlayer().isInJail() && playerList.getPlayer().hasGotFreeJailCard()) {
                // options: manage menu, pay 1000kr, use jailfree card or try roll out of jail
                msg = guiController.button(Message.getMessage("In Jail", 1) + Message.getMessage("In Jail", 10), Message.getMessage("In Jail", 2), Message.getMessage("In Jail", 3), Message.getMessage("In Jail", 4), Message.getMessage("In Jail", 5));
            } else if (playerList.getPlayer().isInJail()) {
                // options: manage menu, pay 1000kr or try roll out of jail
                msg = guiController.button(Message.getMessage("In Jail", 1) + Message.getMessage("In Jail", 10), Message.getMessage("In Jail", 2), Message.getMessage("In Jail", 3), Message.getMessage("In Jail", 5));
            } else {
                // options: manage menu or roll dice
                msg = guiController.button(Message.getMessage("In Jail", 6) + " " + playerList.getPlayer().getName() + Message.getMessage("In Jail", 7), Message.getMessage("In Jail", 2), Message.getMessage("In Jail", 8));
            }
            // manage menu has been chosen
            if (msg.equals(Message.getMessage("In Jail", 2))) {
                propertyController.manageMenu(guiController, playerList, squareController);
            }
        }

        // these if statements are used only when player pays out of jail or use jail free card
        Player player = playerList.getPlayer();
        if (msg.equals(Message.getMessage("In Jail",3))) {
            propertyController.payment(playerList, player.getName(), null, squareController, guiController, JAIL_BAIL_PRICE);
            player.setInJail(false);
            player.resetTurnsInJail();
            guiController.updateBalance(player.getName(), player.getBalance().getAmount());
            player.setInJail(false);
        }

        if (msg.equals(Message.getMessage("In Jail",4))) {
            player.useBailCard();
            player.setInJail(false);
        }

    }



    public void movePlayer() {
        // Movement process
        Player player = playerList.getPlayer();
        int startPos = player.getFieldPos();
        player.move(diceCup.getFaceValueSum(), true);
        guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, player.getFieldPos());
        // Land on
        propertyController.calculateLiquidation(playerList, squareController);
        squareController.getSquare(player.getFieldPos()).squareAction(playerList, guiController, propertyController, squareController, diceCup.getFaceValueSum());

        while (player.hasGotChanceCard()) {
            chanceCardController.pickCard(playerList, squareController, guiController, propertyController);
        }
    }


    // Throwing dice process
    public void rollDiceLogic() {
        Player player = playerList.getPlayer();

        // roll dice
        diceCup.rollDice();
        int[] dicearr = diceCup.getFaceValueArray();
        guiController.showDice(dicearr);

        // checks for pairs
        if (dicearr[0] == dicearr[1]) {
            player.setInJail(false); // in case you are in jail, you will get out if you get a pair
            player.incrementPairCounter();
            player.extraTurn(true);
        } else {
            player.extraTurn(false);
            player.resetPairCounter();
        }

        //sets player in jail if rolled pair 3 times in a row.
        if (player.getPairCounter() == 3) {
            guiController.button(Message.getMessage("In Jail",9),Message.getMessage("General",7));
            player.setInJail(true);
            int startPos = player.getFieldPos();
            guiController.movePlayerFast(player.getName(), startPos, 10);
            player.setFieldPos(10);
            player.resetPairCounter();
        } else {
            if (player.isInJail() && !player.hasExtraTurn()) {
                if (player.getTurnsInJail() == 2) {
                    propertyController.payment(playerList, player.getName(), null, squareController, guiController, JAIL_BAIL_PRICE);
                    player.setInJail(false);
                    player.resetTurnsInJail();
                    guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                }
                player.addTurnInJail();
            }
        }
    }

    public void nextPlayer() {
        playerList.nextPlayer();
    }

    public boolean checkForEndGame(){
        return (playerList.getAllPlayers().length==1);
    }

    public void endGame(){
        guiController.button(getPlayer().getName()+" "+ Message.getMessage("General",11),Message.getMessage("General",7));
        guiController.close();
    }



}
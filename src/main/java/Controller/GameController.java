package Controller;

import Entity.*;
import Entity.chance.ChanceList;
import Entity.square.SquareList;
import message.Message;

public class GameController {
    private final int JAIL_BAIL_PRICE = 1000;
    private int scoreBoard = 1;

    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareList squareList = new SquareList();
    private PropertyController propertyController = new PropertyController();
    private ChanceList chanceList;
    private GameLogic gameLogic = new GameLogic();
    private LiquidateLogic liquidateLogic = new LiquidateLogic();

    public void turn() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);
        chanceList = new ChanceList(); // chanceList object is made after startMenu() because startMenu() sets the language
    }

    public Player getPlayer() {
        Player player = playerList.getPlayer();
        return player;
    }

    public String menu() {
        String msg = "";
        if (playerList.getPlayer().isInJail() && playerList.getPlayer().hasGotFreeJailCard()) {
            msg = guiController.button(Message.getMessage("In Jail", 1),Message.getMessage("In Jail", 2), Message.getMessage("In Jail",3), Message.getMessage("In Jail", 4), Message.getMessage("In Jail",5));
        } else if (playerList.getPlayer().isInJail()) {
            msg = guiController.button(Message.getMessage("In Jail",1), Message.getMessage("In Jail", 2), Message.getMessage("In Jail",3), Message.getMessage("In Jail",5));
        } else {
            msg = guiController.button(Message.getMessage("In Jail",6) + " " +  playerList.getPlayer().getName() + Message.getMessage("In Jail",7), Message.getMessage("In Jail",2), Message.getMessage("In Jail",8));
        }
        if (msg.equals(Message.getMessage("In Jail",2))) {
            propertyController.manageMenu(guiController, playerList, squareList);
        }
        return msg;
    }

    public void checkForLost(int amountToPay) {
        String[] playerNames = playerList.getPlayerNames();
        for (String playerName : playerNames) {
            if (playerList.searchPlayer(playerName).isNeedsToLiquidate()) {
                propertyController.liquidateMenu(playerList, playerName, squareList, guiController, amountToPay);
            } else {
                playerList.searchPlayer(playerName);//
            }
        }
    }


    public void jailLogic(String option) {
        Player player = playerList.getPlayer();
        String jailMsg = option;
        if (player.isInJail() && player.hasGotFreeJailCard()) {

            if (option.equals(Message.getMessage("In Jail",3))) {
                player.payJailBail(JAIL_BAIL_PRICE);
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                player.setInJail(false);
            }

            if (option.equals(Message.getMessage("In Jail",4))) {
                player.useBailCard();
                player.setInJail(false);
            }

        } else if (player.isInJail()) {
            if (option.equals(Message.getMessage("In Jail",3))) {
                player.payJailBail(JAIL_BAIL_PRICE);
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                player.setInJail(false);
            }
        }


        gameLogic.calculateLiquidation(playerList, squareList);
        //Sets extraTurn to true/false depending on getPair method
    }

    public void movePlayer() {
        // Movement process
        Player player = playerList.getPlayer();
        int startPos = player.getFieldPos();
        player.move(diceCup.getFaceValueSum(), true);
        guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, player.getFieldPos());

        gameLogic.calculateLiquidation(playerList, squareList);
        // Land on
        boolean needsToLiquidate;
        do {
            needsToLiquidate = squareList.getSquare(player.getFieldPos()).squareAction(playerList, guiController, diceCup.getFaceValueSum());
            if (needsToLiquidate)
                propertyController.liquidateMenu(playerList,getPlayer().getName(),squareList,guiController,getPlayer().getMoneyToPay());
        } while (needsToLiquidate);

        if (player.hasGotChanceCard()) {
            chanceList.pickCard(playerList, squareList, guiController);
            player.setGotChanceCard(false);
        }

    }

    public void updateProperties() {
        squareList.checkPairs();
    }

    // Throwing dice process
    public void rollDiceLogic() {
        Player player = playerList.getPlayer();
        gameLogic.calculateLiquidation(playerList, squareList);

        // roll dice
        diceCup.rollDice();
        int[] dicearr = diceCup.getFaceValueArray();
        guiController.showDice(dicearr);
//        int[] dicearr = {1,1};
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
            player.setInJail(true);
            int startPos = player.getFieldPos();
            guiController.movePlayerFast(player.getName(), startPos, 10);
            player.setFieldPos(10);
            player.resetPairCounter();
        } else {
            if (player.isInJail() && !player.hasExtraTurn()) {
                if (player.getTurnsInJail() == 2) {
                    if (player.getBalance().getAmount() < JAIL_BAIL_PRICE) {
                        player.setHasLost(true);
                    }
                    player.payJailBail(JAIL_BAIL_PRICE);
                    guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                }
                player.addTurnInJail();
            }
        }
    }

    public void nextPlayer() {
        playerList.nextPlayer();
    }

    public void checkForLoser() {
        //counters with losername and without losername used in remainingPlayer array
        int remainingPlayercounter = 0;
        int allPlayerCounter = 0;
        Player[] playerArray = playerList.getAllPlayers();
        gameLogic.calculateLiquidation(playerList, squareList);
        //checks for all players if they have lost this turn
        for (Player playerName : playerArray) {
            if (!playerName.isHasLost() && (playerName.getBalance().getAmount()<0 || playerName.isAboutToLose())) {
                guiController.removeLoser(playerName.getName(), playerName.getFieldPos(), squareList);
                playerList.getPlayer().setFinalScore(scoreBoard--);
                playerList.getPlayer().setHasLost(true);
                playerList.getPlayer().setInJail(false);
                playerList.getPlayer().extraTurn(false);

                //sets new owner on loser's properties
                String owner;
                int pos = playerName.getFieldPos();
                if (squareList.inPropertyPosition(pos)) {
                    owner = squareList.searchProperty(squareList.getSquare(pos).getFieldName()).getOwner();
                } else owner = "bank";

                if (owner.equals(playerName.getName()) || owner.equals("bank")) {
                    squareList.giveLoserProperty(playerName.getName(), "bank");
                } else {
                    squareList.giveLoserProperty(playerName.getName(), owner);
                }

                //makes new array of remaining players
                int[] playerPosition = new int[playerArray.length - 1];
                String[] remainingPlayers = new String[playerList.getAllPlayers().length - 1];
                for (Player player : playerArray) {
                    if (!playerName.getName().equals(player.getName())) {
                        playerPosition[remainingPlayercounter] = player.getFieldPos();
                        remainingPlayers[remainingPlayercounter] = playerArray[allPlayerCounter].getName();
                        remainingPlayercounter++;
                    }
                    allPlayerCounter++;
                }
                //returns array of remaining players to playerlist and sets index to 1 less
                playerList.addPlayers(remainingPlayers, remainingPlayers.length);
                for (int j = 0; j < remainingPlayers.length; j++) {
                    playerList.searchPlayer(remainingPlayers[j]).setFieldPos(playerPosition[j]);
                }
                if (!(playerList.getIndex() == 0))
                    playerList.setIndex((playerList.getIndex() - 1) % remainingPlayers.length);
                else playerList.setIndex(playerList.getIndex());
            }
        }
    }
}
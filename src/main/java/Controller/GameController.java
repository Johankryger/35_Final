package Controller;

import Entity.*;
import Entity.chance.ChanceList;
import Entity.square.SquareList;

public class GameController {
    private final int JAIL_BAIL_PRICE = 1000;
    private int scoreBoard=1;

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

    public Player getPlayer(){
        Player player = playerList.getPlayer();
        return player;
    }

    public String menu() {
        String msg = "";
        if (playerList.getPlayer().isInJail() && playerList.getPlayer().hasGotFreeJailCard()) {
            msg = guiController.button("Hvad vil du foretage dig?","manage bygninger", "Betal 1000kr", "Brug et løsladelseskort", "Prøv lykken og rul!");
        } else if (playerList.getPlayer().isInJail()) {
            msg = guiController.button("Du er blevet fængslet! Hvad vil du foretage dig?", "manage bygninger", "Betal 1000kr", "Prøv lykken og rul!");
        } else {
            msg = guiController.button("det er " + playerList.getPlayer().getName() + "s tur", "manage bygninger", "kast terninger");
        }
        if (msg.equals("manage bygninger")) {
            propertyController.manageMenu(guiController, playerList, squareList);
        }
        return msg;
    }

    public void checkForLost(int amountToPay) {
        String[] playerNames = playerList.getPlayerNames();
        for (int i = 0; i < playerNames.length; i++) {
            if (playerList.searchPlayer(playerNames[i]).isNeedsToLiquidate()) {
                propertyController.liquidateMenu(playerList, playerNames[i], squareList, guiController, amountToPay);
            } else if (playerList.searchPlayer(playerNames[i]).isHasLost()) {
                //
            }
        }
    }


    public void jailLogic(String option) {
        Player player = playerList.getPlayer();
        String jailMsg = option;
        if (player.isInJail() && player.hasGotFreeJailCard()) {

            if (jailMsg.equals("Betal 1000kr")) {
                player.payJailBail(JAIL_BAIL_PRICE);
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                player.setInJail(false);
            }

            if (jailMsg.equals("Brug et løsladelseskort")) {
                player.useBailCard();
                player.setInJail(false);
            }

        } else if (player.isInJail()) {
            if (jailMsg.equals("Betal 1000kr")) {
                player.payJailBail(JAIL_BAIL_PRICE);
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                player.setInJail(false);
            }
        }


        gameLogic.calculateLiquidation(playerList, squareList);
        //Sets extraTurn to true/false depending on getPair method
    }

    //            if(player.hasGotChanceCard()){
    //                chancelist.drawCard();
    //}
    //gives turn to next player if extraTurn is false

    public void movePlayer(){
            // Movement process
            Player player = playerList.getPlayer();
            int startPos = player.getFieldPos();
            player.move(diceCup.getFaceValueSum(), true);
            guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, player.getFieldPos());

            gameLogic.calculateLiquidation(playerList, squareList);
            // Land on
            squareList.getSquare(player.getFieldPos()).squareAction(playerList, guiController, diceCup.getFaceValueSum());
            if (player.hasGotChanceCard()) {
                chanceList.pickCard(playerList, squareList, guiController);
                player.setGotChanceCard(false);
            }

    }

    public void updateProperties() {
        squareList.checkPairs();
    }


//    //method for checking pair in dices
//    public boolean getPair() {
//        Player player = playerList.getPlayer();
//        int[] dicearr = diceCup.getFaceValueArray();
////        int[] dicearr = {1,1};
//        if (!(dicearr[0] == dicearr[1])) {
//            player.extraTurn(false);
//            player.resetPairCounter();
//            return false;
//        }
//        player.setInJail(false);
//        player.incrementPairCounter();
//        player.extraTurn(true);
//        return true;
//    }

    // Throwing dice process
    public void rollDiceLogic(){
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

    public void nextPlayer(){
        playerList.nextPlayer();
    }

    public void checkForLoser() {
        Player[] playerArray = playerList.getAllPlayers();
        for (int i = 0; i < playerArray.length; i++) {
            Player playerName = playerArray[i];
            if (playerName.getBalance().getAmount() < 0 && !playerName.isHasLost()) {
                guiController.removeLoser(playerName.getName(), playerName.getFieldPos());
                playerList.getPlayer().setFinalScore(scoreBoard--);
                playerList.getPlayer().setHasLost(true);
                playerList.getPlayer().setInJail(false);
                squareList.setBankOwner(playerName.getName());
            }
        }
    }
}
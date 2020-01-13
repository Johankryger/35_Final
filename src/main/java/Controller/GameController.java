package Controller;

import Entity.DiceCup;
import Entity.Player;
import Entity.PlayerList;
import Entity.chance.ChanceList;
import Entity.SquareList;

public class GameController {
    private final int JAIL_BAIL_PRICE = 1000;
    private int scoreBoard=1;

    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareList squareList = new SquareList();
    private PropertyController propertyController = new PropertyController();
    private ChanceList chanceList;

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

            // Land on and squarelist
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
        //counters for with losername and without losername used in remainingPlayer array
        int remainingPlayercounter = 0;
        int allPlayerCounter = 0;
        Player[] playerArray = playerList.getAllPlayers();
        //checks for all players if they have lost this turn
        for (int i = 0; i < playerArray.length; i++) {
            Player playerName = playerArray[i];
            if (playerName.getBalance().getAmount() < 0) {
                guiController.removeLoser(playerName.getName(), playerName.getFieldPos());
                playerList.getPlayer().setFinalScore(scoreBoard--);
                playerList.getPlayer().setHasLost(true);
                playerList.getPlayer().setInJail(false);
                playerList.getPlayer().extraTurn(false);

                //sets new owner on loser's properties
//                try {
//                    int pos = getPlayer().getFieldPos();
//                    String owner = squareList.searchProperty(squareList.getSquare(pos).getFieldName()).getOwner();
//                    if (owner == null || owner.equals(getPlayer().getName())|| owner.equals("bank")) {
                        squareList.giveLoserProperty(playerName.getName(), "bank");
//                    }
//                    squareList.giveLoserProperty(playerName.getName(), owner);
//                } catch (NullPointerException n) {
//                }

                int[] playerPosition = new int[playerArray.length-1];

                //makes new array of remaining players
                String[] remainingPlayers = new String[playerList.getAllPlayers().length - 1];
                for (Player player : playerArray) {
                    if (!playerArray[i].getName().equals(player.getName())) {
                        playerPosition[remainingPlayercounter]=player.getFieldPos();
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
                playerList.setIndex((playerList.getIndex()-1)%remainingPlayers.length);
            }
        }
    }
}
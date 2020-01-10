package Controller;

import Controller.GUIController;
import Entity.DiceCup;
import Entity.Player;
import Entity.PlayerList;
import Entity.SquareList;

public class GameLogic {
    private final int JAIL_BAIL_PRICE = 1000;

    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareList squareList = new SquareList();

    public void turn() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);
    }

    public Player getPlayer(){
        Player player = playerList.getPlayer();
        return player;
    }


    public void startTurn() {
        Player player = playerList.getPlayer();
        if (player.isInJail() && player.hasGotFreeJailCard()) {

            String jailMsg = guiController.button("Du er blevet fængslet! Hvad vil du foretage dig?", "Betal 1000kr", "Brug et løsladelseskort", "Prøv lykken og rul!");
            if (jailMsg.equals("Betal 1000kr")) {
                player.payJailBail(JAIL_BAIL_PRICE);
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());

            }

            if (jailMsg.equals("Brug et løsladelseskort")) {
                player.useBailCard();
            } else {
                player.setInJail(true);
            }

        } else if (player.isInJail()) {
            String jailMsg = guiController.button("Du er blevet fængslet! Hvad vil du foretage dig?", "Betal 1000kr", "Prøv lykken og rul!");
            if (jailMsg.equals("Betal 1000kr")) {
                player.payJailBail(JAIL_BAIL_PRICE);
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());

            } else {
                player.setInJail(true);
            }
        }



        //Sets extraTurn to true/false depending on getPair method
    }
        public void searchForJail() {
            Player player = playerList.getPlayer();
            //sets player in jail if rolled pair 3 times in a row.
            if (player.getPairCounter() == 3) {
                player.setInJail(true);
                int startPos = player.getFieldPos();
                guiController.movePlayerInJail(player.getName(),startPos);
                player.setFieldPos(10);
                player.resetPairCounter();
            } else {
                if (player.isInJail() && !getPair()) {
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

         public void landOn(){
             Player player = playerList.getPlayer();
             // Land on and squarelist
                squareList.getSquare(player.getFieldPos()).squareAction(playerList, guiController, diceCup.getFaceValueSum());
            }

    //            if(player.hasGotChanceCard()){
    //                chancelist.drawCard();
    //}
    //gives turn to next player if extraTurn is false

        public void movePlayer() {
            // Movement process
            Player player = playerList.getPlayer();
            int startPos = player.getFieldPos();
            player.move(diceCup.getFaceValueSum(), true);
            guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, player.getFieldPos());
        }


    //method for checking pair in dices
    public boolean getPair() {
        Player player = playerList.getPlayer();
        int[] dicearr = diceCup.getFaceValueArray();
//        int[] dicearr = {1,1};
        if (!(dicearr[0] == dicearr[1])) {
            player.extraTurn(false);
            player.resetPairCounter();
            return false;
        }
        player.setInJail(false);
        player.incrementPairCounter();
        player.extraTurn(true);
        return true;
    }

    // Throwing dice process
    public void logicRollDice(){
        Player player = playerList.getPlayer();
        guiController.button(player.getName() + "'s tur.", "Kast terning");
        diceCup.rollDice();
        guiController.showDice(diceCup.getFaceValueArray());
    }

    public void nextPlayer(){
        playerList.nextPlayer();
    }
}
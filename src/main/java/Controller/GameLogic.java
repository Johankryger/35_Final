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
    private int pairCounter=0;
    Player player = playerList.getPlayer();

    public void turn() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);


        while (true) {

            if (player.isInJail() && player.hasGotFreeJailCard()) {

                String jailMsg = guiController.button("Du er blevet fængslet! Hvad vil du foretage dig?", "Betal 1000kr", "Brug et løsladelseskort", "Prøv lykken og rul!");
                if (jailMsg.equals("Betal 1000kr")) {
                    player.payJailBail(JAIL_BAIL_PRICE);
                    guiController.updateBalance(player.getName(), player.getBalance().getAmount());

                }

                if (jailMsg.equals("Brug et løsladelseskort")) {
                  player.useBailCard();
                }
                else {
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


            // Throwing dice process
            guiController.button(player.getName() + "'s tur.", "Kast terning");
            diceCup.rollDice();
            guiController.showDice(diceCup.getFaceValueArray());

            //Sets extraTurn to true/false depending on getPair method
            player.extraTurn(getPair());
            //sets player in jail if rolled pair 3 times in a row.
            if (player.getPairCounter() == 3) {
                player.setInJail(true);
                int startPos = player.getFieldPos();
                guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, 10);
                player.setFieldPos(10);
                playerList.nextPlayer();
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
                    playerList.nextPlayer();
                }

                // Movement process
                int startPos = player.getFieldPos();
                player.move(2, true);
                guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, player.getFieldPos());

                // Land on and squarelist
                squareList.getSquare(player.getFieldPos()).squareAction(playerList, guiController, diceCup.getFaceValueSum());

//            if(player.hasGotChanceCard()){
//                chancelist.drawCard();
                //}
                //gives turn to next player if extraTurn is false
                if (!player.hasExtraTurn()) {
                    playerList.nextPlayer();
                }
            }
        }
    }

    //method for checking pair in dices
    public boolean getPair() {
//        int[] dicearr = diceCup.getFaceValueArray();
        int[] dicearr = {1,1};
        if (!(dicearr[0] == dicearr[1])) {
            player.resetPairCounter();
            return false;
        }
            player.incrementPairCounter();
            return true;
    }
}
package Controller;

import Controller.GUIController;
import Entity.DiceCup;
import Entity.PlayerList;
import Entity.SquareList;

public class GameLogic {
    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareList squareList = new SquareList();
    private int pairCounter=0;

    public void turn() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);


        while (true) {
            if (playerList.getPlayer().isInJail() && playerList.getPlayer().isGotFreeJailCard()) {

                String jailMsg = guiController.button("Du er blevet fængslet! Hvad vil du foretage dig?", "Betal 1000kr", "Brug et løsladelseskort", "Prøv lykken og rul!");
                if (jailMsg.equals("Betal 1000kr")) {
                    playerList.getPlayer().getBalance().pay(1000);
                    playerList.getPlayer().setInJail(false);
                    guiController.updateBalance(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount());
                }

                if (jailMsg.equals("Brug et løsladelseskort")) {
                    playerList.getPlayer().setGotFreeJailCard(false);
                    playerList.getPlayer().setInJail(false);
                }
                playerList.getPlayer().setInJail(false);
            }

            else if (playerList.getPlayer().isInJail()){
                String jailMsg = guiController.button("Du er blevet fængslet! Hvad vil du foretage dig?", "Betal 1000kr", "Prøv lykken og rul!");
                if (jailMsg.equals("Betal 1000kr")) {
                    playerList.getPlayer().getBalance().pay(1000);
                    playerList.getPlayer().setInJail(false);
                    guiController.updateBalance(playerList.getPlayer().getName(),playerList.getPlayer().getBalance().getAmount());
                }
                playerList.getPlayer().setInJail(false);
            }

            // Throwing dice process
            guiController.button(playerList.getPlayer().getName() + "'s tur.", "Kast terning");
            diceCup.rollDice();
            guiController.showDice(diceCup.getFaceValueArray());

            //Sets extraTurn to true/false depending on getPair method
            playerList.getPlayer().extraTurn(getPair());
            //sets player in jail if rolled pair 3 times in a row.
            if (pairCounter==3){
                playerList.getPlayer().setInJail(true);
                int startPos = playerList.getPlayer().getFieldPos();
                guiController.movePlayer(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount(), startPos,10);
                playerList.getPlayer().setFieldPos(10);
                playerList.nextPlayer();
                pairCounter=0;
            }

            else {
                if (playerList.getPlayer().isInJail() && !getPair()){
                    playerList.nextPlayer();
                }

                // Movement process
                int startPos = playerList.getPlayer().getFieldPos();
                playerList.getPlayer().move(2, true);
                guiController.movePlayer(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount(), startPos, playerList.getPlayer().getFieldPos());

                // Land on and squarelist
                squareList.getSquare(playerList.getPlayer().getFieldPos()).squareAction(playerList, guiController, diceCup.getFaceValueSum());

//            if(playerList.getPlayer().hasGotChanceCard()){
//                chancelist.drawCard();
                //}
                //gives turn to next player if extraTurn is false
                if (!playerList.getPlayer().isExtraTurn()) {
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
            pairCounter = 0;
            return false;
        }
            pairCounter++;
            return true;
    }
}
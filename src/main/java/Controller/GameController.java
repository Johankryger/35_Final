package Controller;

import Entity.*;
import Entity.chance.ChanceCardController;
import Entity.square.SquareController;
import message.Message;

public class GameController {
    private final int JAIL_BAIL_PRICE = 1000;

    private GUIController guiController = new GUIController();
    private DiceCup diceCup = new DiceCup();
    private PlayerList playerList = new PlayerList();
    private SquareController squareController = new SquareController();
    private PropertyController propertyController = new PropertyController();
    private ChanceCardController chanceCardController;

    public void turn() {
        //Returns a string array of names
        String[] names = guiController.startMenu();
        playerList.addPlayers(names, names.length);
        chanceCardController = new ChanceCardController(); // chanceList object is made after startMenu() because startMenu() sets the language
    }

    public Player getPlayer() {
        Player player = playerList.getPlayer();
        return player;
    }

    public String menu() {
        String msg = "";
        if (playerList.getPlayer().isInJail() && playerList.getPlayer().hasGotFreeJailCard()) {
            msg = guiController.button(Message.getMessage("In Jail", 1)+Message.getMessage("In Jail",10),Message.getMessage("In Jail", 2), Message.getMessage("In Jail",3), Message.getMessage("In Jail", 4), Message.getMessage("In Jail",5));
        } else if (playerList.getPlayer().isInJail()) {
            msg = guiController.button(Message.getMessage("In Jail",1)+Message.getMessage("In Jail",10), Message.getMessage("In Jail", 2), Message.getMessage("In Jail",3), Message.getMessage("In Jail",5));
        } else {
            msg = guiController.button(Message.getMessage("In Jail",6) + " " +  playerList.getPlayer().getName() + Message.getMessage("In Jail",7), Message.getMessage("In Jail",2), Message.getMessage("In Jail",8));
        }
        if (msg.equals(Message.getMessage("In Jail",2))) {
            propertyController.manageMenu(guiController, playerList, squareController);
        }
        return msg;
    }




    public void jailLogic(String option) {
        Player player = playerList.getPlayer();
        String jailMsg = option;
        if (player.isInJail() && player.hasGotFreeJailCard()) {

            if (option.equals(Message.getMessage("In Jail",3))) {
                propertyController.payment(playerList, player.getName(), null, squareController, guiController, JAIL_BAIL_PRICE);
                player.setInJail(false);
                player.resetTurnsInJail();
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                player.setInJail(false);
            }

            if (option.equals(Message.getMessage("In Jail",4))) {
                player.useBailCard();
                player.setInJail(false);
            }

        } else if (player.isInJail()) {
            if (option.equals(Message.getMessage("In Jail",3))) {
                propertyController.payment(playerList, player.getName(), null, squareController, guiController, JAIL_BAIL_PRICE);
                player.setInJail(false);
                player.resetTurnsInJail();
                guiController.updateBalance(player.getName(), player.getBalance().getAmount());
                player.setInJail(false);
            }
        }
        //Sets extraTurn to true/false depending on getPair method
    }

    public void movePlayer() {
        // Movement process
        Player player = playerList.getPlayer();
        int startPos = player.getFieldPos();
        player.move(diceCup.getFaceValueSum(), true);
        guiController.movePlayer(player.getName(), player.getBalance().getAmount(), startPos, player.getFieldPos());
        // Land on
        squareController.getSquare(player.getFieldPos()).squareAction(playerList, guiController, propertyController, squareController, diceCup.getFaceValueSum());

        while (player.hasGotChanceCard()) {
            chanceCardController.pickCard(playerList, squareController, guiController, propertyController);
        }
    }

    public void updateProperties() {
        squareController.checkPairs();
    }

    // Throwing dice process
    public void rollDiceLogic() {
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

//    public void checkForLoser() {
//        //counters with losername and without losername used in remainingPlayer array
//        int remainingPlayercounter = 0;
//        int allPlayerCounter = 0;
//        Player[] playerArray = playerList.getAllPlayers();
//        //checks for all players if they have lost this turn
//        for (Player player : playerArray) {
//            if (!player.isHasLost() && (player.getBalance().getAmount()<0 || player.isAboutToLose())) {
//                guiController.removeLoser(player.getName(), player.getFieldPos(), squareController);
//                player.setHasLost(true);
//                player.setInJail(false);
//                player.extraTurn(false);
//
//                //sets new owner on loser's properties
//                String owner;
//                int pos = player.getFieldPos();
//                if (squareController.inPropertyPosition(pos)) {
//                    owner = squareController.searchProperty(squareController.getSquare(pos).getFieldName()).getOwner();
//                } else owner = "bank";
//
//
//                if (owner.equals(player.getName()) || owner.equals("bank")) {
//                    String[] ownedProperties = squareController.getOwnedPropertyNames(player.getName());
//                    for (String p : ownedProperties) {
//                        squareController.searchProperty(p).setMortgaged(false);
//                        if (squareController.searchStreet(p) != null) {
//                            squareController.searchStreet(p).setNumberOfHouses(0);
//                            guiController.setHouses(0,squareController.searchStreet(p).getFieldPosition());
//                        }
//                        squareController.searchProperty(p).setOwner("bank");
//                    }
//                } else {
//                    String[] ownedProperties = squareController.getOwnedPropertyNames(player.getName());
//                    for (String p : ownedProperties) {
//                        // sell houses and transfer money
//                        if (squareController.searchStreet(p) != null) {
//                            int totalHouseMoney = 0;
//                            int houses = squareController.searchStreet(p).getNumberOfHouses();
//                            int houseMoney = squareController.searchStreet(p).getHousePrice() / 2;
//                            totalHouseMoney += houses * houseMoney;
//                            squareController.searchStreet(p).setNumberOfHouses(0);
//                            guiController.setHouses(0,squareController.searchStreet(p).getFieldPosition());
//                            playerList.searchPlayer(owner).getBalance().add(totalHouseMoney);
//                        }
//                        squareController.searchProperty(p).setOwner(owner);
//                        if (squareController.searchProperty(p).getMortgaged()) {
//                            guiController.mortgageProperty(owner, squareController.searchProperty(p).getFieldPosition());
//                        } else {
//                            guiController.buyProperty(owner, squareController.searchProperty(p).getFieldPosition());
//                        }
//                    }
//                }
//
//                guiController.removeLoser(player.getName(), player.getFieldPos(), squareController);
//                playerList.killPlayer(player.getName());
//
//
//
//                if (!owner.equals("bank")) {
//                    playerList.searchPlayer(owner).getBalance().add(player.getBalance().getAmount());
//                    guiController.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
//                }
//                guiController.button(player.getName() + " has lost", "Ok");
//                guiController.killPlayer(player.getName(), playerArray.length);
//            }
//        }
//    }


}
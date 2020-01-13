package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;
import message.Message;

import java.util.Random;

public class ChanceList {
    private int cardIndex = 0;
    private ChanceCard[] chanceCards = {
            new CardType1(Message.getMessage("Chancecard", 15), -200),
            new CardType1(Message.getMessage("Chancecard", 16), -300),
            new CardType1(Message.getMessage("Chancecard", 17), -3000),
            new CardType1(Message.getMessage("Chancecard", 17), -3000),
            new CardType1(Message.getMessage("Chancecard", 18), -1000),
            new CardType1(Message.getMessage("Chancecard", 19), -200),
            new CardType1(Message.getMessage("Chancecard", 20), -1000),
            new CardType1(Message.getMessage("Chancecard", 21), -1000),
            new CardType1(Message.getMessage("Chancecard", 22), -2000),
            new CardType1(Message.getMessage("Chancecard", 25), 1000),
            new CardType1(Message.getMessage("Chancecard", 25), 1000),
            new CardType1(Message.getMessage("Chancecard", 26), 1000),
            new CardType1(Message.getMessage("Chancecard", 27), 200),
            new CardType1(Message.getMessage("Chancecard", 28), 1000),
            new CardType1(Message.getMessage("Chancecard", 29), 1000),
            new CardType1(Message.getMessage("Chancecard", 30), 1000),
            new CardType1(Message.getMessage("Chancecard", 30), 1000),
            new CardType1(Message.getMessage("Chancecard", 31), 3000),
            new CardType1(Message.getMessage("Chancecard", 32), 500),
            new CardType1(Message.getMessage("Chancecard", 32), 500),
            new CardType1(Message.getMessage("Chancecard", 33), 1000),

            new CardType2(Message.getMessage("Chancecard", 5), 15),
            new CardType2(Message.getMessage("Chancecard", 7), 24),
            new CardType2(Message.getMessage("Chancecard", 8), 32),
            new CardType2(Message.getMessage("Chancecard", 9), 11),
            new CardType2(Message.getMessage("Chancecard", 10), 19),
            new CardType2(Message.getMessage("Chancecard", 11), 39),
            new CardType2(Message.getMessage("Chancecard", 12), 0),
            new CardType2(Message.getMessage("Chancecard", 12), 0),

        //    new CardType7("gå i fængsel makker")
    };
    private int[] cardDeck = new int[chanceCards.length];

    public ChanceList() {
        Random random = new Random();

        // this for-loop generates an unique random number between 1 and chanceCards.length and enters them into the cardDeck array
        for (int i = 0; i < cardDeck.length; i++) {
            while (true) {
                int randomNumber = random.nextInt(chanceCards.length) + 1;

                boolean newNumber = true;
                for (int j = 0; j < cardDeck.length; j++) {
                    if (randomNumber == cardDeck[j]) {
                        newNumber = false;
                    }
                }

                if (newNumber == true) {
                    cardDeck[i] = randomNumber;
                    break;
                }
            }
        }
    }

    public void pickCard(PlayerList playerList, SquareList squareList, GUIController guiController) {
        chanceCards[cardDeck[cardIndex] - 1].chanceAction(playerList, squareList, guiController);
        cardIndex = (cardIndex + 1) % chanceCards.length;
        guiController.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
        guiController.button("Du har trukket et chancekort", "Okeydokey!");
    }



}

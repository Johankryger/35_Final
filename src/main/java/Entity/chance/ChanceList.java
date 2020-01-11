package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.square.SquareList;
import gui_main.GUI;

import java.util.Random;

public class ChanceList {
    private int cardIndex = 0;
    private ChanceCard[] chanceCards = {
        new CardType1("De har vundet i Klasse lotteriet. Modtag 500 kr.", 500),
        new CardType1("De har modtaget Deres tandlægeregning. Betal 2000 kr.", -2000),
            new CardType7("gå i fængsel makker")
    };
    private int[] cardDeck = new int[chanceCards.length];

    public ChanceList() {
        Random random = new Random();

        for (int i = 0; i < cardDeck.length; i++) {
            while (true) {
                int randomNumber = random.nextInt(3) + 1;

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
        chanceCards[cardIndex].chanceAction(playerList, squareList, guiController);
        cardIndex = cardIndex++ % chanceCards.length;
    }



}

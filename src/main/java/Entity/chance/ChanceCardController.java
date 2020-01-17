package Entity.chance;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import Entity.square.SquareController;
import message.Message;

import java.util.Random;

public class ChanceCardController {
    private int cardIndex = 0;
    private ChanceCard[] chanceCards = {
            new MoneyCard(Message.getMessage("Chancecard", 15), -200),
            new MoneyCard(Message.getMessage("Chancecard", 14), -200),
            new MoneyCard(Message.getMessage("Chancecard", 15), -200),
            new MoneyCard(Message.getMessage("Chancecard", 16), -300),
            new MoneyCard(Message.getMessage("Chancecard", 17), -3000),
            new MoneyCard(Message.getMessage("Chancecard", 17), -3000),
            new MoneyCard(Message.getMessage("Chancecard", 18), -1000),
            new MoneyCard(Message.getMessage("Chancecard", 19), -200),
            new MoneyCard(Message.getMessage("Chancecard", 20), -1000),
            new MoneyCard(Message.getMessage("Chancecard", 21), -1000),
            new MoneyCard(Message.getMessage("Chancecard", 22), -2000),
            new MoneyCard(Message.getMessage("Chancecard", 25), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 25), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 26), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 27), 200),
            new MoneyCard(Message.getMessage("Chancecard", 28), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 29), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 30), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 30), 1000),
            new MoneyCard(Message.getMessage("Chancecard", 31), 3000),
            new MoneyCard(Message.getMessage("Chancecard", 32), 500),
            new MoneyCard(Message.getMessage("Chancecard", 32), 500),
            new MoneyCard(Message.getMessage("Chancecard", 33), 1000),

            new MoveCard(Message.getMessage("Chancecard", 5), 15),
            new MoveCard(Message.getMessage("Chancecard", 7), 24),
            new MoveCard(Message.getMessage("Chancecard", 8), 32),
            new MoveCard(Message.getMessage("Chancecard", 9), 11),
            new MoveCard(Message.getMessage("Chancecard", 10), 19),
            new MoveCard(Message.getMessage("Chancecard", 11), 39),
            new MoveCard(Message.getMessage("Chancecard", 12), 0),
            new MoveCard(Message.getMessage("Chancecard", 12), 0),

            new MoveToShipCard(Message.getMessage("Chancecard", 6), 1),
            new MoveToShipCard(Message.getMessage("Chancecard", 13), 2),
            new MoveToShipCard(Message.getMessage("Chancecard", 13), 2),

            new MoveBackwardsCard(Message.getMessage("Chancecard", 4)),
            new MoveBackwardsCard(Message.getMessage("Chancecard", 4)),

            new JailFreeCard(Message.getMessage("Chancecard", 2)),
            new JailFreeCard(Message.getMessage("Chancecard", 2)),

            new ReceiveMoneyFromAllCard(Message.getMessage("Chancecard", 34), 200 ),
            new ReceiveMoneyFromAllCard(Message.getMessage("Chancecard", 35), 500 ),
            new ReceiveMoneyFromAllCard(Message.getMessage("Chancecard", 36), 500 ),

            new GoToJailCard(Message.getMessage("Chancecard", 1)),
            new GoToJailCard(Message.getMessage("Chancecard", 1)),

            new PayPropertyTaxCard(Message.getMessage("Chancecard", 23), 800, 2300),
            new PayPropertyTaxCard(Message.getMessage("Chancecard", 24), 500, 2000),

            new MatadorLegatCard(Message.getMessage("Chancecard", 37)),

            new MoveForwardsCard(Message.getMessage("Chancecard",3)),


    };
    private int[] cardDeck = new int[chanceCards.length];

    public ChanceCardController() {
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

    public void pickCard(PlayerList playerList, SquareController squareList, GUIController guiController, PropertyController propertyController) {
        playerList.getPlayer().setGotChanceCard(false);
        guiController.button(Message.getMessage("General", 9), Message.getMessage("General", 7));
        chanceCards[cardDeck[cardIndex] - 1].chanceAction(playerList, squareList, guiController, propertyController);
        cardIndex = (cardIndex + 1) % chanceCards.length;
        guiController.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
    }



}

package Entity.chance;

import Controller.GUIController;
import Entity.PlayerList;
import Entity.SquareList;
import logic.GameLogic;

public class CardType9 extends ChanceCard {
    public CardType9(String msg) {
        super(msg);
    }

    @Override
    public void chanceAction(PlayerList playerList, SquareList squareList, GUIController guiController) {
        guiController.showChanceCard(msg);

        int totalWealth = GameLogic.countTotalValue(playerList, squareList);

        if (totalWealth <= 15000) {
            playerList.getPlayer().getBalance().add(40000);
            guiController.button("It's your lucky day! 40.000 kr. was added to your balance", "ok");
        } else {
            guiController.button("Your total value is over 15.000 kr. Too bad.");
        }
    }
}

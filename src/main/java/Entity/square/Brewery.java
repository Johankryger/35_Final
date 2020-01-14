package Entity.square;


import Controller.GUIController;
import Entity.PlayerList;

public class Brewery extends Property {

    public Brewery(int fieldPosition, String fieldName, int price, int rent) {
        super(fieldPosition, fieldName, price, rent);
    }

    @Override
    public boolean squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (owner.equals("bank")) {
            if (playerList.getPlayer().isHasLiquidated()) {
                playerList.getPlayer().getBalance().pay(price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                playerList.getPlayer().setHasLiquidated(false);
                return false;
            }

            String option;
            if (playerList.getPlayer().getLiqudationValue() >= price)
                option = gui.button("Want to buy " + fieldName + "?", "Buy", "Auction");
            else
                option = gui.button("You can't afford this property",  "Auction");
            if (option.equals("Buy")) {
                if (price > playerList.getPlayer().getBalance().getAmount()) {
                    playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount()-price);
                    return true;
                }
                playerList.getPlayer().getBalance().pay(price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            } else {
                //setAuction(True, fieldPosition);
                //gui.auction(fieldPosition);
            }
        } else if (!owner.equals(playerList.getPlayer().getName())) {
            if (!playerList.searchPlayer(owner).isInJail()) {
                if (playerList.getPlayer().getLiqudationValue() >= rent * diceSum && playerList.getPlayer().getBalance().getAmount() < rent * diceSum){
                    playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount()-rent * diceSum);
                    return true;
                }
                else if (playerList.getPlayer().getBalance().getAmount() < rent * diceSum){
                    playerList.getPlayer().setHasLost(true);
                    return false;
                }
                gui.button("You pay " + rent * diceSum + " to " + owner, "Okay");
                playerList.transfer(rent * diceSum, playerList.getPlayer().getName(), owner);
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
        return false;
    }

    public void setPaired(boolean isPaired) {
        // only when paired is changed
        if (this.isPaired && !isPaired) {
            this.rent = this.rent / 2;
        } else if (!this.isPaired && isPaired) {
            this.rent = this.rent * 2;
        }

        this.isPaired = isPaired;
    }
}

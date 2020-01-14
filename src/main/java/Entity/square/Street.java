package Entity.square;

import Controller.GUIController;
import Entity.PlayerList;

public class Street extends Property {
    int housePrice, oneHouseRent, twoHouseRent, threeHouseRent, fourHouseRent, hotelRent, numberOfHouses;
    private boolean canBuildHouse = false;

    String color;

    public Street(int fieldPosition, String fieldName, int price, int rent, int housePrice,
                  int oneHouseRent, int twoHouseRent, int threeHouseRent, int fourHouseRent, int hotelRent, String color) {
        super(fieldPosition, fieldName, price, rent);
        this.oneHouseRent = oneHouseRent;
        this.twoHouseRent = twoHouseRent;
        this.threeHouseRent = threeHouseRent;
        this.fourHouseRent = fourHouseRent;
        this.hotelRent = hotelRent;
        this.housePrice = housePrice;
        this.color = color;
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
                if (playerList.getPlayer().getLiqudationValue() >= rent && playerList.getPlayer().getBalance().getAmount() < rent){
                    playerList.getPlayer().setMoneyToPay(playerList.getPlayer().getBalance().getAmount()-rent);
                    return true;
                }
                else if (playerList.getPlayer().getBalance().getAmount() < rent){
                    playerList.getPlayer().setAboutToLose(true);
                    return false;
                }
                gui.button("You pay " + rent + " to " + owner, "Okay");
                playerList.transfer(rent, playerList.getPlayer().getName(), owner);
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
        return false;
    }

    public void addHouse(){
        this.numberOfHouses++;
        switch (numberOfHouses) {
            case 1:
                this.rent = oneHouseRent;
                break;
            case 2:
                this.rent = twoHouseRent;
                break;
            case 3:
                this.rent = threeHouseRent;
                break;
            case 4:
                this.rent = fourHouseRent;
                break;
            case 5:
                this.rent = hotelRent;
                break;
        }
    }

    public void removeHouse(){
        numberOfHouses--;
        switch (numberOfHouses) {
            case 0:
                this.rent = defaultRent;
                break;
            case 1:
                this.rent = oneHouseRent;
                break;
            case 2:
                this.rent = twoHouseRent;
                break;
            case 3:
                this.rent = threeHouseRent;
                break;
            case 4:
                this.rent = fourHouseRent;
                break;
        }
    }

    public void setCanBuildHouse(boolean canBuildHouse) {
        this.canBuildHouse = canBuildHouse;
    }

    public boolean isCanBuildHouse() {
        return canBuildHouse;
    }

    public int getNumberOfHouses(){
        return numberOfHouses;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setPaired(boolean paired){
        // only when paired is changed
        if (this.isPaired && !paired) {
            this.rent = this.rent / 2;
        } else if (!this.isPaired && paired) {
            this.rent = this.rent * 2;
        }

        this.isPaired = paired;
    }
    public String getColor(){return color;}



}
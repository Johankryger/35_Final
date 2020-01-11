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
    public void squareAction(PlayerList playerList, GUIController gui, int diceSum) {
        if (owner.equals("bank")) {
            String option = gui.button("Want to buy " + fieldName + "?", "Buy", "Auction");
            if (option.equals("Buy")) {
                playerList.getPlayer().getBalance().pay(price);
                gui.buyStreet(playerList.getPlayer().getName(), fieldPosition);
                setOwner(playerList.getPlayer().getName());
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            }
            else {
                //setAuction(True, fieldPosition);
                //gui.auction(fieldPosition);
            }
        }
        else if (!owner.equals(playerList.getPlayer().getName())){
            playerList.transfer(price,playerList.getPlayer().getName(),owner);
        }
    }

    public void addHouse(){
        numberOfHouses++;
    }

    public void removeHouse(){
        numberOfHouses--;
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
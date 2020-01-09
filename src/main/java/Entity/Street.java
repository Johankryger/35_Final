package Entity;

import Controller.GUIController;

public class Street extends Property {
    int housePrice, oneHouseRent, twoHouseRent, threeHouseRent, fourHouseRent, hotelRent, numberOfHouses;
    boolean paired;

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

    public int getNumberOfHouses(){
        return numberOfHouses;
    }

    public void setPaired(boolean paired){
        this.paired = paired;
    }
    public String getColor(){return color;}

}
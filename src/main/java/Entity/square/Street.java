package Entity.square;

import Controller.GUIController;
import Controller.PropertyController;
import Entity.PlayerList;
import message.Message;

public class Street extends Property {
    private int housePrice, oneHouseRent, twoHouseRent, threeHouseRent, fourHouseRent, hotelRent, numberOfHouses;
    private boolean canBuildHouse = false;
    private String color;

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
    public void squareAction(PlayerList playerList, GUIController gui, PropertyController propertyController, SquareController squareController, int diceSum) {
        if (owner.equals("bank")) {
            String option = "";
            if (playerList.getPlayer().getLiqudationValue() < price) {
                option = gui.button(Message.getMessage("General", 1) + " " + fieldName + "?", Message.getMessage("General", 4));
            } else {
                option = gui.button(Message.getMessage("General", 1) + " " + fieldName + "?", Message.getMessage("General", 3), Message.getMessage("General", 4));
            }

            if (option.equals(Message.getMessage("General",3))) {
                propertyController.payment(playerList, playerList.getPlayer().getName(), null, squareController, gui, price);
                gui.buyProperty(playerList.getPlayer().getName(), fieldPosition);
                owner = playerList.getPlayer().getName();
                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
            }
        } else if (!owner.equals(playerList.getPlayer().getName())) {
            if (!playerList.searchPlayer(owner).isInJail()) {
                propertyController.payment(playerList, playerList.getPlayer().getName(), owner, squareController, gui, rent);
                if (playerList.getPlayer() != null) {
                    gui.button((Message.getMessage("General",5)) + " "  + rent + " " + (Message.getMessage("General",6))+ " " + owner, (Message.getMessage("General",7)));
                } else {
                    gui.button(Message.getMessage("General", 12), Message.getMessage("General", 7));
                }

                gui.updateBalance(playerList.getPlayer().getName(), playerList.getPlayer().getBalance().getAmount());
                gui.updateBalance(owner, playerList.searchPlayer(owner).getBalance().getAmount());
            }
        }
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

    public void setNumberOfHouses(int numberOfHouses) {
        this.numberOfHouses = numberOfHouses;
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
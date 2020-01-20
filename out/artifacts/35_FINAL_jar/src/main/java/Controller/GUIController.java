package Controller;


import gui_fields.*;
import gui_main.GUI;
import message.Message;

import java.awt.*;

public class GUIController {

    private GUI_Field[] fields = {
            new GUI_Start("START", (Message.getMessage("SubText", 1)), (Message.getMessage("Desc", 1)),  Color.RED, Color.WHITE),
            new GUI_Street("Rødovrevej",  (Message.getMessage("SubText", 2)),(Message.getMessage("Desc", 2)),(Message.getMessage("SubText", 3)),
                    new Color(72, 139, 247), Color.white),
            new GUI_Chance("?",(Message.getMessage("SubText",3)),(Message.getMessage("Desc",3)),Color.black,Color.white),
            new GUI_Street("Hvidovrevej",  (Message.getMessage("SubText", 4)),(Message.getMessage("Desc", 4)),(Message.getMessage("SubText", 5)),
                    new Color(72, 139, 247), Color.white),
            new GUI_Tax((Message.getMessage("Title", 4)), (Message.getMessage("SubText",5)), (Message.getMessage("Desc", 5)), new Color(230, 191, 3), new Color(75, 136, 179)),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "Scandlines", (Message.getMessage("SubText", 6)), (Message.getMessage("Desc", 6)), (Message.getMessage("SubText", 6)), new Color(60, 6, 249), Color.white),
            new GUI_Street("Roskildevej", (Message.getMessage("SubText", 7)), (Message.getMessage("Desc", 7)),(Message.getMessage("SubText", 7)), new Color(242, 125, 41), Color.white),
            new GUI_Chance("?",(Message.getMessage("SubText",8)),(Message.getMessage("Desc",8)),Color.black,Color.white),
            new GUI_Street("Valby Langgade", (Message.getMessage("SubText", 9)), (Message.getMessage("Desc", 9)), (Message.getMessage("SubText", 9)), new Color(242, 125, 41), Color.white),
            new GUI_Street("Allégade", (Message.getMessage("SubText", 10)), (Message.getMessage("Desc", 10)), (Message.getMessage("SubText", 10)), new Color(242, 125, 41), Color.white),
            new GUI_Refuge("src/main/resources/pictures/ironbars.png", (Message.getMessage("Title", 1)), (Message.getMessage("SubText", 11)), (Message.getMessage("Desc", 11)), Color.white, Color.black),
            new GUI_Street("Frederiksberg Allé", (Message.getMessage("SubText", 12)), (Message.getMessage("Desc", 12)), (Message.getMessage("SubText", 12)), new Color(0, 189, 4), Color.white),
            new GUI_Brewery("src/main/resources/Pictures/squash.png", "Squash", (Message.getMessage("SubText", 13)), (Message.getMessage("Desc", 13)), "", new Color(170, 104, 39), Color.white),
            new GUI_Street("Bülowsvej", (Message.getMessage("SubText", 14)), (Message.getMessage("Desc", 14)), (Message.getMessage("SubText", 14)), new Color(0, 189, 4), Color.white),
            new GUI_Street("Gammel Kongevej", (Message.getMessage("SubText", 15)), (Message.getMessage("Desc", 15)), (Message.getMessage("SubText", 15)), new Color(0, 189, 4), Color.white),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "Molslinjen", (Message.getMessage("SubText", 16)), (Message.getMessage("Desc", 16)), (Message.getMessage("SubText", 16)), new Color(60, 6, 249), Color.white),
            new GUI_Street("Bernstorffsvej", (Message.getMessage("SubText", 17)), (Message.getMessage("Desc", 17)), (Message.getMessage("SubText", 17)), new Color(162, 163, 162), Color.white),
            new GUI_Chance("?",(Message.getMessage("SubText",18)),(Message.getMessage("Desc",18)),Color.black,Color.white),
            new GUI_Street("Hellerupvej", (Message.getMessage("SubText", 19)), (Message.getMessage("Desc", 19)), (Message.getMessage("SubText", 19)), new Color(162, 163, 162), Color.white),
            new GUI_Street("Strandvejen", (Message.getMessage("SubText", 20)), (Message.getMessage("Desc", 20)), (Message.getMessage("SubText", 20)), new Color(162, 163, 162), Color.white),
            new GUI_Refuge("src/main/resources/Pictures/parking.png", (Message.getMessage("Title",2)), (Message.getMessage("SubText", 21)), (Message.getMessage("Desc", 21)), Color.black, Color.white),
            new GUI_Street("Trianglen", (Message.getMessage("SubText", 22)), (Message.getMessage("Desc", 22)), (Message.getMessage("Desc", 22)), new Color(226, 39, 39), Color.white),
            new GUI_Chance("?",(Message.getMessage("SubText",23)),(Message.getMessage("Desc",23)),Color.black,Color.white),
            new GUI_Street("Østerbrogade", (Message.getMessage("SubText", 24)), (Message.getMessage("Desc", 24)), (Message.getMessage("SubText", 24)), new Color(226, 39, 39), Color.white),
            new GUI_Street("Grønningen", (Message.getMessage("SubText", 25)), (Message.getMessage("Desc", 25)), (Message.getMessage("SubText", 25)), new Color(226, 39, 39), Color.white),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "Mærsk", (Message.getMessage("SubText", 26)), (Message.getMessage("Desc", 26)), (Message.getMessage("SubText", 26)), new Color(60, 6, 249), Color.white),
            new GUI_Street("Bredgade", (Message.getMessage("SubText", 27)), (Message.getMessage("Desc", 27)), (Message.getMessage("SubText", 27)), Color.WHITE, Color.black),
            new GUI_Street("Kgs. Nytorv", (Message.getMessage("SubText", 28)), (Message.getMessage("Desc", 28)), (Message.getMessage("SubText", 28)), Color.WHITE, Color.black),
            new GUI_Brewery("src/main/resources/pictures/cola.png", "Cola", (Message.getMessage("SubText", 29)), (Message.getMessage("Desc", 29)), (Message.getMessage("SubText",29)), new Color(170, 104, 39), Color.white),
            new GUI_Street("Østergade", (Message.getMessage("SubText", 30)), (Message.getMessage("Desc", 30)), (Message.getMessage("SubText", 30)), Color.WHITE, Color.black),
            new GUI_Jail("src/main/resources/pictures/prisoner.png", (Message.getMessage("Title",3)),(Message.getMessage("SubText", 31)),(Message.getMessage("Desc", 31)), new Color(125, 125, 125), Color.BLACK),
            new GUI_Street("Amagertorv", (Message.getMessage("SubText", 32)), (Message.getMessage("Desc", 32)), (Message.getMessage("SubText", 32)), new Color(192 , 188,13), Color.black),
            new GUI_Street("Vimmelskaftet", (Message.getMessage("SubText", 33)), (Message.getMessage("Desc", 33)), (Message.getMessage("SubText", 33)), new Color(198,188,13), Color.black),
            new GUI_Chance("?",(Message.getMessage("SubText",34)),(Message.getMessage("Desc",34)),Color.black,Color.white),
            new GUI_Street("Nygade", (Message.getMessage("SubText", 35)), (Message.getMessage("Desc", 35)), (Message.getMessage("SubText", 35)), new Color(192,188,13), Color.black),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "DFDS", (Message.getMessage("SubText", 36)), (Message.getMessage("Desc", 36)), (Message.getMessage("SubText", 36)), new Color(60, 6, 249), Color.white),
            new GUI_Chance("?",(Message.getMessage("SubText",37)),(Message.getMessage("Desc",37)),Color.black,Color.white),
            new GUI_Street("Frederiksberggade", (Message.getMessage("SubText", 38)), (Message.getMessage("Desc", 38)), "Pris: 7.000 kr", new Color(165, 6, 209), Color.white),
            new GUI_Tax((Message.getMessage("Title", 5)), (Message.getMessage("SubText",39)), (Message.getMessage("Desc", 39)), new Color(230, 191, 3), new Color(75, 136, 179)),
            new GUI_Street("Rådhuspladsen", (Message.getMessage("SubText", 40)), (Message.getMessage("Desc", 40)), (Message.getMessage("SubText", 40)), new Color(165, 6, 209), Color.white)
    };


    private GUI gui = new GUI(fields);

    private GUI_Player[] gui_players;
    private GUI_Car[] gui_cars = {
            new GUI_Car(Color.magenta, Color.magenta, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.CYAN, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.red, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.yellow, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.green, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.pink, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL)
    };


    public GUI_Player searchGUIPlayer(String name) {
        //finds the player with that name
        GUI_Player choosenPlayer = null;
        for (GUI_Player p: gui_players) {
            if (p.getName().equals(name)) {
                choosenPlayer = p;
            }
        }
        return choosenPlayer;
    }


    public String[] startMenu() {
        //sets all fields' property borders to black
        for (GUI_Field field : fields) {
            if (field instanceof GUI_Ownable)
                ((GUI_Ownable) field).setBorder(Color.black, Color.black);
        }

        String language = gui.getUserButtonPressed("Choose language // Vælg sprog", "Dansk", "English");
        Message.setLanguage(language);
        int balance = 30000;
        int amountOfPlayers = gui.getUserInteger(Message.getMessage("start menu",1), 3 ,6);
        for (int i = 0; i < fields.length ; i++) {
                fields[i].setSubText(Message.getMessage("SubText", i+1));
                fields[i].setDescription(Message.getMessage("Desc",i+1));
                fields[4].setTitle((Message.getMessage("Title",4)));
                fields[10].setTitle((Message.getMessage("Title",1)));
                fields[20].setTitle((Message.getMessage("Title",2)));
                fields[30].setTitle((Message.getMessage("Title",3)));
                fields[38].setTitle((Message.getMessage("Title",5)));
        }

        //makes it invalid to select less or more than 3 or 6 players
        while (true){
            if (amountOfPlayers > 2 && amountOfPlayers < 7){
                break;
            }
            else{
                gui.showMessage(Message.getMessage("Fejlbesked",1));
                amountOfPlayers = gui.getUserInteger(Message.getMessage("start menu",1 ),3,6);
            }
        }
        //requests names and stores them in an array which is returned at end of method
        String[] names = new String[amountOfPlayers];
        for (int i = 0; i < amountOfPlayers; i++) {
            names[i] = gui.getUserString(Message.getMessage("start menu",2) +" "+ (i+1) + Message.getMessage("start menu",3));
        }

        //checks for empty name
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("")){
                names[i] = "Player " + (i+1);
            }
        }

        //checks if any player is named "Player x or bank"
        for (int i = 0; i < names.length ; i++) {
            for (int j = 0; j < names.length; j++) {
                if (names[i].equals("Player " +j) || names[i].equals("Player 6") || names[i].equals("bank")){
                    names[i] = "Player 1";
                }
            }
        }

        //Checks for the same name
        String[] newNames = names;
        int counter = 1;
        for (int i = 0; i <names.length ; i++) {
            for (int j = i+1; j < names.length; j++) {
                if (names[i].equals(names[j])){
                    newNames[j] = "Player " + ( counter + 1);
                    counter++;
                }
            }
        }
        names = newNames;

        gui_players = new GUI_Player[amountOfPlayers];
        // generating piece for each player on gui board
        for (int i = 0; i < amountOfPlayers; i++) {
            gui_players[i] = new GUI_Player(names[i], balance, gui_cars[i]);
            gui.addPlayer(gui_players[i]);
            fields[0].setCar(gui_players[i], true);
        }



        return names;
    }

    public void killPlayer(String name, int placed) {
        GUI_Player player = searchGUIPlayer(name);
        player.setName(name + " - " + placed + "th place");
        player.setBalance(0);
    }

    public void showDice(int[] values) {
        gui.setDice(values[0],values[1]);
    }

    // makes an animation of player's piece moving
    public void movePlayer(String name, int balance, int from, int to) {
        GUI_Player choosenPlayer = searchGUIPlayer(name);
        // keeps going adding 1 to i without reaching over 39 till i == to
        for (int i = from; i != to; i = ++i % 40) {
            sleep(300);

            // move player one square forward at a time
            fields[i].setCar(choosenPlayer, false);
            fields[(i + 1) % 40].setCar(choosenPlayer, true); // modulo is used to never each index 40 in case i = 39

            // balance is updating when player moves from position 0 to 1
            if (i == 0)
                updateBalance(name, balance);

        }
    }

    public void movePlayerFast(String name, int from, int to) {
        GUI_Player choosenPlayer = searchGUIPlayer(name);
        fields[from].setCar(choosenPlayer, false);
        fields[to].setCar(choosenPlayer, true);
    }

    public void moveBackwards(String name, int from, int to) {
        GUI_Player choosenPlayer = searchGUIPlayer(name);
        //move player one square forward at a time
        for (int i = from; i != to; i = ((i - 1) + 40) % 40) {
            sleep(300);
            fields[i].setCar(choosenPlayer, false);
            if (i == 0) {
                fields[39].setCar(choosenPlayer, true);
            } else {
                fields[i - 1].setCar(choosenPlayer, true);
            }
        }
    }

    public void updateBalance(String name, int balance) {
        searchGUIPlayer(name).setBalance(balance);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public String scrollList(String messsage, String ... arrayOptions){
        return gui.getUserSelection(messsage, arrayOptions);
    }

    public void showChanceCard(String msg) {
        gui.displayChanceCard(msg);
    }

    public void buyProperty(String name, int pos){
        // finds player with the name and the players piece color
        Color carColor = searchGUIPlayer(name).getPrimaryColor();

        if (fields[pos] instanceof GUI_Ownable)
            ((GUI_Ownable) fields[pos]).setBorder(carColor);
    }

    public void mortgageProperty(String name, int pos) {
        // finds player with the name and the players piece color
        Color carColor = searchGUIPlayer(name).getPrimaryColor();

        if (fields[pos] instanceof GUI_Ownable)
            ((GUI_Ownable) fields[pos]).setBorder(carColor, Color.black);
    }

    public void setHouses(int houses, int pos) {
        if (houses == 5) {
            ((GUI_Street) fields[pos]).setHotel(true);
        } else {
            ((GUI_Street) fields[pos]).setHotel(false);
            ((GUI_Street) fields[pos]).setHouses(houses);
        }
    }


    public String button(String msg, String ... buttons) {
        return gui.getUserButtonPressed(msg, buttons);
    }
    public int getUserInteger(String msg, int min, int max){
        return gui.getUserInteger(msg,min,max);
    }

    public void close(){
        gui.close();
    }

    public void removeLoser(String name, int position, SquareController squareList){
        //sets all owned property by losing player to have black border
        fields[position].setCar(searchGUIPlayer(name), false);
        String[] ownedPropertyNames = squareList.getOwnedPropertyNames(name);

        for (String ownedPropertyName : ownedPropertyNames) {
            for (int j = 0; j < fields.length; j++) {
                if (ownedPropertyName.equals(squareList.getSquare(j).getFieldName())){
                    if (fields[j] instanceof GUI_Ownable)
                        ((GUI_Ownable) fields[j]).setBorder(Color.black, Color.black);
                }
            }
        }
    }
}

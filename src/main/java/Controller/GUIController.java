package Controller;

import gui_fields.*;
import gui_main.GUI;
import message.Message;

import java.awt.*;

public class GUIController {

    private GUI_Field[] fields = {
            new GUI_Start("START", (Message.getMessage("Squares", 1)), (Message.getMessage("Squares", 2)),  Color.RED, Color.WHITE),
            new GUI_Street("Rødovrevej",  (Message.getMessage("Squares", 3)),(Message.getMessage("Squares", 4)),(Message.getMessage("Squares", 3)),
                    new Color(72, 139, 247), Color.white),
            new GUI_Chance("?","Chance","",Color.black,Color.white),
            new GUI_Street("Hvidovrevej",  (Message.getMessage("Squares", 5)),(Message.getMessage("Squares", 6)),(Message.getMessage("Squares", 5)),
                    new Color(72, 139, 247), Color.white),
            new GUI_Tax((Message.getMessage("Squares", 7)), "", (Message.getMessage("Squares", 7)), Color.orange, Color.black),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "Scandlines", (Message.getMessage("Squares", 8)), (Message.getMessage("Squares", 9)), (Message.getMessage("Squares", 8)), Color.white, Color.black),
            new GUI_Street("Roskildevej", (Message.getMessage("Squares", 10)), (Message.getMessage("Squares", 11)), (Message.getMessage("Squares", 10)), new Color(242, 125, 41), Color.white),
            new GUI_Chance("?","Chance","",Color.black,Color.white),
            new GUI_Street("Valby Langgade", (Message.getMessage("Squares", 12)), (Message.getMessage("Squares", 11)), (Message.getMessage("Squares", 12)), new Color(242, 125, 41), Color.white),
            new GUI_Street("Allégade", (Message.getMessage("Squares", 14)), (Message.getMessage("Squares", 15)), (Message.getMessage("Squares", 14)), new Color(242, 125, 41), Color.white),
            new GUI_Refuge("src/main/resources/pictures/ironbars.png", (Message.getMessage("Squares", 16)), (Message.getMessage("Squares", 16)), (Message.getMessage("Squares", 16)), Color.white, Color.black),
            new GUI_Street("Frederiksberg Allé", (Message.getMessage("Squares", 17)), (Message.getMessage("Squares", 18)), (Message.getMessage("Squares", 17)), new Color(0, 189, 4), Color.white),
            new GUI_Brewery("src/main/resources/Pictures/squash.png", "Squash", (Message.getMessage("Squares", 19)), (Message.getMessage("Squares", 20)), "", Color.cyan, Color.BLACK),
            new GUI_Street("Bülowsvej", (Message.getMessage("Squares", 21)), (Message.getMessage("Squares", 22)), (Message.getMessage("Squares", 21)), new Color(0, 189, 4), Color.white),
            new GUI_Street("Gammel Kongevej", (Message.getMessage("Squares", 23)), (Message.getMessage("Squares", 24)), (Message.getMessage("Squares", 23)), new Color(0, 189, 4), Color.white),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "Molslinjen", (Message.getMessage("Squares", 25)), (Message.getMessage("Squares", 26)), (Message.getMessage("Squares", 25)), Color.white, Color.black),
            new GUI_Street("Bernstorffsvej", (Message.getMessage("Squares", 28)), (Message.getMessage("Squares", 27)), (Message.getMessage("Squares", 28)), new Color(162, 163, 162), Color.white),
            new GUI_Chance("?","Chance","",Color.black,Color.white),
            new GUI_Street("Hellerupvej", (Message.getMessage("Squares", 28)), (Message.getMessage("Squares", 29)), (Message.getMessage("Squares", 28)), new Color(162, 163, 162), Color.white),
            new GUI_Street("Strandvejen", (Message.getMessage("Squares", 30)), (Message.getMessage("Squares", 31)), (Message.getMessage("Squares", 30)), new Color(162, 163, 162), Color.white),
            new GUI_Refuge("src/main/resources/Pictures/parking.png", "", (Message.getMessage("Squares", 32)), "", Color.black, Color.white),
            new GUI_Street("Trianglen", (Message.getMessage("Squares", 33)), (Message.getMessage("Squares", 34)), (Message.getMessage("Squares", 33)), Color.RED, Color.white),
            new GUI_Chance("?","Chance","",Color.black,Color.white),
            new GUI_Street("Østerbrogade", (Message.getMessage("Squares", 35)), (Message.getMessage("Squares", 36)), (Message.getMessage("Squares", 35)), Color.RED, Color.white),
            new GUI_Street("Grønningen", (Message.getMessage("Squares", 37)), (Message.getMessage("Squares", 38)), (Message.getMessage("Squares", 37)), Color.RED, Color.white),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "Mærsk", (Message.getMessage("Squares", 39)), (Message.getMessage("Squares", 40)), (Message.getMessage("Squares", 39)), Color.white, Color.black),
            new GUI_Street("Bredgade", (Message.getMessage("Squares", 41)), (Message.getMessage("Squares", 42)), (Message.getMessage("Squares", 41)), Color.WHITE, Color.black),
            new GUI_Street("Kgs. Nytorv", (Message.getMessage("Squares", 43)), (Message.getMessage("Squares", 44)), (Message.getMessage("Squares", 43)), Color.WHITE, Color.black),
            new GUI_Brewery("src/main/resources/pictures/cola.png", "Cola", (Message.getMessage("Squares", 45)), (Message.getMessage("Squares", 46)), "", Color.cyan, Color.BLACK),
            new GUI_Street("Østergade", (Message.getMessage("Squares", 47)), (Message.getMessage("Squares", 48)), (Message.getMessage("Squares", 47)), Color.WHITE, Color.black),
            new GUI_Jail("src/main/resources/pictures/prisoner.png", (Message.getMessage("Squares", 49)), (Message.getMessage("Squares", 50)), (Message.getMessage("Squares", 49)), Color.black, Color.WHITE),
            new GUI_Street("Amagertorv", (Message.getMessage("Squares", 51)), (Message.getMessage("Squares", 52)), (Message.getMessage("Squares", 51)), new Color(255, 251, 13), Color.black),
            new GUI_Street("Vimmelskaftet", (Message.getMessage("Squares", 53)), (Message.getMessage("Squares", 54)), (Message.getMessage("Squares", 53)), new Color(255, 251, 13), Color.black),
            new GUI_Chance("?","Chance","",Color.black,Color.white),
            new GUI_Street("Nygade", (Message.getMessage("Squares", 55)), (Message.getMessage("Squares", 56)), (Message.getMessage("Squares", 55)), new Color(255, 251, 13), Color.black),
            new GUI_Shipping("src/main/resources/Pictures/ship.png", "DFDS", (Message.getMessage("Squares", 57)), (Message.getMessage("Squares", 58)), (Message.getMessage("Squares", 57)), Color.white, Color.black),
            new GUI_Chance("?","Chance","",Color.black,Color.white),
            new GUI_Street("Frederiksberggade", (Message.getMessage("Squares", 59)), (Message.getMessage("Squares", 60)), "Pris: 7.000 kr", new Color(165, 6, 209), Color.white),
            new GUI_Tax((Message.getMessage("Squares", 61)), "", (Message.getMessage("Squares", 61)), Color.orange, Color.black),
            new GUI_Street("Rådhuspladsen", (Message.getMessage("Squares", 62)), (Message.getMessage("Squares", 63)), (Message.getMessage("Squares", 62)), new Color(165, 6, 209), Color.white)
    };
    private GUI gui = new GUI(fields);
    private GUI_Player[] gui_players;
    private GUI_Car[] gui_cars = {
            new GUI_Car(Color.magenta, Color.magenta, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.CYAN, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.red, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.yellow, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.green, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.white, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL)
    };


    public GUI_Player searchGUIPlayer(String name) {
        //finds the player with that name
        GUI_Player choosenPlayer = null;
        for (GUI_Player p: gui_players) {
            if (p.getName().equals(name)){
                choosenPlayer = p;
            }
        }
        return choosenPlayer;
    }


    public String[] startMenu() {
        String language = gui.getUserButtonPressed("Choose language", "Dansk", "English");
        Message.setLanguage(language);
        int balance = 30000;
        int amountOfPlayers = gui.getUserInteger(Message.getMessage("start menu",1), 3 ,6);

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
            names[i] = gui.getUserString(Message.getMessage("start menu",2) + (i+1) + Message.getMessage("start menu",3));
        }

        //checks for empty name
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("")){
                names[i] = "Player " + (i+1);
            }
        }

        gui_players = new GUI_Player[amountOfPlayers];
        // generating piece for each player on gui board
        for (int i = 0; i < amountOfPlayers; i++) {
            gui_players[i] = new GUI_Player(names[i], balance, gui_cars[i]);
            gui.addPlayer(gui_players[i]);
            fields[0].setCar(gui_players[i], true);
        }
        return names;
    }

    public void showDice(int[] values) {
        gui.setDice(values[0],values[1]);
    }

    //makes an animation of player's piece moving
    public void movePlayer(String name, int balance, int from, int to) {
        GUI_Player choosenPlayer = searchGUIPlayer(name);
        //move player one square forward at a time
        for (int i = from; i != to; i = (i + 1) %40) {
            sleep(300);
            fields[i].setCar(choosenPlayer, false);
            if (i == 39) {
                fields[0].setCar(choosenPlayer, true);

            } else if (i == 0) {
                fields[1].setCar(choosenPlayer, true);
                updateBalance(name, balance);
            } else {
                fields[i + 1].setCar(choosenPlayer, true);
             }
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
            System.out.println(i);
            fields[i].setCar(choosenPlayer, false);
            if (i == 0) {
                fields[39].setCar(choosenPlayer, true);
            } else {
                fields[i - 1].setCar(choosenPlayer, true);
            }
        }
    }

    public void updateBalance(String name, int balance) {
        GUI_Player choosenPlayer = searchGUIPlayer(name);
        choosenPlayer.setBalance(balance);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

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
        Color carColor = null;

        for(GUI_Player p: gui_players){
            if(p.getName().equals(name)){
                carColor = p.getCar().getPrimaryColor();
            }
        }

        if (fields[pos] instanceof GUI_Street)
            ((GUI_Street) fields[pos]).setBorder(carColor);
        if (fields[pos] instanceof GUI_Shipping)
            ((GUI_Shipping) fields[pos]).setBorder(carColor);
        if (fields[pos] instanceof GUI_Brewery)
            ((GUI_Brewery) fields[pos]).setBorder(carColor);
    }

    public void mortgageProperty(String name, int pos) {
        // finds player with the name and the players piece color
        Color carColor = null;

        for(GUI_Player p: gui_players){
            if(p.getName().equals(name)){
                carColor = p.getCar().getPrimaryColor();
            }
        }

        if (fields[pos] instanceof GUI_Street)
            ((GUI_Street) fields[pos]).setBorder(carColor, Color.black);
        if (fields[pos] instanceof GUI_Shipping)
            ((GUI_Shipping) fields[pos]).setBorder(carColor, Color.black);
        if (fields[pos] instanceof GUI_Brewery)
            ((GUI_Brewery) fields[pos]).setBorder(carColor, Color.black);
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
        String buttonPressed = gui.getUserButtonPressed(msg, buttons);
        return buttonPressed;
    }
    public int getUserInteger(String msg, int min, int max){
        int userNumber = gui.getUserInteger(msg,min,max);
        return userNumber;
    }

    public void removeLoser(String name, int position){
        GUI_Player choosenPlayer = null;
        for (GUI_Player p: gui_players) {
            if (p.getName().equals(name)){
                choosenPlayer = p;
            }
        }
        fields[position].setCar(choosenPlayer, false);
    }
}

package Controller;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIController {

    private GUI_Field[] fields = {
            new GUI_Start("START", "Få 4000kr", "De modtager 4000 kr. hver gang de passere START.",  Color.RED, Color.WHITE),
            new GUI_Street("Rødovrevej",  "Pris: 1.200 kr","Leje af grund: _______ 50 kr. m/ 1 hus: __________ 250 kr. > 2 huse: __________ 750 kr. > 3 huse: _________ 2.250 kr. > 4 huse: _________ 4.000 kr. > hotel: __________ 6.000 kr. Hvert hus koster 1000 kr.","Pris: 1.200 kr",
                    new Color(72, 139, 247), Color.white),
            new GUI_Chance(),
            new GUI_Street("Hvidovrevej",  "Pris: 1.200 kr","Leje af grund: _______ 50 kr. m/ 1 hus: __________ 250 kr. > 2 huse: __________ 750 kr. > 3 huse: _________ 2.250 kr. > 4 huse: _________ 4.000 kr. > hotel: __________ 6.000 kr. Hvert hus koster 1000 kr.","Pris: 1.200 kr",
                    new Color(72, 139, 247), Color.white),
            new GUI_Tax("", "Betal indkomstskat: 10% eller 4000 kr.", "Betal indkomstskat: 10% eller 4000 kr.", Color.orange, Color.white),
            new GUI_Shipping("src/main/rescources/pictures/shiplogo3.jpg", "Scandlines", "Pris: 4.000 kr", "Leje: _____________ 500 kr. Hvis 2 rederier ejes: 1.000 kr. Hvis 3 rederier ejes: 2.000 kr. Hvis 4 rederier ejes: 4.000 kr.", "Pris: 4.000kr", Color.white, Color.black),
            new GUI_Street("Roskildevej", "Pris: 2.000 kr", "Leje af grund: ______ 100 kr." +
                    " m/ 1 hus: __________ 600 kr. > 2 huse: _________ 1.800 kr. > 3 huse: _________ 5.400 kr. > 4 huse: _________ 8.000 kr. > hotel: _________ 11.000 kr." +
                    " Hvert hus koster 1000 kr.", "Pris: 2.000 kr", new Color(242, 125, 41), Color.white),
            new GUI_Chance(),
            new GUI_Street("Valby Langgade", "Pris: 2.000 kr", "Leje af grund: ______ 100 kr." +
                    " m/ 1 hus: __________ 600 kr. > 2 huse: _________ 1.800 kr. > 3 huse: _________ 5.400 kr. > 4 huse: _________ 8.000 kr. > hotel: _________ 11.000 kr." +
                    " Hvert hus koster 1000 kr.", "Pris: 2.000 kr", new Color(242, 125, 41), Color.white),
            new GUI_Street("Allégade", "Pris: 2.400 kr", "Leje af grund: ______ 150 kr." +
                    " m/ 1 hus: __________ 800 kr. > 2 huse: _________ 2.000 kr. > 3 huse: _________ 6.000 kr. > 4 huse: _________ 9.000 kr. > hotel: _________ 12.000 kr." +
                    " Hvert hus koster 1000 kr.", "Pris: 2.400 kr", new Color(242, 125, 41), Color.white),
            new GUI_Refuge("src/main/rescources/pictures/prison.jpg", "På besøg", "", "På besøg", Color.WHITE, Color.BLACK),
            new GUI_Street("Frederiksberg Allé", "Pris: 2.800kr", "Leje af grund: ______ 200 kr." +
                    " m/ 1 hus: _________ 1.000 kr. > 2 huse: _________ 3.000 kr. > 3 huse: _________ 9.000 kr. > 4 huse: ________ 12.500 kr. > hotel: _________ 15.000 kr." +
                    " Hvert hus koster 2.000 kr.", "Pris: 2.800kr", new Color(41, 242, 58), Color.white),
            new GUI_Brewery("src/main/rescources/pictures/redbull.jpg", "Red Bull", "", "Hvis 1 virksomhed ejes. betales 100 gange så meget, som øjene viser. Hvis både Red Bull og Monster ejes, betales der dobbelt så meget.", "", Color.cyan, Color.BLACK),
            new GUI_Street("Bülowsvej", "Pris: 2.800kr", "Leje af grund: ______ 200 kr." +
                    " m/ 1 hus: _________ 1.000 kr. > 2 huse: _________ 3.000 kr. > 3 huse: _________ 9.000 kr. > 4 huse: ________ 12.500 kr. > hotel: _________ 15.000 kr." +
                    " Hvert hus koster 2.000 kr.", "Pris: 2.800 kr", new Color(41, 242, 58), Color.white),
            new GUI_Street("Gammel Kongevej", "Pris: 3.200 kr", "Leje af grund: ______ 250 kr." +
                    " m/ 1 hus: _________ 1.250 kr. > 2 huse: _________ 3.750 kr. > 3 huse: ________ 10.000 kr. > 4 huse: ________ 14.000 kr. > hotel: _________ 18.000 kr." +
                    " Hvert hus koster 2.000 kr.", "Pris: 3.200 kr", new Color(41, 242, 58), Color.white),
            new GUI_Shipping("src/main/rescources/pictures/shiplogo3.jpg", "Molslinjen", "Pris: 4.000 kr", "Leje: _____________ 500 kr. " +
                    "Hvis 2 rederier ejes: 1.000 kr. Hvis 3 rederier ejes: 2.000 kr. Hvis 4 rederier ejes: 4.000 kr.", "Pris: 4.000kr", Color.white, Color.black),
            new GUI_Street("Bernstorffsvej", "Pris: 3.600 kr", "Leje af grund: ______ 300 kr." +
                    " m/ 1 hus: _________ 1.400 kr. > 2 huse: _________ 4.000 kr. > 3 huse: ________ 11.000 kr. > 4 huse: ________ 15.000 kr. > hotel: _________ 19.000 kr." +
                    " Hvert hus koster 2.000 kr.", "Pris: 3.600 kr", new Color(162, 163, 162), Color.white),
            new GUI_Chance(),
            new GUI_Street("Hellerupvej", "Pris: 3.600 kr", "Leje af grund: ______ 300 kr." +
                    " m/ 1 hus: _________ 1.400 kr. > 2 huse: _________ 4.000 kr. > 3 huse: ________ 11.000 kr. > 4 huse: ________ 15.000 kr. > hotel: _________ 19.000 kr." +
                    " Hvert hus koster 2.000 kr.", "Pris: 3.600 kr", new Color(162, 163, 162), Color.white),
            new GUI_Street("Strandvejen", "Pris: 4.000 kr", "Leje af grund: ______ 350 kr." +
                    " m/ 1 hus: _________ 1.600 kr. > 2 huse: _________ 4.400 kr. > 3 huse: ________ 12.000 kr. > 4 huse: ________ 16.000 kr. > hotel: _________ 20.000 kr." +
                    " Hvert hus koster 2.000 kr.", "Pris: 4.000 kr", new Color(162, 163, 162), Color.white),
            new GUI_Refuge("src/main/rescources/pictures/kebab.jpg", "Gratis kebab", "", "NamNam", Color.BLACK, Color.white),
            new GUI_Street("Trianglen", "Pris: 4.400 kr", "Leje af grund: ______ 350 kr." +
                    " m/ 1 hus: _________ 1.800 kr. > 2 huse: _________ 5.000 kr. > 3 huse: ________ 14.000 kr. > 4 huse: ________ 17.500 kr. > hotel: _________ 21.000 kr." +
                    " Hvert hus koster 3.000 kr.", "Pris: 4.400 kr", Color.RED, Color.white),
            new GUI_Chance(),
            new GUI_Street("Østerbrogade", "Pris: 4.400 kr", "Leje af grund: ______ 350 kr." +
                    " m/ 1 hus: _________ 1.800 kr. > 2 huse: _________ 5.000 kr. > 3 huse: ________ 14.000 kr. > 4 huse: ________ 17.500 kr. > hotel: _________ 21.000 kr." +
                    " Hvert hus koster 3.000 kr.", "Pris: 4.400 kr", Color.RED, Color.white),
            new GUI_Street("Grønningen", "Pris: 4.800 kr", "Leje af grund: ______ 400 kr." +
                    " m/ 1 hus: _________ 2.000 kr. > 2 huse: _________ 6.000 kr. > 3 huse: ________ 15.000 kr. > 4 huse: ________ 18.500 kr. > hotel: _________ 22.000 kr." +
                    " Hvert hus koster 3.000 kr.", "Pris: 4.800 kr", Color.RED, Color.white),
            new GUI_Shipping("src/main/rescources/pictures/shiplogo3.jpg", "Mærsk", "Pris: 4.000 kr", "Leje: _____________ 500 kr. " +
                    "Hvis 2 rederier ejes: 1.000 kr. Hvis 3 rederier ejes: 2.000 kr. Hvis 4 rederier ejes: 4.000 kr.", "Pris: 4.000kr", Color.white, Color.black),
            new GUI_Street("Bredgade", "Pris: 5.200 kr", "Leje af grund: ______ 450 kr." +
                    " m/ 1 hus: _________ 2.200 kr. > 2 huse: _________ 6.600 kr. > 3 huse: ________ 16.000 kr. > 4 huse: ________ 19.500 kr. > hotel: _________ 23.000 kr." +
                    " Hvert hus koster 3.000 kr.", "Pris: 5.200 kr", Color.WHITE, Color.black),
            new GUI_Street("Kgs. Nytorv", "Pris: 5.200 kr", "Leje af grund: ______ 450 kr." +
                    " m/ 1 hus: _________ 2.200 kr. > 2 huse: _________ 6.600 kr. > 3 huse: ________ 16.000 kr. > 4 huse: ________ 19.500 kr. > hotel: _________ 23.000 kr." +
                    " Hvert hus koster 3.000 kr.", "Pris: 5.200 kr", Color.WHITE, Color.black),
            new GUI_Brewery("src/main/rescources/pictures/Monster-Large.jpg", "Monster", "", "Hvis 1 virksomhed ejes. betales 100 gange så meget, som øjene viser. Hvis både Red Bull og Monster ejes, betales der dobbelt så meget.", "", Color.cyan, Color.BLACK),
            new GUI_Street("Østergade", "Pris: 5.600 kr", "Leje af grund: ______ 500 kr." +
                    " m/ 1 hus: _________ 2.400 kr. > 2 huse: _________ 7.200 kr. > 3 huse: ________ 17.000 kr. > 4 huse: ________ 20.500 kr. > hotel: _________ 24.000 kr." +
                    " Hvert hus koster 3.000 kr.", "Pris: 5.600 kr", Color.WHITE, Color.black),
            new GUI_Jail("src/main/rescources/pictures/burglar.jpg", "Gå i fængsel", "", "Gå i fængsel", Color.black, Color.WHITE),
            new GUI_Street("Amagertorv", "Pris: 6.000 kr", "Leje af grund: ______ 550 kr." +
                    " m/ 1 hus: _________ 2.600 kr. > 2 huse: _________ 7.800 kr. > 3 huse: ________ 18.000 kr. > 4 huse: ________ 22.000 kr. > hotel: _________ 25.000 kr." +
                    " Hvert hus koster 4.000 kr.", "Pris: 6.000 kr", new Color(255, 251, 13), Color.white),
            new GUI_Street("Vimmelskaftet", "Pris: 6.000 kr", "Leje af grund: ______ 550 kr." +
                    " m/ 1 hus: _________ 2.600 kr. > 2 huse: _________ 7.800 kr. > 3 huse: ________ 18.000 kr. > 4 huse: ________ 22.000 kr. > hotel: _________ 25.000 kr." +
                    " Hvert hus koster 4.000 kr.", "Pris: 6.000 kr", new Color(255, 251, 13), Color.white),
            new GUI_Chance(),
            new GUI_Street("Nygade", "Pris: 6.400 kr", "Leje af grund: ______ 600 kr." +
                    " m/ 1 hus: _________ 3.000 kr. > 2 huse: _________ 9.000 kr. > 3 huse: ________ 20.000 kr. > 4 huse: ________ 24.000 kr. > hotel: _________ 28.000 kr." +
                    " Hvert hus koster 4.000 kr.", "Pris: 6.400 kr", new Color(255, 251, 13), Color.white),
            new GUI_Shipping("src/main/rescources/pictures/shiplogo3.jpg", "DFDS", "Pris: 4.000 kr", "Leje: _____________ 500 kr. " +
                    "Hvis 2 rederier ejes: 1.000 kr. Hvis 3 rederier ejes: 2.000 kr. Hvis 4 rederier ejes: 4.000 kr.", "Pris: 4.000kr", Color.white, Color.black),
            new GUI_Chance(),
            new GUI_Street("Frederiksberggade", "Pris: 7.000 kr", "Leje af grund: ______ 700 kr." +
                    " m/ 1 hus: _________ 3.500 kr. > 2 huse: ________ 10.000 kr. > 3 huse: ________ 22.000 kr. > 4 huse: ________ 26.000 kr. > hotel: _________ 30.000 kr." +
                    " Hvert hus koster 4.000 kr.", "Pris: 7.000 kr", new Color(165, 6, 209), Color.white),
            new GUI_Tax("", "Betal ekstraordinær statskat: 2000 kr.", "Betal ekstraordinær statskat: 2000 kr.", Color.orange, Color.white),
            new GUI_Street("Rådhuspladsen", "Pris: 8.000 kr", "Leje af grund: ______ 1.000 kr." +
                    " m/ 1 hus: _________ 4.000 kr. > 2 huse: ________ 12.000 kr. > 3 huse: ________ 28.000 kr. > 4 huse: ________ 34.000 kr. > hotel: _________ 40.000 kr." +
                    " Hvert hus koster 4.000 kr.", "Pris: 8.000 kr", new Color(165, 6, 209), Color.white)
    };
    private GUI gui = new GUI(fields);
    private GUI_Player[] gui_players;
    private GUI_Car[] gui_cars = {
            new GUI_Car(Color.BLACK, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.CYAN, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.red, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.yellow, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.green, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL),
            new GUI_Car(Color.white, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL)
    };





    public String[] startMenu() {
        int amountOfPlayers = gui.getUserInteger("How many players?", 3 ,6);

        String[] names = new String[amountOfPlayers];
        gui_players = new GUI_Player[amountOfPlayers];

        for (int i = 0; i < amountOfPlayers; i++) {
            names[i] = gui.getUserString("Choose name: ");
            // generating piece for each player on gui board
            gui_players[i] = new GUI_Player(names[i], 30000, gui_cars[i]);
            gui.addPlayer(gui_players[i]);
            fields[0].setCar(gui_players[i], true);
        }

        return names;
    }

    public void showDice(int die1, int die2) {
        if (die1 >= 1 && die2 >= 1 && die1 <= 6 && die2 <= 6){
            gui.setDice(die1,die2);
        }
    }

    //makes an animation of player's piece moving
    public void movePlayer(String name, int balance, int from, int to) {
        //finds the player with that name
        GUI_Player choosenPlayer = null;
        for (GUI_Player p: gui_players) {
            if (p.getName().equals(name)){
                choosenPlayer = p;
            }
        }
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

    public void updateBalance(String name, int balance) {
        //finds the player with that name
        GUI_Player choosenPlayer = null;
        for (GUI_Player p: gui_players) {
            if (p.getName().equals(name)){
                choosenPlayer = p;
            }
        }

        choosenPlayer.setBalance(balance);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }


    public void buyStreet(String name, int pos){
        // finds player with the name and the players piece color
        Color carColor = null;

        for(GUI_Player p: gui_players){
            if(p.getName().equals(name)){
                carColor = p.getCar().getPrimaryColor();
            }
        }

        ((GUI_Street) fields[pos]).setBorder(carColor);
    }

    public void button(String msg, String ... buttons) {
        gui.getUserButtonPressed(msg, buttons);
    }

}

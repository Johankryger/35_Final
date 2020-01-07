package Controller;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIController {

    private GUI_Field[] fields = {
            new GUI_Street(),new GUI_Street(),new GUI_Street(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),
            new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),
            new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),
            new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),
            new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),
            new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),
            new GUI_Chance(),new GUI_Chance(),new GUI_Chance(),new GUI_Chance()
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

    public void showDice() {
        gui.setDice(6,6);
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
        //move player one square forthward at a time
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



}

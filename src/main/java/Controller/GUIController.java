package Controller;

import gui_main.GUI;

public class GUIController {
    private GUI gui = new GUI();

    public void showDice() {
        gui.setDice(6,6);
    }
}

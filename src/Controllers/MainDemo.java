package Controllers;

import InterfaceGUI.WelcomeScreenGUI;

public class MainDemo {

    /**
     * Shows the welcome screen.
     *
     * @param args opens the GUI
     */
    public static void main(String[] args) {

        Reader.read();
        new WelcomeScreenGUI().setVisible(true);
    }
}

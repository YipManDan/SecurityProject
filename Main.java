package project.security;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Clock();
        new BuildingList();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame ex = new MainFrame();
                ex.setVisible(true);
            }
        });
    }
}

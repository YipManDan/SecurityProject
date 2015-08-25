package project.security;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MainFrame extends JFrame {

    JPanel buttonPanel, cardPanel;
    JPanel setupPane, passPane;
    JTabbedPane tabbedPane;

    public MainFrame() {
        setTitle("Project");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        setupPane = new SetupCard();
        setupPane.setBackground(Color.GRAY);

        passPane = new JPanel();
        passPane.setBackground(Color.blue);

        tabbedPane.addTab("Setup", setupPane);
        tabbedPane.addTab("Schedule", passPane);


        this.add(tabbedPane);
        this.setVisible(true);
    }
    ///////////////////////////////////////////////////
    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Selected: " + e.getActionCommand());
            //selectedFoods += "-"+e.getActionCommand();
            //System.out.println(selectedFoods);

        }
    }
    ////////////////////////////////////////////////



}

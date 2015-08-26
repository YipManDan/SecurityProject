package project.security;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MainFrame extends JFrame {

    JPanel buttonPanel, cardPanel;
    JPanel setupPane, passPane, logPane;
    JTabbedPane tabbedPane;

    public MainFrame() {
        setTitle("Home Security System");
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1320, 990));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        setupPane = new SetupCard();
        setupPane.setBackground(Color.GRAY);

        passPane = new SchedulePane();

        logPane = new LogPane();

        tabbedPane.addTab("Setup", setupPane);
        tabbedPane.addTab("Schedule", passPane);
        tabbedPane.addTab("Log", logPane);


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

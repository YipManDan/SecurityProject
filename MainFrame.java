package project.security;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame {

    JPanel buttonPanel, cardPanel;
    JPanel setupPane, passPane, logPane, billPane;
    JPanel registerPanel;
    JTabbedPane tabbedPane;

    public MainFrame() {
        setTitle("Home Security System");
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setMinimumSize(new Dimension(1320, 990));
        this.setMinimumSize(new Dimension(1200, 900));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        registerPanel = new RegisterPane(new MenuActionListener());
        this.add(registerPanel);



        tabbedPane = new JTabbedPane();

        setupPane = new SetupCard();
        setupPane.setBackground(Color.GRAY);

        passPane = new SchedulePane();
        logPane = new LogPane();
        billPane = new BillCard();

        tabbedPane.addTab("Setup", setupPane);
        tabbedPane.addTab("Bill", billPane);
        tabbedPane.addTab("Schedule", passPane);
        tabbedPane.addTab("Log", logPane);


        //this.add(tabbedPane);
        this.setVisible(true);
    }
    private void saveRegistration() {
        File toFile = new File("testfile.txt");
        try {
            //write("Hello World again", toFile);
            RegisterPane.saveText(toFile);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        this.remove(registerPanel);
        this.add(tabbedPane);
        this.setVisible(true);
    }
    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "save") {
                saveRegistration();

            }
        }
    }




}

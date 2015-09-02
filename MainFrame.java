package project.security;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame {

    JPanel buttonPanel, cardPanel;
    JPanel setupPane, schedulePane, logPane, billPane, demoPane;
    JPanel registerPanel;
    JTabbedPane tabbedPane;
    JScrollPane logPaneScroll;

    public MainFrame() {
        setTitle("Home Security System");
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setMinimumSize(new Dimension(1320, 990));
        //this.setMinimumSize(new Dimension(1200, 900));
        this.setMinimumSize(new Dimension(1200, 870));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        registerPanel = new RegisterPane(new MenuActionListener());


        //this.add(registerPanel);
        getContentPane().add(registerPanel);


        tabbedPane = new JTabbedPane();
        setupPane = new SetupCard();
        setupPane.setBackground(Color.GRAY);
        schedulePane = new SchedulePane();
        logPane = new LogPane();
        logPaneScroll = new JScrollPane(logPane);
        billPane = new BillCard();
        demoPane = new DemoPane();

        tabbedPane.addTab("Setup", setupPane);
        tabbedPane.addTab("Bill", billPane);
        tabbedPane.addTab("Schedule", schedulePane);
        //tabbedPane.addTab("Log", logPane);
        tabbedPane.addTab("Log", logPaneScroll);
        tabbedPane.addTab("Demo", demoPane);

        //this.add(tabbedPane);
        this.setVisible(true);
    }
    private void saveRegistration() {
        File toFile = new File("UserFile.txt");
        try {
            if (!toFile.exists()) {
                toFile.createNewFile();
            }
        } catch (IOException e) {
            JFrame errorFrame = new JFrame("");
            JPanel errorPanel = new JPanel();
            errorFrame.getContentPane().add(errorPanel);
            errorPanel.add(new JLabel("File not found and file unable to be created. Please create file: UserFile.txt in Jar location."));
            errorFrame.setVisible(true);
        }
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

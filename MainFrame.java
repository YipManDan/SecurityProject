package project.security;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MainFrame extends JFrame {

    JButton setupBtn, scheduleBtn, billBtn, demoBtn;
    CardLayout cards;
    JPanel buttonPanel, cardPanel;
    JPanel setupCard, passCard;
    JTabbedPane tabbedPane;

    public MainFrame() {
        setTitle("Project");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupBtn = new JButton("Setup");
        scheduleBtn = new JButton("Schedule");

        buttonPanel= new JPanel(new FlowLayout());
        cardPanel = new JPanel();

        cards = new CardLayout();
        cardPanel.setLayout(cards);
        cards.show(cardPanel, "Setup");

        setupCard = new JPanel();
        //setupCard - new SetupCard(Color.WHITE);
        setupCard.setBackground(Color.GRAY);

        passCard = new JPanel();
        //passCard = new PassCard(Color.WHITE);
        passCard.setBackground(Color.blue);

        cardPanel.add(setupCard, "Setup");
        cardPanel.add(passCard, "Schedule");

        //buttonPanel.setBorder(outline);
        JButton switchCardsBtn = new JButton("Switch Card");
        switchCardsBtn.setActionCommand("Switch Card");
        switchCardsBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //navigate to the next component
                cards.next(cardPanel);
            }
        });

        buttonPanel.add(switchCardsBtn);


        this.add(buttonPanel,BorderLayout.NORTH);
        this.add(cardPanel,BorderLayout.CENTER);
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

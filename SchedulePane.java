package project.security;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 8/25/2015.
 */
public class SchedulePane extends JPanel{
    MainSystem system;
    private JPanel passCard, scheduleCard;
    private JPanel cardPanel;
    private CardLayout cards;
    JFormattedTextField passTF;
    JButton enter;

    public SchedulePane() {
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);
        passCard = new JPanel(new FlowLayout());

        scheduleCard = new JPanel();
        scheduleCard.setBackground(Color.PINK);

        passTF = new JFormattedTextField(createFormatter("####"));
        passTF.setPreferredSize(new Dimension(100, 30));
        enter = new JButton("Enter");
        enter.setActionCommand("enter");
        enter.addActionListener(new PassHandler());


        passCard.add(passTF);
        passCard.add(enter);

        cardPanel.add(passCard, "password");
        cardPanel.add(scheduleCard, "schedule");

        this.add(cardPanel);


    }

    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        }   catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    private class PassHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputPass = passTF.getText();
            if(BuildingList.getBuilding(0).getSystemPass().equals(inputPass))
                cards.next(cardPanel);
        }
    }
}

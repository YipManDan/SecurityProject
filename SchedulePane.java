package project.security;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Daniel on 8/25/2015.
 */
public class SchedulePane extends JPanel{
    MainSystem system;
    private JPanel bottomPanel;
    private JPanel passCard, scheduleCard;
    private JPanel sensorCard, optionCard;
    private JPanel cardPanel, cardPanel2;
    private CardLayout cards, cards2;
    JFormattedTextField passTF;
    JTextField roomID, results;
    JButton enter, enterID;

    JLabel numRooms;

    public SchedulePane() {
        //roomID = new JFormattedTextField(createFormatter("##"));
        //results = new JTextField("Null");
        //results.setPreferredSize(new Dimension(100, 30));
        roomID = new JTextField();
        roomID.setPreferredSize(new Dimension(100, 30));
        enterID = new JButton("Enter");
        enterID.setActionCommand("enter");
        enterID.addActionListener(new ButtonHandler());
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(roomID);
        bottomPanel.add(enterID);
        //bottomPanel.add(results);

        numRooms = new JLabel("Number of rooms: " + String.valueOf(BuildingList.getBuilding(0).numSubAreas()));
        bottomPanel.add(numRooms);


        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);
        //this.setLayout(cards);
        cards2 = new CardLayout();
        cardPanel2 = new JPanel();
        cardPanel2.setLayout(cards2);

        passCard = new JPanel(new FlowLayout());

        scheduleCard = new JPanel(new BorderLayout());
        scheduleCard.add(bottomPanel, BorderLayout.SOUTH);

        //sensorCard = new JPanel(new BoxLayout(sensorCard, BoxLayout.Y_AXIS));
        sensorCard = new JPanel(new GridLayout(0,1));
        sensorCard.setBackground(Color.PINK);
        sensorCard.add(new JLabel("Hello World"));
        optionCard = new JPanel();
        optionCard.setBackground(Color.CYAN);

        passTF = new JFormattedTextField(createFormatter("####"));
        passTF.setPreferredSize(new Dimension(100, 30));
        enter = new JButton("Enter");
        enter.setActionCommand("enter");
        enter.addActionListener(new PassHandler());


        URL url = BuildingList.getBuilding(0).getImage();
        JLabel homeLabel = new JLabel(new ImageIcon(url));
        scheduleCard.add(homeLabel, BorderLayout.CENTER);


        passCard.add(passTF);
        passCard.add(enter);

        cardPanel.add(passCard, "password");
        //this.add(passCard, "password");
        cardPanel.add(scheduleCard, "schedule");
        //this.add(scheduleCard, "schedule");
        cardPanel2.add(sensorCard, "sensors");
        cardPanel2.add(optionCard, "options");

        this.add(cardPanel);
        scheduleCard.add(cardPanel2, BorderLayout.LINE_START);
        //this.add(cardPanel2, BorderLayout.EAST);


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
                //cards.next(this);

        }
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input= passTF.getText();
            int room = 0;
            try {
                room = Integer.parseInt(roomID.getText());
            } catch(NumberFormatException exc)
            {
                System.out.println("Exception: " + exc);
            }
            SubAreas temp = BuildingList.getBuilding(0).getSubArea(room);
            if(temp.hasFireSensor()) {
                JLabel fireSensor = new JLabel("Has a firesensor");
                sensorCard.add(fireSensor);
            } else {
                JLabel fireSensor = new JLabel("Has no firesensor");
                sensorCard.add(fireSensor);
            }
            if(temp.hasMotionSensor()) {
                JLabel motionSensor = new JLabel("Has a motionsensor");
                sensorCard.add(motionSensor);
            } else {
                JLabel motionSensor = new JLabel("Has no motionsensor");
                sensorCard.add(motionSensor);
            }
            sensorCard.updateUI();



        }
    }
}

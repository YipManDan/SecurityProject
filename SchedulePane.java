package project.security;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.Border;
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
    private JPanel sensorCard, optionCard, sensors;
    private JPanel  cardPanel;
    private CardLayout  cards;
    JFormattedTextField passTF;
    JTextField roomID, results;
    JButton enter, enterID;

    JLabel numRooms;

    public SchedulePane() {
        setLayout(new BorderLayout());
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
        cardPanel.setPreferredSize(new Dimension(200, 100));

        passCard = new JPanel(new FlowLayout());

        scheduleCard = new JPanel(new BorderLayout());
        scheduleCard.add(bottomPanel, BorderLayout.SOUTH);

        //sensorCard = new JPanel(new BoxLayout(sensorCard, BoxLayout.Y_AXIS));
        sensors = new JPanel(new GridLayout(0,1,10,10));
        sensors.setPreferredSize(new Dimension(175, 300));
        //sensorCard = new JPanel(new BoxLayout(sensorCard, BoxLayout.Y_AXIS));
        sensors.setBackground(Color.PINK);
        sensors.add(new JLabel("   Hello World"));

        sensorCard = new JPanel(new BorderLayout());
        sensorCard.setBackground(Color.BLACK);
        sensorCard.add(sensors, BorderLayout.CENTER);
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


        this.add(passCard);
        cardPanel.add(sensorCard, "sensors");
        cardPanel.add(optionCard, "options");

        scheduleCard.add(cardPanel, BorderLayout.LINE_START);


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
                validPassWord();
                //cards.next(cardPanel);
                //cards.next(this);

        }
    }
    private void validPassWord() {
        this.remove(passCard);
        this.add(scheduleCard);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input= roomID.getText();
            int room = 0;
            try {
                room = Integer.parseInt(roomID.getText());
            } catch(NumberFormatException exc)
            {
                System.out.println("Exception: " + exc);
            }
            sensors.removeAll();
            sensors.add(new JLabel("Subarea: " + input));
            SubAreas temp = BuildingList.getBuilding(0).getSubArea(room);
            if(temp.hasFireSensor()) {
                JLabel fireSensor = new JLabel("Has a firesensor");
                sensors.add(fireSensor);
            } else {
                JLabel fireSensor = new JLabel("Has no firesensor");
                sensors.add(fireSensor);
            }
            if(temp.hasMotionSensor()) {
                JLabel motionSensor = new JLabel("Has a motionsensor");
                sensors.add(motionSensor);
            } else {
                JLabel motionSensor = new JLabel("Has no motionsensor");
                sensors.add(motionSensor);
            }
            sensorCard.updateUI();



        }
    }
}

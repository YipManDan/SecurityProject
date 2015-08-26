package project.security;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.Map;

/**
 * Created by Daniel on 8/25/2015.
 */
public class SchedulePane extends JPanel{
    MainSystem system;
    private JPanel bottomPanel;
    private JPanel passCard, scheduleCard;
    private JPanel sensorCard, optionCard, sensors;
    private JPanel  cardPanel, right;
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
        right = new JPanel();
        right.setPreferredSize(new Dimension(200,100));

        passCard = new JPanel(new FlowLayout());

        scheduleCard = new JPanel(new BorderLayout());
        scheduleCard.add(bottomPanel, BorderLayout.SOUTH);

        sensors = new JPanel();
        sensors.setLayout(new BoxLayout(sensors, BoxLayout.Y_AXIS));
        //sensors.setBackground(Color.PINK);
        sensors.add(new JLabel("   Hello World"));

        //sensorCard = new JPanel(new BorderLayout());
        //sensorCard.add(sensors, BorderLayout.CENTER);
        sensorCard = new JPanel();
        sensorCard.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        sensorCard.add(sensors, gbc);
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
        scheduleCard.add(right, BorderLayout.LINE_END);


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
    private void checkForSensor() {
        String input = roomID.getText();
        int room = 0;
        try {
            room = Integer.parseInt(roomID.getText());
        } catch (NumberFormatException exc) {
            System.out.println("Exception: " + exc);
        }
        sensors.removeAll();
        JLabel roomLbl = new JLabel("Subarea: " + input);
        roomLbl.setFont(new Font("Arial", Font.BOLD, 20));
        Font font = roomLbl.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        roomLbl.setFont(font.deriveFont(attributes));

        sensors.add(roomLbl);
        sensors.add(Box.createRigidArea(new Dimension(0, 5)));
        SubAreas temp = BuildingList.getBuilding(0).getSubArea(room);

        if (temp.hasFireSensor()) {
            JButton fireSensorBtn;
            fireSensorBtn = new JButton("Fire Sensor");
            //fireSensorBtn.setPreferredSize(new Dimension(200, 26));
            fireSensorBtn.setMaximumSize(new Dimension(120, 26));
            sensors.add(fireSensorBtn);
        }
        sensors.add(Box.createRigidArea(new Dimension(90, 4)));
        if (temp.hasMotionSensor()) {
            JButton motionSensorBtn;
            motionSensorBtn = new JButton("Motion Sensor");
            //motionSensorBtn.setPreferredSize(new Dimension(200, 26));
            motionSensorBtn.setMaximumSize(new Dimension(120, 26));
            sensors.add(motionSensorBtn);
        }
        else if (!temp.hasMotionSensor() && !temp.hasFireSensor()) {
            JLabel none = new JLabel("There are no sensors");
            none.setFont(new Font("Arial", Font.PLAIN, 14));
            sensors.add(none);
        }
        sensorCard.updateUI();


    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "enter") {
                checkForSensor();
            }
        }
    }
}

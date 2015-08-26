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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private JFormattedTextField passTF;
    private JTextField roomID, results;
    private JButton enter, enterID, showAll;
    private GridBagConstraints c = new GridBagConstraints();
    private DateFormat format;

    JLabel numRooms;

    public SchedulePane() {
        setLayout(new BorderLayout());
        format = new SimpleDateFormat("hh:mm");

        roomID = new JTextField();
        roomID.setPreferredSize(new Dimension(100, 30));
        enterID = new JButton("Enter");
        enterID.setActionCommand("enter");
        enterID.addActionListener(new ButtonHandler());
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(roomID);
        bottomPanel.add(enterID);
        //bottomPanel.add(results);

        showAll = new JButton("Show All");
        showAll.setActionCommand("all");
        showAll.addActionListener(new ButtonHandler());

        numRooms = new JLabel("Number of rooms: " + String.valueOf(BuildingList.getBuilding(0).numSubAreas()));
        bottomPanel.add(numRooms);

        bottomPanel.add(showAll);

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
            fireSensorBtn.setMinimumSize(new Dimension(120, 26));
            fireSensorBtn.setMaximumSize(new Dimension(120, 26));
            fireSensorBtn.setActionCommand("fire");
            fireSensorBtn.addActionListener(new ButtonHandler());
            sensors.add(fireSensorBtn);
            sensors.add(Box.createRigidArea(new Dimension(90, 4)));
        }
        if (temp.hasMotionSensor()) {
            JButton motionSensorBtn;
            motionSensorBtn = new JButton("Motion Sensor");
            //motionSensorBtn.setPreferredSize(new Dimension(200, 26));
            motionSensorBtn.setMaximumSize(new Dimension(120, 26));
            motionSensorBtn.setActionCommand("motion");
            motionSensorBtn.addActionListener(new ButtonHandler());
            sensors.add(motionSensorBtn);
        }
        else if (!temp.hasMotionSensor() && !temp.hasFireSensor()) {
            JLabel none = new JLabel("There are no sensors");
            none.setFont(new Font("Arial", Font.PLAIN, 14));
            sensors.add(none);
        }
        sensorCard.updateUI();


    }
    private void showAllSensors() {
        JFrame allSensors = new JFrame();
        allSensors.setPreferredSize(new Dimension(1000, 750));
        allSensors.setMinimumSize(new Dimension(1000, 750));
        allSensors.setTitle("All Sensors");

        //JScrollPane sensorList = new JScrollPane();
        //JPanel sensorList = new JPanel(new GridLayout(8,6));
        JPanel sensorList = new JPanel(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        sensorList.add(new JLabel("   SubArea Id   "), c);
        c.gridx = 1;
        sensorList.add(new JLabel("   Sensor Type   "), c);
        c.gridx++;
        sensorList.add(new JLabel("  Manual On/Off  "), c);
        c.gridx++;
        sensorList.add(new JLabel("   Weekday On   "), c);
        c.gridx++;
        sensorList.add(new JLabel("   Weekday Off   "), c);
        c.gridx++;
        sensorList.add(new JLabel("   Weekend On   "), c);
        c.gridx++;
        sensorList.add(new JLabel("   Weekend Off   "), c);
        c.gridx++;
        sensorList.add(new JLabel("   Vacation On   "), c);
        c.gridx++;
        sensorList.add(new JLabel("   Vacation Off   "), c);

        //sensorList.add(new JLabel("Hello World"), c);
        ArrayList<SubAreas> areaList = BuildingList.getBuilding(0).getSubAreaList();
        ArrayList<Sensor> sensorArray = new ArrayList<>(0);
        int size=0;
        for(SubAreas temp : areaList) {
            if(temp.hasMotionSensor()) {
                size++;
                sensorArray.add(temp.getMotionSensor());
                System.out.println(temp.getSubAreaId() + " has a motion sensor");
            }
            if(temp.hasFireSensor()) {
                ++size;
                sensorArray.add(temp.getFireSensor());
                System.out.println(temp.getSubAreaId() + " has a fire sensor");
            }
        }
        ArrayList<JLabel> sensorIds = new ArrayList<>(size);
        ArrayList<JCheckBox> manualCBs = new ArrayList<>(size);
        ArrayList<JFormattedTextField> weekdayOn = new ArrayList<>(size);
        ArrayList<JFormattedTextField> weekdayOff= new ArrayList<>(size);
        ArrayList<JFormattedTextField> weekendOn = new ArrayList<>(size);
        ArrayList<JFormattedTextField> weekendOff = new ArrayList<>(size);
        ArrayList<JFormattedTextField> vacationOn = new ArrayList<>(size);
        ArrayList<JFormattedTextField> vacationOff = new ArrayList<>(size);
        for(Sensor temp : sensorArray) {
            sensorIds.add(new JLabel("   " + temp.getSensorID() + "   "));
            JCheckBox tempBox = new JCheckBox();
            tempBox.setSelected(temp.getManualOn());
            manualCBs.add(tempBox);
            JFormattedTextField weekdayOnTF = new JFormattedTextField(format);
            weekdayOnTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            weekdayOn.add(weekdayOnTF);
            JFormattedTextField weekdayOffTF = new JFormattedTextField(format);
            weekdayOffTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            weekdayOff.add(weekdayOffTF);
            JFormattedTextField weekendOnTF = new JFormattedTextField(format);
            weekendOnTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            weekendOn.add(weekendOnTF);
            JFormattedTextField weekendOffTF = new JFormattedTextField(format);
            weekendOffTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            weekendOff.add(weekendOffTF);
            JFormattedTextField vacationOnTF = new JFormattedTextField(format);
            vacationOnTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            vacationOn.add(vacationOnTF);
            JFormattedTextField vacationOffTF = new JFormattedTextField(format);
            vacationOffTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            vacationOff.add(vacationOffTF);
        }
        /*
        for(SubAreas temp : areaList) {
            if(temp.hasFireSensor()) {
                Sensor fs = temp.getFireSensor();
                sensorIds.add(new JLabel("   " + fs.getSensorID() + "   "));
                JCheckBox tempBox = new JCheckBox();
                tempBox.setSelected(fs.getManualOn());
                manualCBs.add(tempBox);
                JFormattedTextField weekdayOnTF = new JFormattedTextField(format);
                weekdayOnTF.setText(fs.getOnTime(Schedule.Setting.weekend).toString());
                weekdayOn.add(weekdayOnTF);
                JFormattedTextField weekdayOffTF = new JFormattedTextField(format);
                weekdayOffTF.setText(fs.getOnTime(Schedule.Setting.weekend).toString());
                weekdayOff.add(weekdayOffTF);
                JFormattedTextField weekendOnTF = new JFormattedTextField(format);
                weekendOnTF.setText(fs.getOnTime(Schedule.Setting.weekend).toString());
                weekendOn.add(weekendOnTF);
                JFormattedTextField weekendOffTF = new JFormattedTextField(format);
                weekendOffTF.setText(fs.getOnTime(Schedule.Setting.weekend).toString());
                weekendOff.add(weekendOffTF);
                JFormattedTextField vacationOnTF = new JFormattedTextField(format);
                vacationOnTF.setText(fs.getOnTime(Schedule.Setting.weekend).toString());
                vacationOn.add(vacationOnTF);
                JFormattedTextField vacationOffTF = new JFormattedTextField(format);
                vacationOffTF.setText(fs.getOnTime(Schedule.Setting.weekend).toString());
                vacationOff.add(vacationOffTF);
            }
            if(temp.hasMotionSensor()) {
                Sensor ms = temp.getMotionSensor();
                sensorIds.add(new JLabel("   " + ms.getSensorID() + "   "));
                JCheckBox tempBox = new JCheckBox();
                tempBox.setSelected(ms.getManualOn());
                manualCBs.add(tempBox);
                JFormattedTextField weekdayOnTF = new JFormattedTextField(format);
                weekdayOnTF.setText(ms.getOnTime(Schedule.Setting.weekend).toString());
                weekdayOn.add(weekdayOnTF);
                JFormattedTextField weekdayOffTF = new JFormattedTextField(format);
                weekdayOffTF.setText(ms.getOnTime(Schedule.Setting.weekend).toString());
                weekdayOff.add(weekdayOffTF);
                JFormattedTextField weekendOnTF = new JFormattedTextField(format);
                weekendOnTF.setText(ms.getOnTime(Schedule.Setting.weekend).toString());
                weekendOn.add(weekendOnTF);
                JFormattedTextField weekendOffTF = new JFormattedTextField(format);
                weekendOffTF.setText(ms.getOnTime(Schedule.Setting.weekend).toString());
                weekendOff.add(weekendOffTF);
                JFormattedTextField vacationOnTF = new JFormattedTextField(format);
                vacationOnTF.setText(ms.getOnTime(Schedule.Setting.weekend).toString());
                vacationOn.add(vacationOnTF);
                JFormattedTextField vacationOffTF = new JFormattedTextField(format);
                vacationOffTF.setText(ms.getOnTime(Schedule.Setting.weekend).toString());
                vacationOff.add(vacationOffTF);
            }
        }
        */
        //for(int i=0; i < size; i++) {
        int i = 0;
        for(Sensor temp: sensorArray) {
            c.gridy = i+1;
            c.gridx = 0;
            sensorList.add(new JLabel("   " + temp.getRoomID() + "   "),c);
            c.gridx++;
            String type;
            if(FireSensor.class.equals(temp.getClass())) {
                type = "Fire Sensor";
            } else {
                type = "Motion Sensor";
            }
            sensorList.add(new JLabel("   " + type + "   "),c);

            i++;
        }


        allSensors.add(sensorList);

        allSensors.setVisible(true);

    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "enter") {
                checkForSensor();
            }
            if(e.getActionCommand() == "all") {
                showAllSensors();
            }
            if(e.getActionCommand() == "fire") {
                cards.next(cardPanel);

            }
            if(e.getActionCommand() == "motion") {

            }
            if(e.getActionCommand() == "save") {

            }
            if(e.getActionCommand() == "cancel") {
                cards.next(cardPanel);
            }
        }
    }
}

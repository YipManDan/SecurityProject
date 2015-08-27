package project.security;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Daniel on 8/25/2015.
 */
public class SchedulePane extends JPanel{
    MainSystem system;
    private JFrame allSensors;
    private JPanel bottomPanel;
    private JPanel passPanel, scheduleCard;
    private JPanel sensorCard, optionCard, sensors;
    private JPanel  cardPanel, right;
    private CardLayout  cards;
    private JFormattedTextField passTF;
    private JTextField roomID, results;
    private JButton enter, enterID, showAll, saveBtn;
    private JButton cancelBtn, optionSaveBtn;
    private GridBagConstraints c = new GridBagConstraints();
    private DateFormat format;
    private DateTimeFormatter formatter;

    private ArrayList<Sensor> sensorArray;
    private ArrayList<JCheckBox> manualCBs;
    private ArrayList<JFormattedTextField> weekdayOn;
    private ArrayList<JFormattedTextField> weekdayOff;
    private ArrayList<JFormattedTextField> weekendOn;
    private ArrayList<JFormattedTextField> weekendOff;
    private ArrayList<JFormattedTextField> vacationOn;
    private ArrayList<JFormattedTextField> vacationOff;

    JLabel numRooms;

    public SchedulePane() {
        setLayout(new BorderLayout());
        format = new SimpleDateFormat("HH:mm");
        formatter = DateTimeFormatter.ofPattern("HH:mm");

        saveBtn = new JButton("Save");
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(new ButtonHandler());

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

        passPanel = new JPanel(new FlowLayout());

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
        //optionCard.setLayout(new BoxLayout(optionCard, BoxLayout.Y_AXIS));
        optionCard.setLayout(new GridBagLayout());

        passTF = new JFormattedTextField(createFormatter("####"));
        passTF.setPreferredSize(new Dimension(100, 30));
        enter = new JButton("Enter");
        enter.setActionCommand("enter");
        enter.addActionListener(new PassHandler());


        URL url = BuildingList.getBuilding(0).getImage();
        JLabel homeLabel = new JLabel(new ImageIcon(url));
        scheduleCard.add(homeLabel, BorderLayout.CENTER);


        passPanel.add(passTF);
        passPanel.add(enter);


        this.add(passPanel);
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
        this.remove(passPanel);
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
        allSensors = new JFrame();
        allSensors.setPreferredSize(new Dimension(1000, 750));
        allSensors.setMinimumSize(new Dimension(1000, 750));
        allSensors.setTitle("All Sensors");

        Font font = new Font("Serif", Font.BOLD, 14);
        Map attributes = font.getAttributes();
        font = font.deriveFont(attributes);


        //JScrollPane sensorList = new JScrollPane();
        JPanel sensorList = new JPanel(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel label = new JLabel(" SubArea Id  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx = 1;
        label = new JLabel(" Sensor Type  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Manual On/Off  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Weekday On  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Weekday Off  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Weekend On  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Weekend Off  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Vacation On  ");
        label.setFont(font);
        sensorList.add(label, c);
        c.gridx++;
        label = new JLabel(" Vacation Off  ");
        label.setFont(font);
        sensorList.add(label, c);

        ArrayList<SubAreas> areaList = BuildingList.getBuilding(0).getSubAreaList();
        sensorArray = new ArrayList<>(0);
        int size=0;
        for(SubAreas temp : areaList) {
            if(temp.hasMotionSensor()) {
                size++;
                sensorArray.add(temp.getMotionSensor());
            }
            if(temp.hasFireSensor()) {
                ++size;
                sensorArray.add(temp.getFireSensor());
            }
        }
        manualCBs = new ArrayList<>(size);
        weekdayOn = new ArrayList<>(size);
        weekdayOff= new ArrayList<>(size);
        weekendOn = new ArrayList<>(size);
        weekendOff = new ArrayList<>(size);
        vacationOn = new ArrayList<>(size);
        vacationOff = new ArrayList<>(size);
        for(Sensor temp : sensorArray) {
            JCheckBox tempBox = new JCheckBox();
            tempBox.setSelected(temp.getManualOn());
            tempBox.setToolTipText("State of sensor when in manual mode.");
            manualCBs.add(tempBox);
            JFormattedTextField weekdayOnTF = new JFormattedTextField(format);
            weekdayOnTF.setText(temp.getOnTime(Schedule.Setting.weekday).toString());
            weekdayOn.add(weekdayOnTF);
            JFormattedTextField weekdayOffTF = new JFormattedTextField(format);
            weekdayOffTF.setText(temp.getOffTime(Schedule.Setting.weekday).toString());
            weekdayOff.add(weekdayOffTF);
            JFormattedTextField weekendOnTF = new JFormattedTextField(format);
            weekendOnTF.setText(temp.getOnTime(Schedule.Setting.weekend).toString());
            weekendOn.add(weekendOnTF);
            JFormattedTextField weekendOffTF = new JFormattedTextField(format);
            weekendOffTF.setText(temp.getOffTime(Schedule.Setting.weekend).toString());
            weekendOff.add(weekendOffTF);
            JFormattedTextField vacationOnTF = new JFormattedTextField(format);
            vacationOnTF.setText(temp.getOnTime(Schedule.Setting.vacation).toString());
            vacationOn.add(vacationOnTF);
            JFormattedTextField vacationOffTF = new JFormattedTextField(format);
            vacationOffTF.setText(temp.getOffTime(Schedule.Setting.vacation).toString());
            vacationOff.add(vacationOffTF);
        }
        int i = 0;
        for(Sensor temp: sensorArray) {
            c.gridy = i+1;
            c.gridx = 0;
            sensorList.add(new JLabel("   " + temp.getRoomID() + "   "),c);
            c.gridx++;
            String type;
            if(FireSensor.class.equals(temp.getClass())) {
                type = "Fire Sensor     ";
            } else {
                type = "Motion Sensor     ";
            }
            sensorList.add(new JLabel("   " + type + "   "),c);
            c.gridx++;
            sensorList.add(manualCBs.get(i),c);
            c.gridx++;
            sensorList.add(weekdayOn.get(i), c);
            c.gridx++;
            sensorList.add(weekdayOff.get(i), c);
            c.gridx++;
            sensorList.add(weekendOn.get(i), c);
            c.gridx++;
            sensorList.add(weekendOff.get(i), c);
            c.gridx++;
            sensorList.add(vacationOn.get(i), c);
            c.gridx++;
            sensorList.add(vacationOff.get(i), c);
            i++;
        }

        c.gridy = i+3;
        c.gridx = 5;
        sensorList.add(saveBtn, c);

        allSensors.add(sensorList);
        allSensors.setVisible(true);
    }
    private void generateOptionCard(int type) {
        int room = 1;
        try {
            room = Integer.parseInt(roomID.getText());
        } catch (NumberFormatException exc) {
            System.out.println("Exception: " + exc);
            return;
        }
        optionCard.removeAll();

        CardLayout cardLayout = new CardLayout();
        JPanel modeCard = new JPanel(cardLayout);
        JPanel manualCard, weekdayCard, weekendCard, vacationCard;
        manualCard = new JPanel(new GridBagLayout());
        weekdayCard = new JPanel(new GridBagLayout());
        weekendCard = new JPanel(new GridBagLayout());
        vacationCard = new JPanel(new GridBagLayout());
        modeCard.add(manualCard, "manual");
        modeCard.add(weekdayCard, "weekday");
        modeCard.add(weekendCard, "weekend");
        modeCard.add(vacationCard, "vacation");

        optionSaveBtn = new JButton("Save");
        optionSaveBtn.setActionCommand("save");
        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("cancel");

        Sensor sensor;
        if(type == 0) {
            sensor = BuildingList.buildings.get(0).getSubArea(room).getFireSensor();
        }
        else if (type == 1) {
            sensor = BuildingList.buildings.get(0).getSubArea(room).getMotionSensor();
        }
        else
            return;
        GridBagConstraints bc = new GridBagConstraints();
        bc.fill = GridBagConstraints.HORIZONTAL;
        bc.gridwidth = 2;
        bc.gridx = 0;
        bc.gridy = 0;
        JLabel sensorIdLbl = new JLabel("Sensor Id: 0" + sensor.getSensorID());
        sensorIdLbl.setFont(new Font("Serif", Font.BOLD, 14));
        optionCard.add(sensorIdLbl, bc);
        bc.gridy = 1;
        if(type ==0) {
            JLabel sensorType = new JLabel("Type: Fire Sensor");
            sensorType.setFont(new Font("Serif", Font.BOLD, 14));
            optionCard.add(sensorType, bc);
        }
        else {
            JLabel sensorType = new JLabel("Type: Motion Sensor");
            sensorType.setFont(new Font("Serif", Font.BOLD, 14));
            optionCard.add(sensorType, bc);
        }


        String [] modes = {"Manual", "Weekday", "Weekend", "Vacation"};
        JComboBox mode = new JComboBox(modes);
        mode.setFont(new Font("Serif", Font.BOLD, 14));
        bc.gridwidth = 1;
        bc.gridy++;
        JLabel modeLbl = new JLabel("Modes: ");
        modeLbl.setFont(new Font("Serif", Font.BOLD, 14));
        optionCard.add(modeLbl, bc);
        bc.gridx++;
        optionCard.add(mode, bc);
        bc.gridx = 0;
        bc.gridy++;
        bc.gridwidth = 3;
        optionCard.add(modeCard, bc);

        /* Generate fields inside manualCard*/
        bc.fill = GridBagConstraints.HORIZONTAL;
        bc.gridwidth = 1;
        bc.gridx = 0;
        bc.gridy = 0;
        JLabel manLbl = new JLabel("Manual Setting");
        manLbl.setFont(new Font("Serif", Font.BOLD, 15));
        JCheckBox manCB = new JCheckBox("Manual On/Off");
        manCB.setSelected(sensor.getManualOn());

        manualCard.add(Box.createRigidArea(new Dimension(0, 6)), bc);
        bc.gridy++;
        manualCard.add(manLbl, bc);
        bc.gridy++;
        manualCard.add(Box.createRigidArea(new Dimension(0, 2)), bc);
        bc.gridy++;
        manualCard.add(manCB, bc);
        bc.gridy++;
        manualCard.add(Box.createRigidArea(new Dimension(0, 2)), bc);
        bc.gridy++;
        manualCard.add(optionSaveBtn, bc);
        bc.gridx++;
        manualCard.add(cancelBtn, bc);

        /* Generate fields in weekdayCard
        bc.fill = GridBagConstraints.HORIZONTAL;
        bc.gridwidth = 1;
        bc.gridx = 0;
        bc.gridy = 0;
        JLabel weekdayLbl = new JLabel("Weekday Settings");
        weekdayLbl.setFont(new Font("Serif", Font.BOLD, 15));

        weekdayCard.add(Box.createRigidArea(new Dimension(0, 6)), bc);
        bc.gridy++;
        weekdayCard.add(manLbl, bc);
        bc.gridy++;
        weekdayCard.add(Box.createRigidArea(new Dimension(0, 2)), bc);
        bc.gridy++;
        weekdayCard.add(manCB, bc);
        bc.gridy++;
        weekdayCard.add(Box.createRigidArea(new Dimension(0, 2)), bc);
        bc.gridy++;
        weekdayCard.add(optionSaveBtn, bc);
        bc.gridx++;
        weekdayCard.add(cancelBtn, bc);
        */

        mode.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       //JComboBox cb = (JComboBox)e.getSource();
                                       //String place = (String)cb.getSelectedItem();
                                       String s = (String) mode.getSelectedItem();
                                       switch (s) {
                                           case "Manual":
                                               cardLayout.show(modeCard, "manual");
                                               manCB.setSelected(sensor.getManualOn());
                                               return;
                                           case "Weekday":
                                               cardLayout.show(modeCard, "weekday");
                                               return;
                                           case "Weekend":
                                               cardLayout.show(modeCard, "weekend");
                                               return;
                                           case "Vacation":
                                               cardLayout.show(modeCard, "vacation");
                                               return;
                                       }
                                   }
                               }
        );
        class optionsBtnHandler implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() == "save") {

                }
                if(e.getActionCommand() == "cancel") {

                }

            }
        }
    }
    private LocalTime parseString(String string) {
        String sub1 = string.substring(0, 2);
        String sub2 = string.substring(3, 5);
        LocalTime time = LocalTime.of(Integer.parseInt(sub1), Integer.parseInt(sub2));
        return time;
    }
    private void saveTextFields() {
        int i=0;
        for(Sensor temp: sensorArray) {
            temp.setManualOn(manualCBs.get(i).isSelected());
            temp.setOnTime(Schedule.Setting.weekday, parseString(weekdayOn.get(i).getText()));
            temp.setOffTime(Schedule.Setting.weekday, parseString(weekdayOff.get(i).getText()));
            temp.setOnTime(Schedule.Setting.weekend, parseString(weekendOn.get(i).getText()));
            temp.setOffTime(Schedule.Setting.weekend, parseString(weekendOff.get(i).getText()));
            temp.setOnTime(Schedule.Setting.vacation, parseString(vacationOn.get(i).getText()));
            temp.setOffTime(Schedule.Setting.vacation, parseString(vacationOff.get(i).getText()));
            i++;
        }
        allSensors.dispose();

    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "enter") {
                checkForSensor();
                cards.show(cardPanel, "sensors");
            }
            if(e.getActionCommand() == "all") {
                showAllSensors();
            }
            if(e.getActionCommand() == "fire") {
                generateOptionCard(0);
                cards.next(cardPanel);

            }
            if(e.getActionCommand() == "motion") {
                generateOptionCard(1);
                cards.next(cardPanel);

            }
            if(e.getActionCommand() == "save") {
                saveTextFields();
            }
            if(e.getActionCommand() == "cancel") {
                cards.next(cardPanel);
            }
            if(e.getActionCommand() == "cancel2") {

            }
        }
    }
}

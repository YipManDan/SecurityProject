package project.security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * Created by Daniel on 8/27/2015.
 */
public class DemoPane extends JPanel{

    private JComboBox combo;
    private JPanel panelRight, panelLeft, topPanel, bottomPanel;
    private JPanel panelMiddle;
    PanelCenter panelCenter = new PanelCenter(new RoomHandler());

    File toFile;


    private BuildingList.roomRef currentRef;
    private Schedule.Setting currentMode ;
    private enum eventType {FIRE, INTRUDER}
    private eventType thisEvent;
    JFormattedTextField currentTime;
    Boolean isWeekend;
    String dayIs;


    String soundName = "yourSound.wav";
    //DisplayBluePrint pic = new DisplayBluePrint();


    private DateFormat format;
    private DateTimeFormatter formatter;



    DemoPane() {
        setLayout(new BorderLayout());
        currentMode = Schedule.Setting.manual;
        isWeekend = true;
        toFile = new File("LogFile.txt");
        format = new SimpleDateFormat("HH:mm");
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        dayIs = "sunday";

        currentMode = Schedule.Setting.manual;
        BuildingList.getBuilding(0).updateSettings(Schedule.Setting.manual);

        panelRight = new JPanel(new GridBagLayout());
        topPanel = new JPanel(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(700, 35));
        panelLeft = new JPanel(new GridBagLayout());
        panelMiddle = new JPanel();
        bottomPanel = new JPanel(new GridBagLayout());

        panelMiddle.setBackground(Color.white);

        add(topPanel, BorderLayout.NORTH);
        add(panelRight, BorderLayout.LINE_END);
        add(panelLeft, BorderLayout.LINE_START);
        add(panelMiddle, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        // Create an Rectangular house structure

        /*Top Panel Components*/
        combo = new JComboBox();
        combo.addItem("Select a building");
        combo.addItem("Single House");
        combo.addItem("Commercial");

        combo.setMinimumSize(new Dimension(200, 25));
        combo.setMaximumSize(new Dimension(200, 25));
        topPanel.add(combo);

        /*Create Bottom Panel*/
        bottomPanelCreator();


        /*Right Panel*/
        panelRight.setPreferredSize(new Dimension(270, 100));
        panelRight.setMaximumSize(new Dimension(270, 100));

        /*Left Panel*/
        panelLeft.setPreferredSize(new Dimension(270, 30));
        panelLeft.setMaximumSize(new Dimension(270, 100));
        thisEvent = eventType.FIRE;
        generateLeft();


        /*Center Panel*/
        panelCenter.setBackground(Color.white);

                /*
                */
        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedBuilding = (String) combo.getSelectedItem();

                if (selectedBuilding.equals("Single House")) {
                    createHouse();
                } else if (selectedBuilding.equals("Commercial")) {
                    createCBuilding();
                    return;
                }
                else if (selectedBuilding.equals("Select a building")) {
                    clearMiddle();
                }


            }
        });
    }
    private void createHouse() {
        this.remove(panelMiddle);
        panelMiddle = panelCenter;
        this.add(panelMiddle, BorderLayout.CENTER);
        this.updateUI();
    }
    private void bottomPanelCreator() {
        bottomPanel.setMinimumSize(new Dimension(100, 200));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;

        String [] modes = {"Manual", "Automatic", "Vacation"};
        JComboBox mode = new JComboBox(modes);
        mode.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel modeLbl = new JLabel("Mode: ");
        modeLbl.setFont(new Font("Serif", Font.BOLD, 14));

        c.gridx = 7;
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 5)), c);
        c.gridx++;
        bottomPanel.add(modeLbl, c);
        c.gridx++;
        bottomPanel.add(mode, c);

        //JRadioButton dayOfWeek = new JRadioButton()
        JRadioButton sunday = new JRadioButton("Sunday");
        sunday.setMnemonic(KeyEvent.VK_B);
        sunday.setActionCommand("sunday");
        sunday.setSelected(true);

        JRadioButton monday = new JRadioButton("Monday");
        monday.setMnemonic(KeyEvent.VK_B);
        monday.setActionCommand("monday");

        JRadioButton tuesday = new JRadioButton("Tuesday");
        tuesday.setMnemonic(KeyEvent.VK_B);
        tuesday.setActionCommand("tuesday");

        JRadioButton wednesday = new JRadioButton("Wednesday");
        wednesday.setMnemonic(KeyEvent.VK_B);
        wednesday.setActionCommand("wednesday");

        JRadioButton thursday = new JRadioButton("Thursday");
        thursday.setMnemonic(KeyEvent.VK_B);
        thursday.setActionCommand("monday");

        JRadioButton friday = new JRadioButton("Friday");
        friday.setMnemonic(KeyEvent.VK_B);
        friday.setActionCommand("friday");

        JRadioButton saturday = new JRadioButton("Saturday");
        saturday.setMnemonic(KeyEvent.VK_B);
        saturday.setActionCommand("saturday");

        ButtonGroup days = new ButtonGroup();
        days.add(sunday);
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        days.add(saturday);

        c.gridy++;
        c.gridx = 0;
        bottomPanel.add(sunday, c);
        c.gridx++;
        bottomPanel.add(monday, c);
        c.gridx++;
        bottomPanel.add(tuesday, c);
        c.gridx++;
        bottomPanel.add(wednesday, c);
        c.gridx++;
        bottomPanel.add(thursday, c);
        c.gridx++;
        bottomPanel.add(friday, c);
        c.gridx++;
        bottomPanel.add(saturday, c);

        c.gridx++;
        c.gridx++;
        JLabel timeLbl = new JLabel("Time: ");
        timeLbl.setFont(new Font("Serif", Font.BOLD, 14));
        bottomPanel.add(timeLbl, c);


        currentTime= new JFormattedTextField(format);
        /*
        String time = LocalTime.now().toString();
        currentTime.setText(LocalTime.parse(time, formatter).toString());
        */
        currentTime.setText("17:23");
        c.gridx++;
        bottomPanel.add(currentTime, c);


        Building building = BuildingList.getBuilding(0);
        /* Mode will:
            update all sensors settings by calling the building's updateSettings method [This method will call setSetting in all internal subAreas]
         */
        mode.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String s = (String) mode.getSelectedItem();
               switch (s) {
                   case "Manual":
                       currentMode = Schedule.Setting.manual;
                       building.updateSettings(Schedule.Setting.manual);
                       return;
                   case "Automatic":
                       if(isWeekend) {
                           currentMode = Schedule.Setting.weekend;
                           building.updateSettings(Schedule.Setting.weekend);
                       } else {
                           currentMode = Schedule.Setting.weekday;
                           building.updateSettings(Schedule.Setting.weekday);
                       }
                       return;
                   case "Vacation":
                       currentMode = Schedule.Setting.vacation;
                       building.updateSettings(Schedule.Setting.vacation);
                       return;
               }
           }
        }
        );
        class radioListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) mode.getSelectedItem();
                dayIs = e.getActionCommand();
                if(e.getActionCommand() == "sunday" || e.getActionCommand() == "saturday") {
                    isWeekend = true;
                    System.out.println("Hey! It's the weekend!");
                    //Radio listeners need to be able to update setting between weekday and weekend
                    if(s.equals("Automatic")) {
                        if(isWeekend && currentMode.equals(Schedule.Setting.weekday)) {
                            currentMode = Schedule.Setting.weekend;
                            building.updateSettings(Schedule.Setting.weekend);
                        } else if(!isWeekend && currentMode.equals(Schedule.Setting.weekend)){
                            currentMode = Schedule.Setting.weekday;
                            building.updateSettings(Schedule.Setting.weekday);
                        }
                    }
                }
                else {
                    isWeekend = false;
                    if(s.equals("Automatic")) {
                        if(isWeekend && currentMode.equals(Schedule.Setting.weekday)) {
                            currentMode = Schedule.Setting.weekend;
                            building.updateSettings(Schedule.Setting.weekend);
                        } else if(!isWeekend && currentMode.equals(Schedule.Setting.weekend)){
                            currentMode = Schedule.Setting.weekday;
                            building.updateSettings(Schedule.Setting.weekday);
                        }
                    }
                    System.out.println("It's a weekday =(");
                }
            }
        }

        sunday.addActionListener(new radioListener());
        monday.addActionListener(new radioListener());
        tuesday.addActionListener(new radioListener());
        wednesday.addActionListener(new radioListener());
        thursday.addActionListener(new radioListener());
        friday.addActionListener(new radioListener());
        saturday.addActionListener(new radioListener());


        bottomPanel.updateUI();
    }
    private void createCBuilding() {
        this.remove(panelMiddle);
        panelMiddle = new JPanel();
        panelMiddle.setBackground(Color.white);

        URL url = this.getClass().getClassLoader()
                .getResource("commercial.jpg");
        JLabel commercialLabel = new JLabel(new ImageIcon(url));

        panelMiddle.add(commercialLabel);

        this.add(panelMiddle, BorderLayout.CENTER);
        this.updateUI();

    }
    private void clearMiddle() {
        this.remove(panelMiddle);
        panelMiddle = new JPanel();
        panelMiddle.setBackground(Color.white);

        this.add(panelMiddle, BorderLayout.CENTER);
        this.updateUI();
    }
    //private void generateLeft(BuildingList.roomRef input) {
    private void generateLeft() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;


        JLabel leftTitle = new JLabel("Select a situation: " );
        leftTitle.setFont(new Font("Arial", Font.BOLD, 16));
        //Font font = leftTitle.getFont();
        //Map attributes = font.getAttributes();
        //attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        //leftTitle.setFont(font.deriveFont(attributes));

        c.gridwidth = 2;
        panelLeft.add(leftTitle, c);
        c.gridy++;
        panelLeft.add(Box.createRigidArea(new Dimension(150, 5)), c);

        JRadioButton fireEvent = new JRadioButton("Fire");
        fireEvent.setMnemonic(KeyEvent.VK_B);
        fireEvent.setActionCommand("fire");
        fireEvent.setSelected(true);

        JRadioButton thiefEvent = new JRadioButton("Intruder");
        thiefEvent.setMnemonic(KeyEvent.VK_B);
        thiefEvent.setActionCommand("thief");

        ButtonGroup events = new ButtonGroup();
        events.add(fireEvent);
        events.add(thiefEvent);

        c.gridy++;
        panelLeft.add(fireEvent, c);

        c.gridy++;
        panelLeft.add(thiefEvent, c);

        class eventSelector implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() == "fire") {
                    thisEvent = eventType.FIRE;

                }
                if(e.getActionCommand() == "thief") {

                    thisEvent = eventType.INTRUDER;
                }

            }
        }

        fireEvent.addActionListener(new eventSelector());
        thiefEvent.addActionListener(new eventSelector());

        panelLeft.updateUI();
    }

    private void emergencyEvent(BuildingList.roomRef input) {
        writeText("Event Occurred on " + dayIs.toString()  + " at " + getTime().toString());
        writeText("System mode: " + currentMode.toString());

        String room = "";
        int roomNum = 0;
        switch (input) {
            case ROOM1:
                roomNum = 1;
                room = "bedroom 1";
                break;
            case CLOSET:
                roomNum = 2;
                room = "the closet";
                break;
            case KITCHEN:
                roomNum = 3;
                room = "the kitchen";
                break;
            case ROOM2:
                roomNum = 4;
                room = "bedroom 2";
                break;
            case LIVINGROOM:
                roomNum = 5;
                room = "living room";
                break;
            case BATHROOM:
                roomNum = 6;
                room = "the bathroom";
                break;
        }
        SubAreas subArea = BuildingList.getBuilding(0).getSubArea(roomNum);
        String event = "";
        Sensor sensor;
        LocalTime time = getTime();
        BuildingList.getBuilding(0).updateTime(time);
        /*Switch does:
            Determines type of event.
            Determine if appropriate sensor is available.
            (?)Update individual sensor setting.
            Determine if appropriate sensor is on.
         */
        switch (thisEvent) {
            case FIRE:
                if(!subArea.hasFireSensor()) {
                    System.out.println(input + " has no fire sensor");
                    writeText("A fire went undetected");
                    writeText(" ");
                    return;
                }
                sensor = subArea.getFireSensor();
                if(!sensor.isOn()) {
                    System.out.println(input + " fire sensor is off");
                    writeText("A fire went undetected");
                    writeText(" ");
                    return;
                }
                event = " fire";
                break;
            case INTRUDER:
                if(!subArea.hasMotionSensor()) {
                    System.out.println(input + " has no motion sensor");
                    writeText("An intrusion went undetected");
                    writeText(" ");
                    return;
                }
                sensor = subArea.getMotionSensor();
                if(!sensor.isOn()) {
                    System.out.println(input + " motion sensor is off");
                    writeText("An intrusion went undetected");
                    writeText(" ");
                    return;
                }
                event = "n intrusion";
                break;
            default:
                return;
        }

        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader()
                    .getResource("myplay.wav");
            AudioInputStream audioIn = AudioSystem
                    .getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input
            // stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e3) {
            e3.printStackTrace();
        }

        JFrame alertFrame = new JFrame();
        alertFrame.setPreferredSize(new Dimension(800, 450));
        alertFrame.setMinimumSize(new Dimension(800, 450));
        alertFrame.setTitle("Emergency Detected");
        alertFrame.setLocationRelativeTo(null);
        alertFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //alertFrame.dispose();


        JPanel eventPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        JLabel message1 = new JLabel("A" + event + " was detected in " + room);
        eventPanel.add(message1, c);
        writeText(message1.getText());

        if(MainSystem.phone1IsEnabled()) {
            JLabel phoneMessage1 = new JLabel("Emergency Number Called: " + MainSystem.getPhone1());
            c.gridy++;
            eventPanel.add(phoneMessage1, c);
            writeText(phoneMessage1.getText());
        }
        if(MainSystem.phone2IsEnabled()) {
            JLabel phoneMessage2 = new JLabel("Emergency Number Called: " + MainSystem.getPhone2());
            c.gridy++;
            eventPanel.add(phoneMessage2, c);
            writeText(phoneMessage2.getText());
        }

        JLabel pinLbl = new JLabel("Enter pin to stop alarm (DEFAULT: 123):  ");
        JLabel responseLbl = new JLabel("");

        JTextField pinTF = new JTextField(5);

        JButton pinBtn = new JButton("Enter");
        pinBtn.setActionCommand("pin");
        pinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pinTF.getText().equals("123"))
                    alertFrame.dispose();
                else {
                    responseLbl.setText("The pin entered is incorrect.");
                }
            }
        });

        c.gridx = 0;
        c.gridy++;
        eventPanel.add(pinLbl, c);
        c.gridx++;
        eventPanel.add(pinTF, c);
        c.gridx++;
        eventPanel.add(pinBtn, c);
        c.gridwidth = 3;
        c.gridy++;
        c.gridx--;
        eventPanel.add(responseLbl, c);



        writeText(" ");

        alertFrame.add(eventPanel);
        alertFrame.setVisible(true);
    }

    public void writeText(String s){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true));
            PrintWriter out = new PrintWriter(writer);
            out.println(s);
            //writer.write(s);
            //writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.err.print(e);
            System.exit(1);
        }
        /*
        try {
            Files.write(Paths.get("LogFile.txt"), s.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.print(e);
            System.exit(1);
        }
        */
    }
    private LocalTime getTime() {
        String string = currentTime.getText();
        String sub1 = string.substring(0, 2);
        String sub2 = string.substring(3, 5);
        LocalTime time = LocalTime.of(Integer.parseInt(sub1), Integer.parseInt(sub2));
        System.out.println("The current time is: " + time);
        return time;
    }

    private class RoomHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "room1") {
                emergencyEvent(BuildingList.roomRef.ROOM1);
            }
            if(e.getActionCommand() == "closet") {
                emergencyEvent(BuildingList.roomRef.CLOSET);
            }
            if(e.getActionCommand() == "kitchen") {
                emergencyEvent(BuildingList.roomRef.KITCHEN);
            }
            if(e.getActionCommand() == "room2") {
                emergencyEvent(BuildingList.roomRef.ROOM2);
            }
            if(e.getActionCommand() == "living room") {
                emergencyEvent(BuildingList.roomRef.LIVINGROOM);
            }
            if(e.getActionCommand() == "bathroom") {
                emergencyEvent(BuildingList.roomRef.BATHROOM);
            }

        }
    }
}

package project.security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private JTextField enterNumber1, enterNumber2;
    private JLabel firstNumber, secondNumber;
    private JButton savePhoneNumbers, saveSensor, cancelSensor;
    private JCheckBox fireSensor, motionSensor;
    private JLabel xLabel, yLabel;
    //private MouseEventAdapterA meaA;
    private String xStr, yStr;
    BufferedImage image;
    private BuildingList.roomRef currentRef;
    private Schedule.Setting currentMode ;
    String soundName = "yourSound.wav";
    //DisplayBluePrint pic = new DisplayBluePrint();



    DemoPane() {
        setLayout(new BorderLayout());
        currentMode = Schedule.Setting.manual;

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


        /*Center Panel*/
        panelCenter.setBackground(Color.white);

                /*
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

        String [] modes = {"Manual", "Weekday", "Weekend", "Vacation"};
        JComboBox mode = new JComboBox(modes);
        mode.setFont(new Font("Serif", Font.BOLD, 14));

        JLabel modeLbl = new JLabel("Modes: ");
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
        saturday.setActionCommand("monday");

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


        mode.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       String s = (String) mode.getSelectedItem();
                                       switch (s) {
                                           case "Manual":
                                               currentMode = Schedule.Setting.manual;
                                               return;
                                           case "Weekday":
                                               currentMode = Schedule.Setting.weekday;
                                               return;
                                           case "Weekend":
                                               currentMode = Schedule.Setting.weekend;
                                               return;
                                           case "Vacation":
                                               currentMode = Schedule.Setting.vacation;
                                               return;
                                       }
                                   }
                               }
        );
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
    private void generateLeft(BuildingList.roomRef input) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        int room = 0;
        String title = "";
        switch (input) {
            case ROOM1:
                room = 1;
                title = "Bedroom1";
                break;
            case CLOSET:
                room = 2;
                title = "Closet";
                break;
            case KITCHEN:
                room = 3;
                title = "Kitchen";
                break;
            case ROOM2:
                room = 4;
                title = "Bedroom2";
                break;
            case LIVINGROOM:
                room = 5;
                title = "Living Room";
                break;
            case BATHROOM:
                room = 6;
                title = "Bathroom";
                break;
        }
        currentRef = input;
        panelLeft.removeAll();
        JLabel roomLbl = new JLabel("Subarea: " + title);
        roomLbl.setFont(new Font("Arial", Font.BOLD, 16));
        Font font = roomLbl.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        roomLbl.setFont(font.deriveFont(attributes));

        c.gridwidth = 2;
        panelLeft.add(roomLbl, c);
        c.gridy++;
        panelLeft.add(Box.createRigidArea(new Dimension(150, 5)), c);

        SubAreas temp = BuildingList.getBuilding(0).getSubArea(room);



        panelLeft.updateUI();
    }
    private void addSensors(BuildingList.roomRef input) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        int room = 0;
        String title = "";
        switch (input) {
            case ROOM1:
                room = 1;
                title = "Bedroom1";
                break;
            case CLOSET:
                room = 2;
                title = "Closet";
                break;
            case KITCHEN:
                room = 3;
                title = "Kitchen";
                break;
            case ROOM2:
                room = 4;
                title = "Bedroom2";
                break;
            case LIVINGROOM:
                room = 5;
                title = "Living Room";
                break;
            case BATHROOM:
                room = 6;
                title = "Bathroom";
                break;
        }
        currentRef = input;
        panelLeft.removeAll();
        JLabel roomLbl = new JLabel("Subarea: " + title);
        roomLbl.setFont(new Font("Arial", Font.BOLD, 16));
        Font font = roomLbl.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        roomLbl.setFont(font.deriveFont(attributes));

        c.gridwidth = 2;
        panelLeft.add(roomLbl, c);
        c.gridy++;
        panelLeft.add(Box.createRigidArea(new Dimension(150, 5)), c);

        SubAreas temp = BuildingList.getBuilding(0).getSubArea(room);

        fireSensor = new JCheckBox("Fire Sensor");
        //fireSensor.setBackground(Color.GRAY);
        //fireSensor.setMnemonic(KeyEvent.VK_C);
        fireSensor.setSelected(temp.hasFireSensor());


        motionSensor = new JCheckBox("Motion Sensor");
        //motionSensor.setBackground(Color.GRAY);
        //motionSensor.setMnemonic(KeyEvent.VK_G);
        motionSensor.setSelected(temp.hasMotionSensor());

        c.gridy++;
        panelLeft.add(fireSensor, c);
        c.gridy++;
        panelLeft.add(motionSensor, c);

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        class ButtonHandler implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() == "save") {
                    if(temp.hasFireSensor() && !fireSensor.isSelected())
                        temp.removeFireSensor();
                    else if(!temp.hasFireSensor() && fireSensor.isSelected())
                        temp.createFireSensor();
                    if(temp.hasMotionSensor() && !motionSensor.isSelected())
                        temp.removeMotionSensor();
                    else if(!temp.hasMotionSensor() && motionSensor.isSelected())
                        temp.createMotionSensor();
                }
                if(e.getActionCommand() == "cancel") {
                    panelLeft.removeAll();
                    panelLeft.updateUI();
                }
            }
        }


        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(new ButtonHandler());
        saveBtn.setMaximumSize(new Dimension(50, 20));

        cancelBtn.setActionCommand("cancel");
        cancelBtn.addActionListener(new ButtonHandler());
        cancelBtn.setMaximumSize(new Dimension(50, 20));

        c.gridwidth = 2;
        c.gridy++;
        panelLeft.add(Box.createRigidArea(new Dimension(150, 5)), c);

        c.gridwidth = 1;
        c.gridy++;
        panelLeft.add(saveBtn, c);
        c.gridx++;
        panelLeft.add(cancelBtn, c);

        panelLeft.updateUI();
    }

    private class RoomHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "room1") {
                generateLeft(BuildingList.roomRef.ROOM1);
            }
            if(e.getActionCommand() == "closet") {
                generateLeft(BuildingList.roomRef.CLOSET);
            }
            if(e.getActionCommand() == "kitchen") {
                generateLeft(BuildingList.roomRef.KITCHEN);
            }
            if(e.getActionCommand() == "room2") {
                generateLeft(BuildingList.roomRef.ROOM2);
            }
            if(e.getActionCommand() == "living room") {
                generateLeft(BuildingList.roomRef.LIVINGROOM);
            }
            if(e.getActionCommand() == "bathroom") {
                generateLeft(BuildingList.roomRef.BATHROOM);
            }

        }
    }
}

package project.security;


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

public class SetupCard extends JPanel {

    private JComboBox combo;
    private JPanel panelRight, panelLeft, topPanel;
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
    String soundName = "yourSound.wav";
    //DisplayBluePrint pic = new DisplayBluePrint();



    SetupCard() {
        setLayout(new BorderLayout());

        // panelRight = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 6));
//        panelRight = new JPanel(new FlowLayout());
        panelRight = new JPanel(new GridBagLayout());
        topPanel = new JPanel(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(700, 35));
        panelLeft = new JPanel(new GridBagLayout());
        panelMiddle = new JPanel();

        panelMiddle.setBackground(Color.white);

        add(topPanel, BorderLayout.NORTH);
        add(panelRight, BorderLayout.LINE_END);
        add(panelLeft, BorderLayout.LINE_START);
        add(panelMiddle, BorderLayout.CENTER);
        //add(panelCenter, BorderLayout.CENTER);


        // Create an Rectangular house structure


        combo = new JComboBox();
        combo.addItem("Select a building");
        combo.addItem("Single House");
        combo.addItem("Commercial");

        combo.setMinimumSize(new Dimension(200, 25));
        combo.setMaximumSize(new Dimension(200, 25));
        topPanel.add(combo);

        //panelRight.setBackground(Color.GRAY);
        panelRight.setPreferredSize(new Dimension(270, 100));
        panelRight.setMaximumSize(new Dimension(270, 100));

        //panelLeft.setBackground(Color.GRAY);
        panelLeft.setPreferredSize(new Dimension(270, 30));
        panelLeft.setMaximumSize(new Dimension(270, 100));

        panelCenter.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        firstNumber = new JLabel(" Enter First Phone number:", JLabel.LEFT);
        firstNumber.setFont(new Font("Serif", Font.BOLD, 15));
        enterNumber1 = new JTextField();
        enterNumber1.setPreferredSize(new Dimension(100, 30));
        enterNumber1.setMaximumSize(new Dimension(150, 30));
        enterNumber1.setEditable(true);

        panelRight.add(firstNumber, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2;
        panelRight.add(enterNumber1, gbc);

        gbc.gridy++;
        panelRight.add(Box.createRigidArea(new Dimension(150, 8)), gbc);

        secondNumber = new JLabel(" Enter Second Phone number:", JLabel.LEFT);
        secondNumber.setFont(new Font("Serif", Font.BOLD, 15));
        enterNumber2 = new JTextField();
        enterNumber2.setPreferredSize(new Dimension(100, 30));
        enterNumber2.setMaximumSize(new Dimension(50, 30));
        enterNumber2.setEditable(true);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panelRight.add(secondNumber, gbc);
        gbc.gridwidth = 2;
        gbc.gridy++;
        panelRight.add(enterNumber2, gbc);

        gbc.gridy++;
        panelRight.add(Box.createRigidArea(new Dimension(150, 12)), gbc);

        savePhoneNumbers = new JButton("Save");
        savePhoneNumbers.setPreferredSize(new Dimension(100, 30));
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy++;
        panelRight.add(savePhoneNumbers, gbc);

        savePhoneNumbers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!enterNumber1.getText().equals(""))
                    BuildingList.getBuilding(0).setPhone1(Integer.parseInt(enterNumber1.getText()));
                if(!enterNumber2.getText().equals(""))
                    BuildingList.getBuilding(0).setPhone2(Integer.parseInt(enterNumber2.getText()));
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

            }
        });

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
                addSensors(BuildingList.roomRef.ROOM1);
            }
            if(e.getActionCommand() == "closet") {
                addSensors(BuildingList.roomRef.CLOSET);
            }
            if(e.getActionCommand() == "kitchen") {
                addSensors(BuildingList.roomRef.KITCHEN);
            }
            if(e.getActionCommand() == "room2") {
                addSensors(BuildingList.roomRef.ROOM2);
            }
            if(e.getActionCommand() == "living room") {
                addSensors(BuildingList.roomRef.LIVINGROOM);
            }
            if(e.getActionCommand() == "bathroom") {
                addSensors(BuildingList.roomRef.BATHROOM);
            }

        }
    }
}

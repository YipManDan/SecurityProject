package project.security;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class SetupCard extends JPanel {

    private JComboBox combo;
    private JPanel panelRight, panelLeft, topPanel;
    //public JPanel panelCenter;
    PanelCenter panelCenter = new PanelCenter(new roomHandler());
    private JTextField enterNumber1, enterNumber2;
    private JLabel firstNumber, secondNumber;
    private JButton savePhoneNumbers, saveSensor, cancelSensor;
    private JCheckBox fireSensor, motionSensor;
    private JLabel xLabel, yLabel;
    //private MouseEventAdapterA meaA;
    private String xStr, yStr;
    BufferedImage image;
    String soundName = "yourSound.wav";
    //DisplayBluePrint pic = new DisplayBluePrint();



    SetupCard() {
        setLayout(new BorderLayout());

        // panelRight = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 6));
        panelRight = new JPanel(new FlowLayout());
        topPanel = new JPanel(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(700, 30));
        panelLeft = new JPanel(new FlowLayout());

        //panelCenter = new JPanel();

        add(topPanel, BorderLayout.NORTH);
        add(panelRight, BorderLayout.LINE_END);
        add(panelLeft, BorderLayout.LINE_START);
        //add(panelCenter, BorderLayout.CENTER);


        // Create an Rectangular house structure


        combo = new JComboBox();
        combo.addItem("Select a building");
        combo.addItem("Single House");
        combo.addItem("Commercial");

        combo.setMinimumSize(new Dimension(200, 30));
        topPanel.add(combo);

        panelRight.setBackground(Color.cyan);
        panelRight.setPreferredSize(new Dimension(300, 100));

        panelLeft.setBackground(Color.cyan);
        panelLeft.setPreferredSize(new Dimension(200, 30));

        //panelCenter.setBackground(Color.white);

        firstNumber = new JLabel(" Enter First Phone number", JLabel.RIGHT);
        enterNumber1 = new JTextField();
        enterNumber1.setPreferredSize(new Dimension(100, 30));
        enterNumber1.setEditable(true);

        panelRight.add(firstNumber);
        panelRight.add(enterNumber1);

        secondNumber = new JLabel(" Enter Second Phone number ", JLabel.RIGHT);
        enterNumber2 = new JTextField();
        enterNumber2.setPreferredSize(new Dimension(100, 30));
        enterNumber2.setEditable(true);

        panelRight.add(secondNumber);
        panelRight.add(enterNumber2);

        savePhoneNumbers = new JButton("Save");
        savePhoneNumbers.setPreferredSize(new Dimension(100, 30));
        panelRight.add(savePhoneNumbers);
        savePhoneNumbers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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

            }
        });

        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedBuilding = (String) combo.getSelectedItem();

                if (selectedBuilding.equals("Single House")) {
					/*panelCenter.removeAll();
					URL url = this.getClass().getClassLoader()
							.getResource("singleHouse.jpg");
					JLabel homeLabel = new JLabel(new ImageIcon(url));
					panelCenter.add(homeLabel);
					panelCenter.updateUI();
					return;*/
                    createHouse();

                    //pic.singleHouse();

                } else if (selectedBuilding.equals("Commercial")) {

                    //pic.commercial();
                    panelCenter.removeAll();
                    URL url = this.getClass().getClassLoader()
                            .getResource("commercial.jpg");
                    JLabel commercialLabel = new JLabel(new ImageIcon(url));
                    panelCenter.add(commercialLabel);
                    panelCenter.updateUI();
                    return;

                }
                else if (selectedBuilding.equals("Select a building")) {

                    //pic.selection();
                    panelCenter.removeAll();
                    panelCenter.updateUI();
                }


            }
        });

        fireSensor = new JCheckBox("Fire Sensor");
        fireSensor.setMnemonic(KeyEvent.VK_C);
        fireSensor.setSelected(false);

        panelLeft.add(fireSensor);

        motionSensor = new JCheckBox("Motion Sensor");
        motionSensor.setMnemonic(KeyEvent.VK_G);
        motionSensor.setSelected(false);

        panelLeft.add(motionSensor);

        saveSensor = new JButton("Save");
        saveSensor.setPreferredSize(new Dimension(100, 30));
        panelLeft.add(saveSensor);
        saveSensor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        cancelSensor = new JButton("Cancel");
        cancelSensor.setPreferredSize(new Dimension(100, 30));
        panelLeft.add(cancelSensor);
        cancelSensor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        xLabel = new JLabel("");
        yLabel = new JLabel("");

        panelLeft.add(xLabel);
        panelLeft.add(yLabel);


    }

    private void createHouse() {
        System.out.println("inside method");
        this.add(panelCenter, BorderLayout.CENTER);
        this.updateUI();
        //this.add(panelCenter);
    }
    private class roomHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}

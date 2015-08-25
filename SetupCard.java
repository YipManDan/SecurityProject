package project.security;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class SetupCard extends JPanel {

    private JComboBox combo;
    private JPanel panelRight, panelLeft, topPanel, panelCenter;
    private JTextField enterNumber1, enterNumber2;
    private JLabel firstNumber, secondNumber;
    private JButton savePhoneNumbers, saveSensor, cancelSensor;
    private JCheckBox fireSensor, motionSensor;
    private JLabel xLabel, yLabel;
    private MouseEventAdapterA meaA;
    private String xStr, yStr;
    BufferedImage image;
    String soundName = "yourSound.wav";

    SetupCard() {
        setLayout(new BorderLayout());

        // panelRight = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 6));
        panelRight = new JPanel(new FlowLayout());
        topPanel = new JPanel(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(700, 30));
        panelLeft = new JPanel(new FlowLayout());

        panelCenter = new JPanel();

        add(topPanel, BorderLayout.NORTH);
        add(panelRight, BorderLayout.LINE_END);
        add(panelLeft, BorderLayout.LINE_START);
        add(panelCenter, BorderLayout.CENTER);

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

        panelCenter.setBackground(Color.white);

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
                    panelCenter.removeAll();
                    URL url = BuildingList.getBuilding(0).getImage();
                    JLabel homeLabel = new JLabel(new ImageIcon(url));
                    panelCenter.add(homeLabel);
                    panelCenter.updateUI();
                    return;

                } else if (selectedBuilding.equals("Commercial")) {

                    panelCenter.removeAll();
                    URL url = BuildingList.getBuilding(1).getImage();
                    JLabel commercialLabel = new JLabel(new ImageIcon(url));
                    panelCenter.add(commercialLabel);
                    panelCenter.updateUI();
                    return;

                }
                else if (selectedBuilding.equals("Select a building")) {

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

        // panelCenter.add( xLabel );
        // panelCenter.add( yLabel);
        panelLeft.add(xLabel);
        panelLeft.add(yLabel);

        // An instance of MouseAdapter listens to the mouse events
        addMouseListener(new MouseEventAdapterA());
        setVisible(true);

    }

    class MouseEventAdapterA extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            xStr = new String("<html><h2><font color=blue size=10>" + "X: ");
            xStr = xStr + x + "</font></h2></html>";

            yStr = new String("<html><h2><font color=green size=10>" + "Y: ");
            yStr = yStr + y + "</font></h2></html>";

            if (x >= 433 && x <= 762 && y >= 67 && y <= 210) {

                JOptionPane.showMessageDialog(null, "Room number 1");
            } else if (x >= 431 && x <= 674 && y >= 229 && y <= 632) {
                JOptionPane.showMessageDialog(null, "Room number 2");

            } else if (x >= 684 && x <= 1165 && y >= 229 && y <= 372) {
                JOptionPane.showMessageDialog(null, "Room number 3");

            }

            else if (x >= 763 && x <= 1165 && y >= 382 && y <= 629) {
                JOptionPane.showMessageDialog(null, "Room number 4");

            }

            xLabel.setText(xStr);
            yLabel.setText(yStr);

        }
    }

}

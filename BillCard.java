package project.security;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BillCard extends JPanel {

    private JLabel contractIDLabel, nameLabel,addressLabel,contact1Label, contact2Label;
    private JLabel emailLabel,dateLabel,fireSensorLabel, motionSensorLabel,pricePerFireLabel;
    private JLabel pricePerMotionLabel,priceOfFireLabel,priceOfMotionLabel,totalPriceLabel;
    private JTextField contractIDField,nameField,addressField,contact1Field,contact2Field;
    private JTextField emailField,fireSensorField,motionSensorField, pricePerFireField;
    private JTextField pricePerMotionLField,priceOfFireField,priceOfMotionField,totalPriceField;
    private JFormattedTextField dateField;
    private JPanel labelPane, fieldPane;
    private GridBagConstraints c = new GridBagConstraints();

    public static final int FONT_SIZE = 16;
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    BillCard() {
        this.setLayout(new GridBagLayout());
        /*
        labelPane = new JPanel(new GridLayout(0,1));
        labelPane.setBackground(Color.WHITE);
        fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.setBackground(Color.WHITE)3
        */
        //setBoxLayout1();

        //setBackground(Color.WHITE);

        generateLabels();
        generateTextFields();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        this.add(Box.createRigidArea(new Dimension(130, 10)), c);
        c.gridy++;
        this.add(contractIDLabel, c);
        c.gridx++;
        this.add(contractIDField, c);
        c.gridx=0;
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridy++;
        this.add(nameLabel, c);
        c.gridx++;
        this.add(nameField, c);
        c.gridx=0;
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridy++;
        this.add(addressLabel, c);
        c.gridx++;
        this.add(addressField, c);
        c.gridx=0;
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridy++;
        this.add(contact1Label, c);
        c.gridx++;
        this.add(contact1Field, c);
        c.gridx=0;
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridy++;
        this.add(contact2Label, c);
        c.gridx++;
        this.add(contact2Field, c);
        c.gridx=0;
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridy++;
        this.add(emailLabel, c);
        c.gridx++;
        this.add(emailField, c);
        c.gridx=0;
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridy++;
        this.add(dateLabel, c);
        c.gridx++;
        this.add(dateField, c);
        c.gridx=0;
        c.gridy++;


        /*


        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(contractIDLabel);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(nameLabel);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(addressLabel);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(contact1Label);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(contact2Label);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(emailLabel);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPane.add(dateLabel);
        labelPane.add(Box.createRigidArea(new Dimension(0, 10)));

        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(contractIDField);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(nameField);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(addressField);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(contact1Field);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(contact2Field);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(emailField);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        fieldPane.add(dateField);
        fieldPane.add(Box.createRigidArea(new Dimension(0, 1)));
        */
    }


    private void generateLabels() {
        contractIDLabel = new JLabel("Contract ID ");
        contractIDLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        nameLabel = new JLabel("User Name ");
        nameLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        contact1Label = new JLabel("Contact no. 1");
        contact1Label.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        contact2Label = new JLabel("Contact no. 2");
        contact2Label.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        emailLabel = new JLabel("Email ");
        emailLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        dateLabel = new JLabel("Date ");
        dateLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        fireSensorLabel = new JLabel("No of Firesensors ");
        fireSensorLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        motionSensorLabel = new JLabel("No of Motionsensors ");
        motionSensorLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        pricePerFireLabel = new JLabel("Price per FireSensor");
        pricePerFireLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        pricePerMotionLabel = new JLabel("Price per MotionSensor");
        pricePerMotionLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        priceOfFireLabel = new JLabel("Total Price of Firesensors");
        priceOfFireLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        priceOfMotionLabel = new JLabel("Total Price of Motionsensors");
        priceOfMotionLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
        totalPriceLabel = new JLabel("Total Price");
        totalPriceLabel.setFont(new Font("ARIAL", Font.BOLD, FONT_SIZE));
    }
    private void generateTextFields() {
        contractIDField = new JTextField(10);
        contractIDField.setPreferredSize(new Dimension(200, FONT_SIZE));
        nameField = new JTextField(10);
        addressField = new JTextField(10);
        contact1Field = new JTextField(10);
        contact2Field = new JTextField(10);
        emailField = new JTextField(10);
        dateField = new JFormattedTextField((format));
        fireSensorField = new JTextField(10);
        motionSensorField = new JTextField(10);
        pricePerFireField = new JTextField(10);
        pricePerMotionLField = new JTextField(10);
        priceOfFireField = new JTextField(10);
        priceOfMotionField= new JTextField(10);
        totalPriceField  = new JTextField(10);
    }

    public void setBoxLayout1(){
    	/* c.fill = GridBagConstraints.HORIZONTAL;
         c.gridwidth = 2;
         c.gridx = 0;
         c.gridy = 0;
         this.add(labelPane, c);
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridwidth = 1;
         c.gridx = 0;
         c.gridy = 1;
         this.add(fieldPane, c);*/
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(labelPane, c);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 1;
        c.gridy = 1;
        this.add(fieldPane, c);



    }
}



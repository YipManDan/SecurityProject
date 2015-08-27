package project.security;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;


public class BillCard extends JPanel {

    private JLabel contractIDLabel, nameLabel, addressLabel, contact1Label, contact2Label, emailLabel, dateLabel, fireSensorLabel, motionSensorLabel, pricePerFireLabel, pricePerMotionLabel, priceOfFireLabel, priceOfMotionLabel, totalPriceLabel;
    private JTextField contractIDField, nameField, addressField, contact1Field, contact2Field, emailField, fireSensorField, motionSensorField, pricePerFireField, pricePerMotionLField, priceOfFireField, priceOfMotionField, totalPriceField;
    private JFormattedTextField dateField;
    private JPanel centerPanel, panelRight, panelLeft;
    private JButton save;
    //private GridBagConstraints c = new GridBagConstraints();

    public static final int FONTSIZE = 20;
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    BillCard() {


        GridBagLayout gbl_panel_1 = new GridBagLayout();
		/*gbl_panel_1.columnWidths = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0,0 };

		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 ,0,0 ,0,0};
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0,0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0, 0,0,0,0,0,Double.MIN_VALUE };*/


        gbl_panel_1.columnWidths = new int[]{1};

        gbl_panel_1.rowHeights = new int[]{0};
        //gbl_panel_1.columnWeights = new double[] { 0.5};
        //gbl_panel_1.rowWeights = new double[] { 0};

        setLayout(gbl_panel_1);

        final GridBagConstraints c = new GridBagConstraints();
        final GridBagConstraints c1 = new GridBagConstraints();

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridy = 1;
        c1.gridy = 16;


        //centerPanel.setPreferredSize(new Dimension(200, 100));


        setBackground(Color.WHITE);

        contractIDLabel = new JLabel("Contract ID ");
        contractIDLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        contractIDField = new JTextField(10);
        contractIDField.setPreferredSize(new Dimension(500, FONTSIZE));

        this.add(Box.createRigidArea(new Dimension(290, 10)), c);
        c.gridy++;
        add(contractIDLabel, c);
        add(contractIDField, c);
        c.gridy++;

        nameLabel = new JLabel("User Name ");
        nameLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        nameField = new JTextField(10);
        nameField.setPreferredSize(new Dimension(300, FONTSIZE));


        add(nameLabel, c);
        add(nameField, c);
        c.gridy++;

        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        addressField = new JTextField(10);
        addressField.setPreferredSize(new Dimension(300, FONTSIZE));

        add(addressLabel, c);
        add(addressField, c);
        c.gridy++;

        contact1Label = new JLabel("Contact no. 1");
        contact1Label.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        contact1Field = new JTextField(10);
        contact1Field.setPreferredSize(new Dimension(300, FONTSIZE));

        add(contact1Label, c);
        add(contact1Field, c);
        c.gridy++;

        contact2Label = new JLabel("Contact no. 2");
        contact2Label.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        contact2Field = new JTextField(10);
        contact2Field.setPreferredSize(new Dimension(300, FONTSIZE));

        add(contact2Label, c);
        add(contact2Field, c);
        c.gridy++;

        emailLabel = new JLabel("Email ");
        emailLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        emailField = new JTextField(10);
        emailField.setPreferredSize(new Dimension(300, FONTSIZE));

        add(emailLabel, c);
        add(emailField, c);
        c.gridy++;

        dateLabel = new JLabel("Date ");
        dateLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        dateField = new JFormattedTextField((format));
        dateField.setPreferredSize(new Dimension(150, FONTSIZE));

        add(dateLabel, c);
        add(dateField, c);
        c.gridy++;

        fireSensorLabel = new JLabel("No of Firesensors ");
        fireSensorLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        fireSensorField = new JTextField(10);
        fireSensorField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(fireSensorLabel, c);
        add(fireSensorField, c);
        c.gridy++;

        motionSensorLabel = new JLabel("No of Motionsensors ");
        motionSensorLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        motionSensorField = new JTextField(10);
        motionSensorField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(motionSensorLabel, c);
        add(motionSensorField, c);
        c.gridy++;

        pricePerFireLabel = new JLabel("Price per FireSensor");
        pricePerFireLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        pricePerFireField = new JTextField(10);
        pricePerFireField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(pricePerFireLabel, c);
        add(pricePerFireField, c);
        c.gridy++;

        pricePerMotionLabel = new JLabel("Price per MotionSensor");
        pricePerMotionLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        pricePerMotionLField = new JTextField(10);
        pricePerMotionLField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(pricePerMotionLabel, c);
        add(pricePerMotionLField, c);
        c.gridy++;

        priceOfFireLabel = new JLabel("Total Price of Firesensors");
        priceOfFireLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        priceOfFireField = new JTextField(10);
        priceOfFireField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(priceOfFireLabel, c);
        add(priceOfFireField, c);
        c.gridy++;

        priceOfMotionLabel = new JLabel("Total Price of Motionsensors");
        priceOfMotionLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        priceOfMotionField = new JTextField(10);
        priceOfMotionField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(priceOfMotionLabel, c);
        add(priceOfMotionField, c);
        c.gridy++;


        totalPriceLabel = new JLabel("Total Price");
        totalPriceLabel.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        totalPriceField = new JTextField(10);
        totalPriceField.setPreferredSize(new Dimension(100, FONTSIZE));

        add(totalPriceLabel, c);
        add(totalPriceField, c);
        c.gridy++;
        c.gridy++;

        save = new JButton("Save as PDF");
        save.setFont(new Font("ARIAL", Font.BOLD, FONTSIZE));
        add(save, c1);
        c1.gridy++;


    }
}




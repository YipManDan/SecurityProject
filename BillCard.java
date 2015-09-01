package project.security;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;


public class BillCard extends JPanel {

    private JLabel contractIDLabel, nameLabel, addressLabel,cityStateLabel,zipLabel,phoneLabel, contact1Label, contact2Label, emailLabel, dateLabel, fireSensorLabel, motionSensorLabel, pricePerFireLabel, pricePerMotionLabel, priceOfFireLabel, priceOfMotionLabel, totalPriceLabel;
    private JTextField contractIDField, nameField, addressField, cityStateField,zipField,phoneField,contact1Field, contact2Field, emailField, fireSensorField, motionSensorField, pricePerFireField, pricePerMotionLField, priceOfFireField, priceOfMotionField, totalPriceField;
    private JFormattedTextField dateField;
    private JPanel centerPanel, panelRight, panelLeft;
    private JButton update;
    File inFile;
    // private SubAreas subAreas = new SubAreas() ;
    //private GridBagConstraints c = new GridBagConstraints();

    public static final int FONTSIZE = 15;
    public final int BOX_SIZE = 27;
    //Font font1 = new Font("Courier", Font.BOLD, 16);
    //DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    int motionCount;
    int fireCount;

    BillCard() {

        //inFile = new File("testfile.txt");
        inFile = new File("UserFile.txt");
        ArrayList<SubAreas> areaList = BuildingList.getBuilding(0).getSubAreaList();
        motionCount =0;
        fireCount = 0;
        for(SubAreas temp: areaList) {
            if(temp.hasMotionSensor()) {
                ++motionCount;
            }
            if(temp.hasFireSensor()) {
                ++fireCount;
            }
        }


        GridBagLayout gbl_panel_1 = new GridBagLayout();

        gbl_panel_1.columnWidths = new int[]{1};
        gbl_panel_1.rowHeights = new int[]{0};

        setLayout(gbl_panel_1);

        final GridBagConstraints c = new GridBagConstraints();
        final GridBagConstraints c1 = new GridBagConstraints();

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridy = 1;
        c1.gridy = 16;

        //setBackground(Color.WHITE);

        contractIDLabel = new JLabel("Contract ID: ");
        contractIDLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        contractIDField = new JTextField(15);
        contractIDField.setPreferredSize(new Dimension(500, FONTSIZE + 7));
        contractIDField.setText("C1120");
        //contractIDField.setFont(font1);
        contractIDField.setEditable(false);

        JLabel title = new JLabel("System-Generated Bill");
        title.setFont(new Font("Serif", Font.BOLD, FONTSIZE+9));
        add(title);
        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(260, 15)), c);
        c.gridy++;
        add(contractIDLabel, c);
        add(contractIDField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        nameLabel = new JLabel("User Name: ");
        nameLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        //nameField.setFont(font1);
        nameField.setEditable(false);

        add(nameLabel, c);
        add(nameField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        addressField = new JTextField(15);
        addressField.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        // addressField.setFont(font1);
        addressField.setEditable(false);

        add(addressLabel, c);
        add(addressField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;



        cityStateLabel = new JLabel("City, State:");
        cityStateLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        cityStateField = new JTextField(15);
        cityStateField.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        //cityStateField.setFont(font1);
        cityStateField.setEditable(false);


        add(cityStateLabel, c);
        add(cityStateField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;


        zipLabel = new JLabel("ZipCode:");
        zipLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        zipField = new JTextField(15);
        zipField.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        // zipField.setFont(font1);
        zipField.setEditable(false);

        add(zipLabel, c);
        add(zipField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        phoneLabel = new JLabel("User Phone No.:");
        phoneLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        phoneField = new JTextField(15);
        phoneField.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        //phoneField.setFont(font1);
        phoneField.setEditable(false);

        add(phoneLabel, c);
        add(phoneField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;


        contact1Label = new JLabel("Emergency Contact No.1:");
        contact1Label.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        contact1Field = new JTextField(15);
        contact1Field.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        contact1Field.setText(String.valueOf(MainSystem.getPhone1()));


        //contact1Field.setFont(font1);
        contact1Field.setEditable(false);

        add(contact1Label, c);
        add(contact1Field, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        contact2Label = new JLabel("Emergency Contact No.2");
        contact2Label.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        contact2Field = new JTextField(15);
        contact2Field.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        contact2Field.setText(String.valueOf(MainSystem.getPhone2()));
        //contact2Field.setFont(font1);
        contact2Field.setEditable(false);

        add(contact2Label, c);
        add(contact2Field, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension(300, FONTSIZE + 7));
        //emailField.setFont(font1);
        emailField.setEditable(false);

        add(emailLabel, c);
        add(emailField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        dateLabel = new JLabel("Date: ");
        dateLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        dateField = new JFormattedTextField((format));
        dateField.setColumns(15);
        dateField.setPreferredSize(new Dimension(135, FONTSIZE+7));
        dateField.setValue(new java.util.Date());
        //dateField.setFont(font1);
        dateField.setEditable(false);

        add(dateLabel, c);
        add(dateField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        fireSensorLabel = new JLabel("No of Firesensors: ");
        fireSensorLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        fireSensorField = new JTextField(15);
        fireSensorField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        fireSensorField.setText(String.valueOf(fireCount));
        // fireSensorField.setFont(font1);
        fireSensorField.setEditable(false);

        add(fireSensorLabel, c);
        add(fireSensorField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        motionSensorLabel = new JLabel("No of Motionsensors: ");
        motionSensorLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        motionSensorField = new JTextField(15);
        motionSensorField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        motionSensorField.setText(String.valueOf(motionCount));
        // motionSensorField.setFont(font1);
        motionSensorField.setEditable(false);

        add(motionSensorLabel, c);
        add(motionSensorField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        pricePerFireLabel = new JLabel("Price per FireSensor:");
        pricePerFireLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        pricePerFireField = new JTextField(15);
        pricePerFireField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        int firePrice = 20;
        pricePerFireField.setText("$" + String.valueOf(firePrice));
        //pricePerFireField.setFont(font1);
        pricePerFireField.setEditable(false);

        add(pricePerFireLabel, c);
        add(pricePerFireField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        pricePerMotionLabel = new JLabel("Price per MotionSensor:");
        pricePerMotionLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        pricePerMotionLField = new JTextField(15);
        pricePerMotionLField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        int motionPrice = 18;
        pricePerMotionLField.setText("$" + String.valueOf(motionPrice));
        //pricePerMotionLField.setFont(font1);
        pricePerMotionLField.setEditable(false);

        add(pricePerMotionLabel, c);
        add(pricePerMotionLField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        priceOfFireLabel = new JLabel("Total Price of Firesensors:");
        priceOfFireLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        priceOfFireField = new JTextField(15);
        priceOfFireField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        int priceOfFire = fireCount * firePrice;
        priceOfFireField.setText("$" + String.valueOf(priceOfFire));
        // priceOfFireField.setFont(font1);
        priceOfFireField.setEditable(false);


        add(priceOfFireLabel, c);
        add(priceOfFireField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;

        priceOfMotionLabel = new JLabel("Total Price of Motionsensors:");
        priceOfMotionLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        priceOfMotionField = new JTextField(15);
        priceOfMotionField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        int priceOfMotion = motionCount * motionPrice;
        priceOfMotionField.setText("$" + String.valueOf(priceOfMotion));
        //priceOfMotionField.setFont(font1);
        priceOfMotionField.setEditable(false);

        add(priceOfMotionLabel, c);
        add(priceOfMotionField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;


        totalPriceLabel = new JLabel("Total Price:");
        totalPriceLabel.setFont(new Font("ARIAL", Font.PLAIN, FONTSIZE));
        totalPriceField = new JTextField(15);
        totalPriceField.setPreferredSize(new Dimension(100, FONTSIZE+7));
        int totalPrice= priceOfFire + priceOfMotion;
        totalPriceField.setText("$" + String.valueOf(totalPrice));
        // totalPriceField.setFont(font1);
        totalPriceField.setEditable(false);

        add(totalPriceLabel, c);
        add(totalPriceField, c);
        add(Box.createRigidArea(new Dimension(0, BOX_SIZE)), c);
        c.gridy++;
        c.gridy++;

       /* save = new JButton("Save as PDF");
        save.setFont(new Font("ARIAL", Font.ITALIC, 16));
        add(save, c1);
        c1.gridy++;*/
        //c1.gridx++;
        add(Box.createRigidArea(new Dimension(14, 0)), c1);
        //c1.gridx++;
        update = new JButton("Refresh");
        //update.setFont(new Font("ARIAL", Font.ITALIC, 16));
        c1.gridheight = 2;
        update.setFont(new Font("Serif", Font.PLAIN, 16));
        update.setPreferredSize(new Dimension(115, 24));
        add(update, c1);
        c1.gridy++;

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        try {
            fillTextFields(inFile);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

    }

    private void update() {
        try {
            fillTextFields(inFile);
        } catch (IOException e) {
            System.err.print(e);
            System.exit(1);
        }
        this.updateUI();
        contact1Field.setText(String.valueOf(MainSystem.getPhone1()));
        contact2Field.setText(String.valueOf(MainSystem.getPhone2()));
        ArrayList<SubAreas> areaList = BuildingList.getBuilding(0).getSubAreaList();
        motionCount =0;
        fireCount = 0;
        for(SubAreas temp: areaList) {
            if(temp.hasMotionSensor()) {
                ++motionCount;
            }
            if(temp.hasFireSensor()) {
                ++fireCount;
            }
        }

        fireSensorField.setText(String.valueOf(fireCount));
        motionSensorField.setText(String.valueOf(motionCount));

        int firePrice = 20;
        int priceOfFire = fireCount * firePrice;
        priceOfFireField.setText("$" + String.valueOf(priceOfFire));

        int motionPrice = 18;
        int priceOfMotion = motionCount * motionPrice;
        priceOfMotionField.setText("$" + String.valueOf(priceOfMotion));

        int totalPrice= priceOfFire + priceOfMotion;
        totalPriceField.setText("$" + String.valueOf(totalPrice));
    }

    public void fillTextFields(File inFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        nameField.setText(reader.readLine());
        addressField.setText(reader.readLine());
        cityStateField.setText(reader.readLine());
        zipField.setText(reader.readLine());
        phoneField.setText(reader.readLine());
        emailField.setText(reader.readLine());
        reader.close();
    }
}





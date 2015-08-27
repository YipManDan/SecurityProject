package project.security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Daniel on 8/27/2015.
 */
public class RegisterPane extends JPanel {
    private JButton save;

    private JLabel  nameLabel, addressLabel, cityStateLabel, zipLabel, phoneLabel, emailLabel;
    private static JTextField  nameField, addressField, cityStateField, zipField, phoneField,  emailField;
    private JFormattedTextField dateField;

    public static final int FONTSIZE = 16;
    Font font = new Font("Arial", Font.PLAIN, FONTSIZE);
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    private GridBagConstraints c = new GridBagConstraints();
    RegisterPane(ActionListener listener) {
        File inFile = new File("testfile.txt");


        setLayout(new GridBagLayout());
        save = new JButton("Save");
        save.setActionCommand("save");
        save.addActionListener(listener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        //c.weighty = 1;
        c.gridwidth = 2;
        JLabel title = new JLabel("New User Registration");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        add(title, c);

        c.gridy++;
        this.add(Box.createRigidArea(new Dimension(290, 5)), c);

        c.gridwidth = 1;
        nameLabel = new JLabel("First and Last Name:    ");
        nameField = new JTextField(15);
        addThis(nameLabel, nameField);

        addressLabel = new JLabel("Street Address:    ");
        addressField = new JTextField(15);
        addThis(addressLabel, addressField);

        cityStateLabel = new JLabel("City, State:    ");
        cityStateField = new JTextField(15);
        addThis(cityStateLabel, cityStateField);

        zipLabel = new JLabel("Zip Code:     ");
        zipField = new JTextField(15);
        addThis(zipLabel, zipField);

        phoneLabel = new JLabel("Phone Number:    ");
        phoneField = new JTextField(15);
        addThis(phoneLabel, phoneField);

        emailLabel = new JLabel("Email Address:    ");
        emailField = new JTextField(15);
        addThis(emailLabel, emailField);

        try {
            fillTextFields(inFile);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(Box.createRigidArea(new Dimension(290, 10)), c);
        c.gridy ++;
        c.gridwidth = 1;
        c.gridx = 1;
        this.add(save, c);
    }

    private void addThis(JLabel label, JTextField textField) {
        c.gridy++;
        c.gridwidth = 2;
        c.gridx = 0;
        this.add(Box.createRigidArea(new Dimension(290, 5)), c);
        c.gridy++;
        c.gridwidth = 1;
        label.setFont(font);
        add(label, c);
        c.gridx = 1;
        add(textField, c);
        c.gridx = 0;

    }
    /*
    public static void copyFile3(File fromFile, File toFile) throws IOException {
        //Scanner freader = new Scanner(fromFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));

        //... Loop as long as there are input lines.
        String line = null;
        while (freader.hasNextLine()) {
            line = freader.nextLine();
            writer.write(line);
            writer.newLine();   // Write system dependent end of line.
        }

        //... Close reader and writer.
        freader.close();  // Close to unlock.
        writer.close();  // Close to unlock and flush to disk.
    }
    */
    public static void saveText(File toFile) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
        writer.write(nameField.getText());
        writer.newLine();
        writer.write(addressField.getText());
        writer.newLine();
        writer.write(cityStateField.getText());
        writer.newLine();
        writer.write(zipField.getText());
        writer.newLine();
        writer.write(phoneField.getText());
        writer.newLine();
        writer.write(emailField.getText());
        writer.newLine();
        writer.close();
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
    public void write(String s, File toFile) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
        writer.write(s);
        writer.close();
    }
}

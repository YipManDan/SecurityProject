package project.security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Daniel on 8/25/2015.
 */
public class LogPane extends JPanel{
    private JPanel left, right, center;
    private static GridBagConstraints c = new GridBagConstraints();
    File inFile;
    JButton refresh;

    LogPane() {
        center = new JPanel();
        center.setLayout(new GridBagLayout());

        inFile = new File("LogFile.txt");

        /*
        try {
            PrintWriter writer = new PrintWriter(inFile);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        */

        refresh = new JButton("Refresh");
        refresh.setActionCommand("refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    refreshPane(inFile);
                } catch (IOException e) {
                    System.err.println(e);
                    System.exit(1);
                }
            }
        });
        try {
            refreshPane(inFile);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        setVisible(true);
    }

    public void refreshPane (File inFile) throws IOException {
        center.removeAll();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;

        JLabel title = new JLabel("Event Log: ");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        center.add(title, c);
        c.gridx++;
        center.add(refresh, c);

        this.add(center);

        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String line = null;
        c.gridx=0;
        c.gridwidth = 2;
        c.gridy++;
        center.add(Box.createRigidArea(new Dimension(200, 10)), c);
        while ((line=reader.readLine()) != null) {
            c.gridy++;
            center.add(new JLabel(line), c);
        }
        c.gridwidth = 1;
        reader.close();
    }
    /*
    private void logText(String text) {
        c.gridy++;
        add(new JLabel(text), c);
    }
    */
}

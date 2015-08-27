package project.security;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    JFormattedTextField test;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    LogPane() {
        /*
        setLayout(new BorderLayout());
        left = new JPanel();
        left.setBackground(Color.BLACK);
        left.setPreferredSize(new Dimension(200, 100));
        right = new JPanel();
        right.setBackground(Color.GREEN);
        right.setPreferredSize(new Dimension(200, 100));
        center = new JPanel();
        center.setBackground(Color.PINK);
        this.add(left, BorderLayout.LINE_START);
        this.add(right, BorderLayout.LINE_END);
        this.add(center, BorderLayout.CENTER);
        */
        left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(new JLabel("HelloWorld"));

        Date date = new Date();
        LocalDateTime date2 = LocalDateTime.now();
        //LocalDateTime date3 = LocalDateTime.parse("10:15:00", DateTimeFormatter.ISO_TIME);
        LocalDateTime date3 = LocalDateTime.parse("2014-06-30T12:01:00", DateTimeFormatter.ISO_DATE_TIME);
        System.out.print(date3);
        //LocalDateTime date3 = LocalDateTime.parse("23:59", formatter);
        //LocalTime time = LocalTime.of(date.getHours(), date.getMinutes());
        //LocalDateTime time = L

        test = new JFormattedTextField(formatter.toFormat());
        test.setText(date2.format(formatter));
        test.setMinimumSize(new Dimension(400, 30));

        JButton save = new JButton("Save");
        save.addActionListener(new ButtonHandler());

        left.add(test);
        left.add(save);


        this.add(left);

        setVisible(true);
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println(test.getText());
            String sub1 = test.getText().substring(0,2);
            String sub2 = test.getText().substring(3,5);
            System.out.println("The hour is: "+ sub1 + "The minute is: " + sub2);
            LocalTime time = LocalTime.of(Integer.parseInt(sub1), Integer.parseInt(sub2));
            System.out.print(time);

            //LocalDateTime test2 = LocalDateTime.parse(test.getText(), formatter);

        }

    }
}

package project.security;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created by Daniel on 8/25/2015.
 */
public class LogPane extends JPanel{
    private JPanel left, right, center;
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
        this.add(left);

        setVisible(true);
    }
}
